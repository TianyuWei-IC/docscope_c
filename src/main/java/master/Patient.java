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
    public SeriesChartPane panelEcg1;
    public SeriesChartPane panelEcg2;
    public Chart_Label_Display panelTemperature;

    public Chart_Label_Display panelHeartRate;

    public Chart_Label_Display panelSysBloodPressure;

    public Chart_Label_Display panelDiaBloodPressure;

    public Chart_Label_Display panelRespiratoryRate;

    public SeriesChartPane panelRespiratoryPattern;

    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private long time_milli;

    // fields for time interval

    public Integer ecg_interval = 5;
    public Integer temperature_interval = 6;
    public Integer resp_pattern_interval = 6;
    public Integer resp_rate_interval = 6;
    public Integer hr_interval = 6;
    public Integer bp_interval = 6;

    /**
     * Patient object represent one patient, it contains all relevant information of the patients. Inherent from JButton
     * so that it's clickable which enables switching and editing of the patients.
     * (NOTE: for min/max values, e.g. temp_min,these correspond to the values that would pop the urgent notification
     * once reached, defined by users.)
     */
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
        // this is the default color of the patient as a JButton
        this.setBackground(new Color(193, 211, 224));
        this.setOpaque(true);
        this.setBorderPainted(false);

        String patient_full_name = this.first_name+" "+this.last_name;
        // these lines of code help with handling long string in JButton, so that patient name can be displayed properly
        this.setText("<html>" + patient_full_name.replaceAll("<break>", "<br>") + "</html>");
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setMaximumSize(new Dimension(170,100));
        this.setPreferredSize(new Dimension(170,100));
        this.setMinimumSize(new Dimension(170,100));
        this.setHorizontalAlignment(SwingConstants.CENTER);

        // creation of all the signal display for the patient
        panelEcg1 = load_chart((long) floor(ecg_interval*1000),"ecg1","ECG_Lead_I","real time");
        panelEcg2 = load_chart((long) floor(ecg_interval * 1000), "ecg2", "ECG_Lead_II","real time");
        panelRespiratoryPattern = load_chart((long) floor(resp_pattern_interval*1000),"resp","Respiratory Pattern","real time");
        panelTemperature=load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature","Body Temperature");
        panelHeartRate=load_chartLabel((long) floor(hr_interval*1000*60),"heart rate","Heart Rate");
        panelRespiratoryRate=load_chartLabel((long) floor(resp_rate_interval*1000*60),"respiratory rate","Respiratory Rate");
        panelDiaBloodPressure = load_chartLabel((long) floor(bp_interval*1000*60),"diastolic blood pressure","Diastolic Blood Pressure");
        panelSysBloodPressure = load_chartLabel((long) floor(bp_interval*1000*60),"systolic blood pressure","Systolic Blood Pressure");

        display(this.reference_value);
        this.addActionListener(e -> switch_patient(e));
        this.time_milli = time.getTime();
    }

    /**
     *
     *
     */
    public void switch_patient(ActionEvent e) {
        // find the previous patients displaying on the MainGUI
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
            previous_patient.panelRespiratoryPattern.worker.cancel(true);
            previous_patient.panelHeartRate.updater.cancel(true);
            System.out.println(previous_patient.panelEcg1.worker.isCancelled());
            previous_patient.setBackground(new Color(193, 211, 224));
            previous_patient.setOpaque(true);
            previous_patient.setBorderPainted(false);
        }

        display(this.reference_value);
        Timestamp time_now = new Timestamp(System.currentTimeMillis());
        if ((time_now.getTime()-time_milli)<=300){
            this.setEnabled(false);
            Patient_Editor editor = new Patient_Editor(this.mainGUI, this);
            editor.setVisible(true);
        }
        this.setBackground(new Color(84, 160, 173));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.time_milli = time_now.getTime();
    }

    /**
     *
     * @param chart_capacity
     * @param type
     * @param title
     * @param mode
     * @return
     */
    public SeriesChartPane load_chart(long chart_capacity, String type, String title,String mode){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime(this.reference_value);
        responsePack respPack =netAction.recordData(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime,
                type,2,this.reference_value);
        return new SeriesChartPane(new inputData(respPack.valueList,dataBaseInitialTime,0.002),respPack.lastTime,type,this,title,mode);
    }

    /**
     *
     * @param chart_capacity
     * @param type
     * @param title
     * @return
     */
    public Chart_Label_Display load_chartLabel(long chart_capacity,String type, String title){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime(this.reference_value);
        responsePack respPack =netAction.recordData(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime,
                type,1000,this.reference_value);
        return new Chart_Label_Display(this,new inputData(respPack.valueList,dataBaseInitialTime,0.0166667),respPack.lastTime,type,title);
    }

    /**
     * This function display all the vital signals of the patient to the corresponding slots on the MainGUI
     *
     */
    private void display(String reference_value) {
        // if the updater/worker of the patient is cancelled, we create a new updater/worker for it.
        // when switching between the patient on the MainGUI by clicking, the previous patient's updater/worker will be
        // cancelled
        if (this.panelEcg1.worker.isCancelled()) {
            this.panelEcg1.worker = new UpdateWorker(this.panelEcg1);
            this.panelEcg1.worker.execute();

            this.panelEcg2.worker = new UpdateWorker(this.panelEcg2);
            this.panelEcg2.worker.execute();

            this.panelTemperature.updater = new Chart_Label_Update(this.panelTemperature);
            this.panelTemperature.updater.execute();

            this.panelHeartRate.updater = new Chart_Label_Update(this.panelHeartRate);
            this.panelHeartRate.updater.execute();

            this.panelRespiratoryPattern.worker = new UpdateWorker(this.panelRespiratoryPattern);
            this.panelRespiratoryPattern.worker.execute();

            this.panelRespiratoryRate.updater = new Chart_Label_Update(this.panelRespiratoryRate);
            this.panelRespiratoryRate.updater.execute();

            this.panelSysBloodPressure.updater = new Chart_Label_Update(this.panelSysBloodPressure);
            this.panelSysBloodPressure.updater.execute();

            this.panelDiaBloodPressure.updater = new Chart_Label_Update(this.panelDiaBloodPressure);
            this.panelDiaBloodPressure.updater.execute();
        }

        //
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

        this.mainGUI.hr_display_value.setVisible(false);
        this.mainGUI.hr_display_value.removeAll();
        this.mainGUI.hr_display_value.add(this.panelHeartRate.value_label);
        this.mainGUI.hr_display_value.setVisible(true);
        // resp pattern
        this.mainGUI.resp_pattern_table.setVisible(false);
        this.mainGUI.resp_pattern_table.removeAll();
        this.mainGUI.resp_pattern_table.add(this.panelRespiratoryPattern);
        this.mainGUI.resp_pattern_table.setVisible(true);

        // resp rate
        this.mainGUI.resp_rate_table.setVisible(false);
        this.mainGUI.resp_rate_table.removeAll();
        this.mainGUI.resp_rate_table.add(this.panelRespiratoryRate.display_chart);
        this.mainGUI.resp_rate_table.setVisible(true);

        this.mainGUI.resp_display_value.setVisible(false);
        this.mainGUI.resp_display_value.removeAll();
        this.mainGUI.resp_display_value.add(this.panelRespiratoryRate.value_label);
        this.mainGUI.resp_display_value.setVisible(true);

        // bp_sys
        this.mainGUI.sys_table.setVisible(false);
        this.mainGUI.sys_table.removeAll();
        this.mainGUI.sys_table.add(this.panelSysBloodPressure.display_chart);
        this.mainGUI.sys_table.setVisible(true);

        this.mainGUI.sys_display_value.setVisible(false);
        this.mainGUI.sys_display_value.removeAll();
        this.mainGUI.sys_display_value.add(this.panelSysBloodPressure.value_label);
        this.mainGUI.sys_display_value.setVisible(true);

        // bp_dia
        this.mainGUI.dia_table.setVisible(false);
        this.mainGUI.dia_table.removeAll();
        this.mainGUI.dia_table.add(this.panelDiaBloodPressure.display_chart);
        this.mainGUI.dia_table.setVisible(true);

        this.mainGUI.dia_display_value.setVisible(false);
        this.mainGUI.dia_display_value.removeAll();
        this.mainGUI.dia_display_value.add(this.panelDiaBloodPressure.value_label);
        this.mainGUI.dia_display_value.setVisible(true);

        // set the default signal display length
        this.mainGUI.ECG_display_interval.setText(String.valueOf(this.ecg_interval));
        this.mainGUI.Temp_display_interval.setText(String.valueOf(this.temperature_interval));
        this.mainGUI.RESP_rate_display_interval.setText(String.valueOf(this.resp_rate_interval));
        this.mainGUI.RESP_pattern_display_interval.setText(String.valueOf(this.resp_pattern_interval));
        this.mainGUI.BP_display_interval.setText(String.valueOf(this.bp_interval));
        this.mainGUI.HR_display_interval.setText(String.valueOf(this.hr_interval));
    }

}
