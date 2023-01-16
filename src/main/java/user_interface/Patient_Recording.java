package user_interface;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.swing.*;

import chartPanel.Recording_Panel;
import chartPanel.inputData;
import com.toedter.calendar.*;
import master.Patient;
import net.miginfocom.swing.*;
import netRelated.netAction;
import netRelated.responsePack;
/*
 * Created by JFormDesigner on Thu Dec 29 18:21:53 GMT 2022
 */



/**
 * this is a JFrame using for displaying the selected patient's vital signs' recordings
 */
public class Patient_Recording extends JFrame {
    // the mainGUI it corresponding to
    private GUI_test mainGUI;
    // selected patient
    private Patient current_patient;
    // the recording start and end time
    private long start_in_milli;
    private long end_in_milli;

    /**
     *  this is a JFrame using for displaying the selected patient's vital signs' recordings
     * @param mainGUI the mainGUI it corresponding to
     * @param current_patient the recording start and end time
     */
    public Patient_Recording(GUI_test mainGUI, Patient current_patient) {
        initComponents();
        this.mainGUI = mainGUI;
        this.current_patient = current_patient;
    }

    /**
     * when the recording window closed, the recording button on mainGUI should be set back to enabled
     */
    private void PatientRecordingWindowClosing(WindowEvent e) {
        this.mainGUI.recordings.setEnabled(true);
    }

    /**
     * transform the selected signal from JComboBox into the formatted type of signal used as input for Recording_Panel
     * @param signal_pack_select the selected signal from JComboBox
     * @return the formatted type of signal used as input for Recording_Panel
     */
    private String find_data_type(String signal_pack_select) {
        if (Objects.equals(signal_pack_select, "ECG Lead I")) {
            return "ecg1";
        } else if (signal_pack_select == "ECG lead II") {
            return "ecg2";
        } else if (signal_pack_select =="Respiratory Pattern") {
            return "resp";
        } else if (signal_pack_select == "Heart Rate") {
            return "heart rate";
        } else if (signal_pack_select =="Respiratory Rate") {
            return "respiratory rate";
        } else if (signal_pack_select == "Systolic Blood Pressure") {
            return "systolic blood pressure";
        } else if (signal_pack_select == "Diastolic Blood Pressure") {
            return "diastolic blood pressure";
        } else if (signal_pack_select == "Body Temperature") {
            return "body temperature";
        }
        return signal_pack_select;
    }

    /**
     *  this is a input key filter for entering the start hour and min
     *  the generate button is only enabled when start hour/min is smaller than end hour
     *  and the date is chosen
     */
    private void time_value_check_click(){
        try {
            Integer start_hour = Integer.parseInt((String) start_time_hour.getSelectedItem());
            Integer start_min = Integer.parseInt((String) start_time_min.getSelectedItem());
            Integer end_hour = Integer.parseInt((String)end_time_hour.getSelectedItem());
            Integer end_min = Integer.parseInt((String) end_time_min.getSelectedItem());
            // only when start hour/min is smaller than end hour and the date is chosen the button is enabled
            if (((start_hour<end_hour) | ((start_hour==end_hour) & (start_min<end_min)))& (dateChooser1.getDate()!=null)){
                generate_button.setEnabled(true);
            }else{
                generate_button.setEnabled(false);
            }
        }catch(Exception error) {
            generate_button.setEnabled(false);
        }
    }

    /**
     * key enter and date chosen check
     */
    private void start_time_hour_click(ActionEvent e) {
        time_value_check_click();
    }
    /**
     * key enter and date chosen check
     */
    private void start_time_min_click(ActionEvent e) {
        time_value_check_click();
    }
    /**
     * key enter and date chosen check
     */
    private void end_time_hour_click(ActionEvent e) {
        time_value_check_click();
    }
    /**
     * key enter and date chosen check
     */
    private void end_time_min_click(ActionEvent e) {
        time_value_check_click();
    }

