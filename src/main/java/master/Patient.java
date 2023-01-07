package master;

import Interface.GUI_test;
import Interface.Patient_Editor;
import chartPanel.Chart_Label_Display;
import chartPanel.SeriesChartPane;
import chartPanel.inputData;
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
    public Chart_Label_Display panelTemperature;

    public Chart_Label_Display panelHeartRate;

    public Chart_Label_Display panelSysBloodPressure;

    public Chart_Label_Display panelDiaBloodPressure;

    public Chart_Label_Display panelRespiratoryRate;

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
        panelEcg1 = load_chart((long) floor(ecg_interval*1000),"ecg1");
        panelTemperature=load_chartLabel((long) floor(temperature_interval*1000*60),"body temperature");

        display(this.reference_value);
        this.addActionListener(e -> switch_patient(e));
        this.time_milli = time.getTime();
    }

    private void switch_patient(ActionEvent e) {

        display(this.reference_value);
        Timestamp time_now = new Timestamp(System.currentTimeMillis());
        if ((time_now.getTime()-time_milli)<=300){

            this.setEnabled(false);
            Patient_Editor editor = new Patient_Editor(this.mainGUI, this);
            editor.setVisible(true);
        }
        this.time_milli = time_now.getTime();
    }

    public SeriesChartPane load_chart(long chart_capacity, String type){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime();
        responsePack respPack =netAction.recordData(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime);
        return new SeriesChartPane(new inputData(respPack.valueList,dataBaseInitialTime),respPack.lastTime,type,this);
    }

    public Chart_Label_Display load_chartLabel(long chart_capacity,String type){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long dataBaseInitialTime=netAction.getInitialTime();
        responsePack respPack =netAction.recordDataTemp(timestamp.getTime()-chart_capacity,
                timestamp.getTime(),
                dataBaseInitialTime);
        return new Chart_Label_Display(this,new inputData(respPack.valueList,dataBaseInitialTime),respPack.lastTime,type);
    }

    private void display(String reference_value) {

        this.mainGUI.ecg1.setVisible(false);
        this.mainGUI.ecg1.removeAll();
        this.mainGUI.ecg1.add(this.panelEcg1);
        this.mainGUI.ecg1.setVisible(true);

        this.mainGUI.ecg2.setVisible(false);
        this.mainGUI.ecg2.removeAll();
        this.mainGUI.ecg2.add(this.panelTemperature.display_chart);
        this.mainGUI.ecg2.setVisible(true);

        this.mainGUI.temp_display_value.setVisible(false);
        this.mainGUI.temp_display_value.removeAll();
        this.mainGUI.temp_display_value.add(this.panelTemperature.value_label);
        this.mainGUI.temp_display_value.setVisible(true);

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
