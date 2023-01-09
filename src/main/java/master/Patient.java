package master;

import Interface.GUI_test;
import Interface.Patient_Editor;
import chartPanel.*;
import netRelated.netAction;
import netRelated.requestPack;
import netRelated.responsePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static java.lang.Math.floor;


public class Patient extends JButton {
    public String first_name;
    public String last_name;
    public String reference_value;
    public String gender;
    public int year_of_birth;
    public Double temp_min;
    public Double temp_max;
    public Integer hr_min;
    public Integer hr_max;
    public Integer sys_min;
    public Integer sys_max;
    public Integer dia_min;
    public Integer dia_max;
    public Integer resp_min;
    public Integer resp_max;
    public GUI_test mainGUI;

    // data and charts for display
    private inputData dataEcg1;
    private inputData dataTemperature;
    public SeriesChartPane panelEcg1;
    public SeriesChartPane panelEcg2;
    public Chart_Label_Display panelTemperature;

    public Chart_Label_Display panelHeartRate;

    public Chart_Label_Display panelSysBloodPressure;

    public Chart_Label_Display panelDiaBloodPressure;

    public Chart_Label_Display panelRespiratoryRate;

    public Chart_Label_Display panelRespiratoryPattern;

    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private long time_milli;

    // fields for time interval

    public Double ecg_interval = 5.0;
    public Double temperature_interval = 6.0;

    public Patient(String first_name,
                   String last_name,
                   String reference_value,
                   String gender,
                   Integer year_of_birth,
                   Double temp_min,
                   Double temp_max,
                   Integer hr_min,
                   Integer hr_max,
                   Integer sys_min,
                   Integer sys_max,
                   Integer dia_min,
                   Integer dia_max,
                   Integer resp_min,
                   Integer resp_max,
                   GUI_test mainGUI
    ){

        this.first_name = first_name;
        this.last_name= last_name;
        this.reference_value= reference_value;
        this.gender= gender;
        this.year_of_birth= year_of_birth;
        this.temp_min= temp_min;
        this.temp_max= temp_max;
        this.hr_min= hr_min;
        this.hr_max= hr_max;
        this.sys_min= sys_min;
        this.sys_max= sys_max;
        this.dia_min= dia_min;
        this.dia_max= dia_max;
        this.resp_min= resp_min;
        this.resp_max= resp_max;
        this.mainGUI = mainGUI;

        String patient_full_name = this.first_name+" "+this.last_name;
        this.setText("<html>" + patient_full_name.replaceAll("<break>", "<br>") + "</html>");
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setMaximumSize(new Dimension(170,100));
        this.setPreferredSize(new Dimension(170,100));
        this.setMinimumSize(new Dimension(170,100));
        this.setHorizontalAlignment(SwingConstants.CENTER);


        this.addMouseListener(new MouseAdapter() {
            //@Override
            public void mouseClicked(MouseEvent e) {
                patient_mouseClicked(e);
            }
        });
        panelEcg1 = load_chart((long) floor(ecg_interval*1000),"ecg1","ECG_Lead_I");
        panelEcg2 = load_chart((long) floor(ecg_interval * 1000), "ecg1", "ECG_Lead_II");

        panelTemperature=load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Body Temperature");
        panelHeartRate=load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Heart Rate");
        panelRespiratoryRate=load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Respiratory Rate");
        panelDiaBloodPressure = load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Diastolic Blood Pressure");
        panelSysBloodPressure = load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Systolic Blood Pressure");
        panelRespiratoryPattern = load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Respiratory Pattern");


        display(this.reference_value);
        this.addActionListener(e -> switch_patient(e));
        this.time_milli = time.getTime();
    }

    private void switch_patient(ActionEvent e) {
        // find the previous patients
        Display_Chart current_Temp = (Display_Chart) mainGUI.body_temp_table.getComponent(0);
        Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();

        Patient previous_patient = current_temp_cl_display.patient;
        System.out.println(previous_patient.first_name);
        //previous_patient.stop_display();
        if (this.equals(previous_patient)==false) {
            previous_patient.panelEcg1.worker.cancel(true);
            previous_patient.panelEcg2.worker.cancel(true);
            previous_patient.panelTemperature.updater.cancel(true);
            previous_patient.panelRespiratoryRate.updater.cancel(true);
            previous_patient.panelDiaBloodPressure.updater.cancel(true);
            previous_patient.panelSysBloodPressure.updater.cancel(true);
            previous_patient.panelRespiratoryPattern.updater.cancel(true);
            previous_patient.panelHeartRate.updater.cancel(true);
            System.out.println(previous_patient.panelEcg1.worker.isCancelled());
        }

        display(this.reference_value);
        Timestamp time_now = new Timestamp(System.currentTimeMillis());
        if ((time_now.getTime()-time_milli)<=300){
            this.setEnabled(false);
            Patient_Editor editor = new Patient_Editor(this.mainGUI, this);
            editor.setVisible(true);
        }
        this.time_milli = time_now.getTime();
    }