    /**
     * when generate button is clicked, the recording will be displayed via creating new Recording_Display()
     */
    private void generate_button(ActionEvent e)  {
        String Date = String.valueOf(dateChooser1.getDate());

        // get the start and end hour/min based on user's choice and cast them into string
        String start_hour = (String) start_time_hour.getSelectedItem();
        String start_min = (String) start_time_min.getSelectedItem();
        String end_hour = (String) end_time_hour.getSelectedItem();
        String end_min = (String) end_time_min.getSelectedItem();

        // putting into the correct format
        String start_time = start_hour + ":" + start_min;
        String end_time = end_hour + ":" + end_min;

        String[] tokens = Date.split(" ");
        String Date_selected = tokens[2]+"-"+tokens[1]+"-"+tokens[5];
        String start_time_date = tokens[2]+"-"+tokens[1]+"-"+tokens[5]+" "+ start_time + ":"+ "00";
        String end_time_date = tokens[2]+"-"+tokens[1]+"-"+tokens[5]+" "+ end_time + ":"+ "00";

        // set the text showed in the display window
        String signal_pack_select = (String) signal_selector.getSelectedItem();
        String type = find_data_type(signal_pack_select);
        Recording_Display display = new Recording_Display();
        display.Patient_name.setText(this.current_patient.first_name + " " + this.current_patient.last_name);
        display.signal_type.setText(signal_pack_select);
        display.start_time.setText(start_time);
        display.end_time.setText(end_time);
        display.Date.setText(Date_selected);


        // get time in milliseconds using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date start_date=null;
        Date end_date=null;
        try {
            start_date = sdf.parse(start_time_date);
            end_date = sdf.parse(end_time_date);
        }catch (Exception ignore){}
        start_in_milli = start_date.getTime();
        end_in_milli = end_date.getTime();

        long dataBaseInitialTime=netAction.getInitialTime(current_patient.reference_value);
        //get interval
        Integer interval;
        Double period = 1.0;
        responsePack respPack;
        if (type=="ecg1"|type=="ecg2"|type=="resp") {
            interval = 2;
            period = 0.002;
            respPack =netAction.recordData(start_in_milli,
                    end_in_milli,
                    dataBaseInitialTime,
                    type,interval,this.current_patient.reference_value);
        }else {
            respPack =netAction.averageData(start_in_milli,
                    end_in_milli,
                    dataBaseInitialTime,
                    type,this.current_patient.reference_value);
        }
        display.setVisible(true);
        Recording_Panel recordingPanel = new Recording_Panel(new inputData(respPack.valueList,dataBaseInitialTime,
                period), respPack.lastTime,type,signal_pack_select,"recording");
        recordingPanel.setVisible(true);
        display.A1.add(recordingPanel);
    }