    public SeriesChartPane load_chart(long chart_capacity, String type, String title){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime();
        responsePack respPack =netAction.recordData(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime);
        return new SeriesChartPane(new inputData(respPack.valueList,dataBaseInitialTime,0.002),respPack.lastTime,type,this,title);
    }

    public Chart_Label_Display load_chartLabel(long chart_capacity,String type, String title){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime();
        responsePack respPack =netAction.recordDataTemp(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime);
        return new Chart_Label_Display(this,new inputData(respPack.valueList,dataBaseInitialTime,0.0166667),respPack.lastTime,type,title);
    }

    private void display(String reference_value) {

        if (this.panelEcg1.worker.isCancelled()) {
            this.panelEcg1.worker = new UpdateWorker(this.panelEcg1);
            this.panelEcg1.worker.execute();
            System.out.println(this.panelEcg1.worker.isCancelled());

            this.panelEcg2.worker = new UpdateWorker(this.panelEcg2);
            this.panelEcg2.worker.execute();

            this.panelTemperature.updater = new Chart_Label_Update(this.panelTemperature,10);
            this.panelTemperature.updater.execute();

            this.panelHeartRate.updater = new Chart_Label_Update(this.panelHeartRate,10);
            this.panelHeartRate.updater.execute();

            this.panelRespiratoryPattern.updater = new Chart_Label_Update(this.panelRespiratoryPattern,10);
            this.panelRespiratoryPattern.updater.execute();

            this.panelRespiratoryRate.updater = new Chart_Label_Update(this.panelRespiratoryRate,10);
            this.panelRespiratoryRate.updater.execute();

            this.panelSysBloodPressure.updater = new Chart_Label_Update(this.panelSysBloodPressure,10);
            this.panelSysBloodPressure.updater.execute();

            this.panelDiaBloodPressure.updater = new Chart_Label_Update(this.panelDiaBloodPressure,10);
            this.panelDiaBloodPressure.updater.execute();
        }

        // ecg1
        this.mainGUI.ecg1.setVisible(false);
        this.mainGUI.ecg1.removeAll();
        this.mainGUI.ecg1.add(this.panelEcg1);
        this.mainGUI.ecg1.setVisible(true);
        // ecg2
        this.mainGUI.ecg2.setVisible(false);
        this.mainGUI.ecg2.removeAll();
        this.mainGUI.ecg2.add(this.panelEcg2);
        this.mainGUI.ecg2.setVisible(true);
        // body temp
        this.mainGUI.body_temp_table.setVisible(false);
        this.mainGUI.body_temp_table.removeAll();
        this.mainGUI.body_temp_table.add(this.panelTemperature.display_chart);
        this.mainGUI.body_temp_table.setVisible(true);

        this.mainGUI.temp_display_value.setVisible(false);
        this.mainGUI.temp_display_value.removeAll();
        this.mainGUI.temp_display_value.add(this.panelTemperature.value_label);
        this.mainGUI.temp_display_value.setVisible(true);
        // heart rate
        this.mainGUI.heartrate_table.setVisible(false);
        this.mainGUI.heartrate_table.removeAll();
        this.mainGUI.heartrate_table.add(this.panelHeartRate.display_chart);
        this.mainGUI.heartrate_table.setVisible(true);

        // resp pattern
        this.mainGUI.resp_pattern_table.setVisible(false);
        this.mainGUI.resp_pattern_table.removeAll();
        this.mainGUI.resp_pattern_table.add(this.panelRespiratoryPattern.display_chart);
        this.mainGUI.resp_pattern_table.setVisible(true);

        // resp rate
        this.mainGUI.resp_rate_table.setVisible(false);
        this.mainGUI.resp_rate_table.removeAll();
        this.mainGUI.resp_rate_table.add(this.panelRespiratoryRate.display_chart);
        this.mainGUI.resp_rate_table.setVisible(true);

        // bp_sys
        this.mainGUI.sys_table.setVisible(false);
        this.mainGUI.sys_table.removeAll();
        this.mainGUI.sys_table.add(this.panelSysBloodPressure.display_chart);
        this.mainGUI.sys_table.setVisible(true);
        // bp_dia
        this.mainGUI.dia_table.setVisible(false);
        this.mainGUI.dia_table.removeAll();
        this.mainGUI.dia_table.add(this.panelDiaBloodPressure.display_chart);
        this.mainGUI.dia_table.setVisible(true);
        // set the default length
        this.mainGUI.ECG_display_interval.setText(String.valueOf(this.ecg_interval));
        this.mainGUI.Temp_display_interval.setText(String.valueOf(this.temperature_interval));
    }


    public void patient_mouseClicked(MouseEvent e) {
//        if(e.getClickCount()==2){
//            Patient_Editor editor = new Patient_Editor(this.mainGUI, this);
//            editor.setVisible(true);
//        }
    }
}