    /**
     * when the patient recording window is opened, the current time and the recording start time from server
     * are found to limit doctor's choice on recording.
     */
    private void thisWindowOpened(WindowEvent e) {
        dateChooser1.setMinSelectableDate(new Date(netAction.getInitialTime(current_patient.reference_value)));
        dateChooser1.setMaxSelectableDate(new Timestamp(System.currentTimeMillis()));
        dateChooser1.setDate(new Date(netAction.getInitialTime(current_patient.reference_value)));
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license 
        recording_title = new JLabel();
        signal_select_label = new JLabel();
        String[] signal_pack = new String[8];
        signal_pack[0] = "ECG Lead I";
        signal_pack[1] = "ECG lead II";
        signal_pack[2] = "Respiratory Pattern";
        signal_pack[3] = "Heart Rate";
        signal_pack[4] = "Respiratory Rate";
        signal_pack[5] = "Systolic Blood Pressure";
        signal_pack[6] = "Diastolic Blood Pressure";
        signal_pack[7] = "Body Temperature";
        signal_selector = new JComboBox(signal_pack);
        blank2 = new JLabel();
        blank = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        dateChooser1 = new JDateChooser();
        time_intervel_select_panel = new JPanel();
        start_time_label = new JLabel();
        String[] hour_pack = new String[24];
        for (int i = 0; i<24; i++) {
         if (i<10){
                hour_pack[i] = "0"+i;
            }else{
                hour_pack[i] = String.valueOf(i);
            }
        }
        start_time_hour = new JComboBox(hour_pack);
        colon = new JLabel();
        String[] min_pack = new String[60];
        for (int i = 0; i<60; i++) {
            if (i<10){
                min_pack[i] = "0"+i;
            }else{
                min_pack[i] = String.valueOf(i);
            }
          }
        start_time_min = new JComboBox(min_pack);
        end_time_label = new JLabel();
        end_time_hour = new JComboBox(hour_pack);
        colon2 = new JLabel();
        end_time_min = new JComboBox(min_pack);
        panel2 = new JPanel();
        label3 = new JLabel();
        label2 = new JLabel();
        generate_button = new JButton();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 8));
        setPreferredSize(new Dimension(500, 300));
        setMinimumSize(new Dimension(500, 300));
        setMaximumSize(new Dimension(500, 300));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PatientRecordingWindowClosing(e);
            }
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[506,fill]",
            // rows
            "[53]" +
            "[29]" +
            "[34]" +
            "[35]" +
            "[]" +
            "[19]" +
            "[54]" +
            "[]"));

        //---- recording_title ----
        recording_title.setText(" Recordings");
        recording_title.setFont(new Font("Arial", Font.PLAIN, 18));
        contentPane.add(recording_title, "cell 0 0");

        //---- signal_select_label ----
        signal_select_label.setText("  Please Select a Signal:");
        signal_select_label.setFont(new Font("Arial", Font.PLAIN, 14));
        signal_select_label.setPreferredSize(new Dimension(180, 22));
        signal_select_label.setMinimumSize(new Dimension(180, 22));
        signal_select_label.setMaximumSize(new Dimension(180, 22));
        signal_select_label.setIconTextGap(0);
        contentPane.add(signal_select_label, "cell 0 1,alignx left,growx 0");

        //---- signal_selector ----
        signal_selector.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(signal_selector, "cell 0 1");
        contentPane.add(blank2, "cell 0 1");
        contentPane.add(blank, "cell 0 1");

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[113,fill]" +
                "[158,fill]",
                // rows
                "[]"));

            //---- label1 ----
            label1.setText("Choose the Date:");
            label1.setFont(new Font("Arial", Font.PLAIN, 14));
            panel1.add(label1, "cell 0 0");
            panel1.add(dateChooser1, "cell 1 0");
        }
        contentPane.add(panel1, "cell 0 2,align left center,grow 0 0");

        //======== time_intervel_select_panel ========
        {
            time_intervel_select_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[62,fill]" +
                "[47,fill]0" +
                "[0,fill]0" +
                "[39,fill]" +
                "[60,fill]" +
                "[42,fill]0" +
                "[0,fill]0" +
                "[48,fill]" +
                "[fill]",
                // rows
                "[46]0" +
                "[]"));

            //---- start_time_label ----
            start_time_label.setText("Start Time:");
            start_time_label.setFont(new Font("Arial", Font.PLAIN, 14));
            time_intervel_select_panel.add(start_time_label, "cell 0 0");

            //---- start_time_hour ----
            start_time_hour.setMinimumSize(new Dimension(50, 30));
            start_time_hour.setPreferredSize(new Dimension(50, 30));
            start_time_hour.setMaximumSize(new Dimension(50, 30));
            start_time_hour.addActionListener(e -> start_time_hour_click(e));
            time_intervel_select_panel.add(start_time_hour, "cell 1 0");

            //---- colon ----
            colon.setText(":");
            time_intervel_select_panel.add(colon, "cell 2 0");

            //---- start_time_min ----
            start_time_min.setMinimumSize(new Dimension(50, 30));
            start_time_min.setPreferredSize(new Dimension(50, 30));
            start_time_min.setMaximumSize(new Dimension(50, 30));
            start_time_min.addActionListener(e -> start_time_min_click(e));
            time_intervel_select_panel.add(start_time_min, "cell 3 0");

            //---- end_time_label ----
            end_time_label.setText("End Time:");
            end_time_label.setFont(new Font("Arial", Font.PLAIN, 14));
            time_intervel_select_panel.add(end_time_label, "cell 4 0");

            //---- end_time_hour ----
            end_time_hour.setMinimumSize(new Dimension(50, 30));
            end_time_hour.setPreferredSize(new Dimension(50, 30));
            end_time_hour.setMaximumSize(new Dimension(50, 30));
            end_time_hour.addActionListener(e -> end_time_hour_click(e));
            time_intervel_select_panel.add(end_time_hour, "cell 5 0");

            //---- colon2 ----
            colon2.setText(":");
            time_intervel_select_panel.add(colon2, "cell 6 0");

            //---- end_time_min ----
            end_time_min.setMinimumSize(new Dimension(50, 30));
            end_time_min.setPreferredSize(new Dimension(50, 30));
            end_time_min.setMaximumSize(new Dimension(50, 30));
            end_time_min.addActionListener(e -> end_time_min_click(e));
            time_intervel_select_panel.add(end_time_min, "cell 7 0");

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]",
                    // rows
                    "[]"));
            }
            time_intervel_select_panel.add(panel2, "cell 8 0");
        }
        contentPane.add(time_intervel_select_panel, "cell 0 3,alignx left,growx 0");

        //---- label3 ----
        label3.setText("   * For ECG and respiratory pattern, the recommend time interval is 1 minute");
        label3.setForeground(new Color(0xff0033));
        label3.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(label3, "cell 0 4,alignx left,growx 0");

        //---- label2 ----
        label2.setText("   * For the rest of signals, the minimal displable interval for their averages is 2 minutes");
        label2.setForeground(new Color(0xff0033));
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(label2, "cell 0 5,alignx left,growx 0");

        //---- generate_button ----
        generate_button.setText("Generate");
        generate_button.setEnabled(false);
        generate_button.setFont(new Font("Arial", Font.PLAIN, 12));
        generate_button.addActionListener(e -> generate_button(e));
        contentPane.add(generate_button, "cell 0 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license 
    private JLabel recording_title;
    private JLabel signal_select_label;
    public JComboBox signal_selector;
    private JLabel blank2;
    private JLabel blank;
    private JPanel panel1;
    private JLabel label1;
    private JDateChooser dateChooser1;
    private JPanel time_intervel_select_panel;
    private JLabel start_time_label;
    public JComboBox start_time_hour;
    private JLabel colon;
    public JComboBox start_time_min;
    private JLabel end_time_label;
    public JComboBox end_time_hour;
    private JLabel colon2;
    public JComboBox end_time_min;
    private JPanel panel2;
    private JLabel label3;
    private JLabel label2;
    private JButton generate_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
