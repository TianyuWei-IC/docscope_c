package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import chartPanel.Chart_Label_Display;
import chartPanel.Display_Chart;
import chartPanel.SeriesChartPane;
import net.miginfocom.swing.*;
import master.*;
import static java.lang.Math.floor;

/*
 * Created by JFormDesigner on Mon Dec 26 17:36:59 GMT 2022
 */



/**
 * @author Tianyu
 */
public class GUI_test extends JFrame {
    public GUI_test() {
        initComponents();
    }

    private void real_time_plots(ActionEvent e) {
        // TODO add your code here
    }

    private void recordings(ActionEvent e) {
        Display_Chart current_Temp = (Display_Chart) this.body_temp_table.getComponent(0);
        Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();
        Patient current_patient = current_temp_cl_display.patient;
        System.out.println(current_patient.first_name);

        Patient_Recording recorder = new Patient_Recording(this,current_patient);
        recorder.setVisible(true);
        this.recordings.setEnabled(false);
    }


    private void add_new_patient(ActionEvent e) {
        Patient_Adder editor = new Patient_Adder(this);
        this.add_new_patient.setEnabled(false);
        editor.setVisible(true);


    }

    private void report_button(ActionEvent e) {
        // TODO add your code here
    }

    private void Temp_update_button(ActionEvent e) {
        // get the chart in the temp display panel
        Display_Chart current_Temp = (Display_Chart) this.body_temp_table.getComponent(0);
        Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();

        Patient current_patient = current_temp_cl_display.patient;

        current_patient.panelTemperature.updater.cancel(true);
        current_temp_cl_display.updater.cancel(true);

        current_patient.temperature_interval = Double.valueOf(this.Temp_display_interval.getText());
        Chart_Label_Display updated_temp_cl_display =  current_patient.load_chartLabel(
                (long) floor(current_patient.temperature_interval*1000*60),"body temperature", "Body Temperature");
        this.body_temp_table.removeAll();
        this.body_temp_table.add(updated_temp_cl_display.display_chart);
        this.temp_display_value.removeAll();
        this.temp_display_value.add(updated_temp_cl_display.value_label);

        current_patient.panelTemperature = updated_temp_cl_display;
    }

    private void ECG_update_button(ActionEvent e) {
        // get the chart in the ecg panel
        SeriesChartPane current_ECG_I = (SeriesChartPane) this.ecg1.getComponent(0);
        SeriesChartPane current_ECG_II = (SeriesChartPane) this.ecg2.getComponent(0);
        Patient current_patient = current_ECG_I.find_patient();
        current_patient.ecg_interval = Double.valueOf(this.ECG_display_interval.getText());

        current_patient.panelEcg1.worker.cancel(true);
        current_patient.panelEcg2.worker.cancel(true);
        current_ECG_I.worker.cancel(true);
        current_ECG_II.worker.cancel(true);
        SeriesChartPane updated_ECG_I =  current_patient.load_chart(
                (long) floor(current_patient.ecg_interval*1000),"ecg1","ECG_Lead_I");
        SeriesChartPane updated_ECG_II =  current_patient.load_chart(
                (long) floor(current_patient.ecg_interval*1000),"ecg2","ECG_Lead_II");
        this.ecg1.removeAll();
        this.ecg2.removeAll();
        this.ecg1.add(updated_ECG_I);
        this.ecg2.add(updated_ECG_II);

        current_patient.panelEcg1 = updated_ECG_I;
        current_patient.panelEcg2 = updated_ECG_II;

    }

    private void RESP_pattern_update_button(ActionEvent e) {
        SeriesChartPane current_RESP_pattern = (SeriesChartPane) this.resp_pattern_table.getComponent(0);
        Patient current_patient = current_RESP_pattern.find_patient();


        current_patient.panelRespiratoryPattern.worker.cancel(true);
        current_RESP_pattern.worker.cancel(true);

        current_patient.resp_pattern_interval = Double.valueOf(this.RESP_pattern_display_interval.getText());
        SeriesChartPane updated_resp_pattern =  current_patient.load_chart(
                (long) floor(current_patient.ecg_interval*1000),"resp pattern","Respiratory Pattern");
        this.resp_pattern_table.removeAll();
        this.resp_pattern_table.add(updated_resp_pattern);

        current_patient.panelRespiratoryPattern = updated_resp_pattern;
    }

    private void HR_update_button(ActionEvent e) {
        Display_Chart current_HR = (Display_Chart) this.heartrate_table.getComponent(0);
        Chart_Label_Display current_hr_cl_display = current_HR.find_cl_display();

        Patient current_patient = current_hr_cl_display.patient;

        current_patient.panelHeartRate.updater.cancel(true);
        current_hr_cl_display.updater.cancel(true);

        current_patient.hr_interval = Double.valueOf(this.HR_display_interval.getText());
        Chart_Label_Display updated_hr_cl_display =  current_patient.load_chartLabel(
                (long) floor(current_patient.hr_interval*1000*60),"body temperature", "Heart Rate");
        this.heartrate_table.removeAll();
        this.heartrate_table.add(updated_hr_cl_display.display_chart);
        this.hr_display_value.removeAll();
        this.hr_display_value.add(updated_hr_cl_display.value_label);

        current_patient.panelHeartRate = updated_hr_cl_display;

    }

    private void RESP_rate_update_button(ActionEvent e) {
        Display_Chart current_RESP_rate = (Display_Chart) this.resp_rate_table.getComponent(0);
        Chart_Label_Display current_resp_rate_cl_display = current_RESP_rate.find_cl_display();

        Patient current_patient = current_resp_rate_cl_display.patient;

        current_patient.panelRespiratoryRate.updater.cancel(true);
        current_resp_rate_cl_display.updater.cancel(true);

        current_patient.resp_rate_interval = Double.valueOf(this.RESP_rate_display_interval.getText());
        Chart_Label_Display updated_resp_rate_cl_display =  current_patient.load_chartLabel(
                (long) floor(current_patient.resp_rate_interval*1000*60),"body temperature", "Respiratory Rate");
        this.resp_rate_table.removeAll();
        this.resp_rate_table.add(updated_resp_rate_cl_display.display_chart);

        current_patient.panelRespiratoryRate = updated_resp_rate_cl_display;
    }

    private void BP_update_button(ActionEvent e) {
        Display_Chart current_BP_dia = (Display_Chart) this.dia_table.getComponent(0);
        Display_Chart current_BP_sys = (Display_Chart) this.sys_table.getComponent(0);
        Chart_Label_Display current_bp_dia_cl_display = current_BP_dia.find_cl_display();
        Chart_Label_Display current_bp_sys_cl_display = current_BP_sys.find_cl_display();

        Patient current_patient = current_bp_dia_cl_display.patient;

        current_patient.panelDiaBloodPressure.updater.cancel(true);
        current_patient.panelSysBloodPressure.updater.cancel(true);
        current_bp_dia_cl_display.updater.cancel(true);
        current_bp_sys_cl_display.updater.cancel(true);

        current_patient.bp_interval = Double.valueOf(this.BP_display_interval.getText());
        Chart_Label_Display updated_bp_dia_cl_display =  current_patient.load_chartLabel(
                (long) floor(current_patient.bp_interval*1000*60),"body temperature", "Diastolic Blood Pressure");
        Chart_Label_Display updated_bp_sys_cl_display =  current_patient.load_chartLabel(
                (long) floor(current_patient.bp_interval*1000*60),"body temperature", "Systolic Blood Pressure");

        this.dia_table.removeAll();
        this.dia_table.add(updated_bp_dia_cl_display.display_chart);
        this.dia_display_value.removeAll();
        this.dia_display_value.add(updated_bp_dia_cl_display.value_label);

        this.sys_table.removeAll();
        this.sys_table.add(updated_bp_sys_cl_display.display_chart);
        this.sys_display_value.removeAll();
        this.sys_display_value.add(updated_bp_sys_cl_display.value_label);

        current_patient.panelDiaBloodPressure = updated_bp_dia_cl_display;
        current_patient.panelSysBloodPressure = updated_bp_sys_cl_display;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        patientList_recordings = new JPanel();
        NewPatient = new JPanel();
        label1 = new JLabel();
        add_new_patient = new JButton();
        patient = new JScrollPane();
        patient_list = new JPanel();
        panel2 = new JPanel();
        recordings = new JButton();
        recordings.setOpaque(true);
        recordings.setBorderPainted(false);
        value_display = new JPanel();
        ecg_interval_panel = new JPanel();
        label12 = new JLabel();
        panel8 = new JPanel();
        label10 = new JLabel();
        ECG_display_interval = new JTextField();
        label11 = new JLabel();
        ECG_update_button = new JButton();
        resp_interval_panel = new JPanel();
        label16 = new JLabel();
        panel12 = new JPanel();
        label17 = new JLabel();
        RESP_pattern_display_interval = new JTextField();
        label18 = new JLabel();
        RESP_pattern_update_button = new JButton();
        hr_panel = new JPanel();
        hr_title = new JLabel();
        hr_display = new JPanel();
        hr_display_value = new JPanel();
        hr_unit = new JLabel();
        panel3 = new JPanel();
        label2 = new JLabel();
        HR_display_interval = new JTextField();
        label3 = new JLabel();
        HR_update_button = new JButton();
        resp_panel = new JPanel();
        resp_title = new JLabel();
        resp_display = new JPanel();
        resp_display_value = new JPanel();
        resp_unit = new JLabel();
        panel4 = new JPanel();
        panel13 = new JPanel();
        label19 = new JLabel();
        RESP_rate_display_interval = new JTextField();
        label20 = new JLabel();
        RESP_rate_update_button = new JButton();
        bp_panel = new JPanel();
        bp_title = new JLabel();
        bp_display = new JPanel();
        bp_values = new JPanel();
        sys_display_value = new JPanel();
        slash = new JLabel();
        dia_display_value = new JPanel();
        bp_unit = new JLabel();
        panel5 = new JPanel();
        panel14 = new JPanel();
        label4 = new JLabel();
        BP_display_interval = new JTextField();
        label5 = new JLabel();
        BP_update_button = new JButton();
        temp_panel = new JPanel();
        temp_title = new JLabel();
        temp_display = new JPanel();
        temp_display_value = new JPanel();
        temp_unit = new JLabel();
        panel6 = new JPanel();
        panel15 = new JPanel();
        label6 = new JLabel();
        Temp_display_interval = new JTextField();
        label7 = new JLabel();
        Temp_update_button = new JButton();
        report_button = new JButton();
        report_button.setOpaque(true);
        report_button.setBorderPainted(false);
        plotPanel = new JPanel();
        ecg_table = new JPanel();
        ecg1 = new JPanel();
        ecg2 = new JPanel();
        char_label_panel = new JPanel();
        resp_pattern_table = new JPanel();
        resp_rate_table = new JPanel();
        sys_table = new JPanel();
        dia_table = new JPanel();
        body_temp_table = new JPanel();
        heartrate_table = new JPanel();

        //======== this ========
        setBackground(new Color(0x54a0ad));
        setForeground(new Color(0x54a0ad));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== patientList_recordings ========
        {
            patientList_recordings.setBackground(new Color(0x54a0ad));
            patientList_recordings.setForeground(Color.white);
            patientList_recordings.setLayout(new MigLayout(
                "hidemode 3,gap 100 10",
                // columns
                "[207,fill]",
                // rows
                "[412]" +
                "[]" +
                "[972]" +
                "[300]"));

            //======== NewPatient ========
            {
                NewPatient.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[204,fill]",
                    // rows
                    "[128]" +
                    "[49]"));

                //---- label1 ----
                label1.setText("Patient List");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 6f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setVerticalAlignment(SwingConstants.TOP);
                NewPatient.add(label1, "cell 0 0");

                //---- add_new_patient ----
                add_new_patient.setText("Add a New Patient");
                add_new_patient.addActionListener(e -> add_new_patient(e));
                NewPatient.add(add_new_patient, "cell 0 1");
            }
            patientList_recordings.add(NewPatient, "cell 0 0,grow,gapx 20 20,gapy 20 0");

            //======== patient ========
            {
                patient.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

                //======== patient_list ========
                {
                    patient_list.setLayout(new BoxLayout(patient_list, BoxLayout.Y_AXIS));
                }
                patient.setViewportView(patient_list);
            }
            patientList_recordings.add(patient, "cell 0 2,dock center,wmax 3000,gapx 20 20,gapy 20 20");

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x54a0ad));
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[301,fill]",
                    // rows
                    "[63]"));

                //---- recordings ----
                recordings.setText("Recordings");
                recordings.setBackground(Color.yellow);
                recordings.setEnabled(false);
                recordings.addActionListener(e -> recordings(e));
                panel2.add(recordings, "cell 0 0,dock center");
            }
            patientList_recordings.add(panel2, "cell 0 3");
        }
        contentPane.add(patientList_recordings, BorderLayout.WEST);

        //======== value_display ========
        {
            value_display.setBackground(new Color(0x54a0ad));
            value_display.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[284,fill]",
                // rows
                "[61]" +
                "[56]" +
                "[212]" +
                "[228]" +
                "[195]" +
                "[189]" +
                "[65]" +
                "[]"));

            //======== ecg_interval_panel ========
            {
                ecg_interval_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[345,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- label12 ----
                label12.setText("ECG Lead I and II");
                ecg_interval_panel.add(label12, "cell 0 0");

                //======== panel8 ========
                {
                    panel8.setLayout(new MigLayout(
                        "hidemode 3,alignx center",
                        // columns
                        "[23,fill]" +
                        "[57,fill]" +
                        "[28,fill]" +
                        "[65,fill]",
                        // rows
                        "[]"));

                    //---- label10 ----
                    label10.setText("Plot");
                    panel8.add(label10, "cell 0 0");

                    //---- ECG_display_interval ----
                    ECG_display_interval.setEditable(false);
                    panel8.add(ECG_display_interval, "cell 1 0");

                    //---- label11 ----
                    label11.setText("seconds");
                    panel8.add(label11, "cell 2 0");

                    //---- ECG_update_button ----
                    ECG_update_button.setText("update");
                    ECG_update_button.setEnabled(false);
                    ECG_update_button.addActionListener(e -> ECG_update_button(e));
                    panel8.add(ECG_update_button, "cell 3 0");
                }
                ecg_interval_panel.add(panel8, "cell 0 1");
            }
            value_display.add(ecg_interval_panel, "cell 0 0");

            //======== resp_interval_panel ========
            {
                resp_interval_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[287,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- label16 ----
                label16.setText("Respiratory Pattern");
                resp_interval_panel.add(label16, "cell 0 0");

                //======== panel12 ========
                {
                    panel12.setLayout(new MigLayout(
                        "hidemode 3,alignx center",
                        // columns
                        "[23,fill]" +
                        "[57,fill]" +
                        "[28,fill]" +
                        "[65,fill]",
                        // rows
                        "[]"));

                    //---- label17 ----
                    label17.setText("Plot");
                    panel12.add(label17, "cell 0 0");

                    //---- RESP_pattern_display_interval ----
                    RESP_pattern_display_interval.setEditable(false);
                    panel12.add(RESP_pattern_display_interval, "cell 1 0");

                    //---- label18 ----
                    label18.setText("seconds");
                    panel12.add(label18, "cell 2 0");

                    //---- RESP_pattern_update_button ----
                    RESP_pattern_update_button.setText("update");
                    RESP_pattern_update_button.setEnabled(false);
                    RESP_pattern_update_button.addActionListener(e -> RESP_pattern_update_button(e));
                    panel12.add(RESP_pattern_update_button, "cell 3 0");
                }
                resp_interval_panel.add(panel12, "cell 0 1");
            }
            value_display.add(resp_interval_panel, "cell 0 1");

            //======== hr_panel ========
            {
                hr_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]",
                    // rows
                    "[61]" +
                    "[125]" +
                    "[38]"));

                //---- hr_title ----
                hr_title.setText("Heart Rate");
                hr_panel.add(hr_title, "cell 0 0");

                //======== hr_display ========
                {
                    hr_display.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[181,fill]" +
                        "[111,fill]",
                        // rows
                        "[142]"));

                    //======== hr_display_value ========
                    {
                        hr_display_value.setLayout(new MigLayout(
                            "hidemode 3",
                            // columns
                            "[257,fill]",
                            // rows
                            "[178]"));
                    }
                    hr_display.add(hr_display_value, "cell 0 0");

                    //---- hr_unit ----
                    hr_unit.setText("BPM");
                    hr_display.add(hr_unit, "cell 1 0");
                }
                hr_panel.add(hr_display, "cell 0 1");

                //======== panel3 ========
                {
                    panel3.setLayout(new MigLayout(
                        "hidemode 3,alignx center",
                        // columns
                        "[23,fill]" +
                        "[57,fill]" +
                        "[28,fill]" +
                        "[65,fill]",
                        // rows
                        "[]"));

                    //---- label2 ----
                    label2.setText("Plot");
                    panel3.add(label2, "cell 0 0");

                    //---- HR_display_interval ----
                    HR_display_interval.setEditable(false);
                    panel3.add(HR_display_interval, "cell 1 0");

                    //---- label3 ----
                    label3.setText("mins");
                    panel3.add(label3, "cell 2 0");

                    //---- HR_update_button ----
                    HR_update_button.setText("update");
                    HR_update_button.setEnabled(false);
                    HR_update_button.addActionListener(e -> HR_update_button(e));
                    panel3.add(HR_update_button, "cell 3 0");
                }
                hr_panel.add(panel3, "cell 0 2");
            }
            value_display.add(hr_panel, "cell 0 2");

            //======== resp_panel ========
            {
                resp_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]",
                    // rows
                    "[64]" +
                    "[126]" +
                    "[]"));

                //---- resp_title ----
                resp_title.setText("Respiratory Rate");
                resp_panel.add(resp_title, "cell 0 0");

                //======== resp_display ========
                {
                    resp_display.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[181,fill]" +
                        "[111,fill]",
                        // rows
                        "[142]"));

                    //======== resp_display_value ========
                    {
                        resp_display_value.setLayout(new MigLayout(
                            "hidemode 3",
                            // columns
                            "[341,fill]",
                            // rows
                            "[181]"));
                    }
                    resp_display.add(resp_display_value, "cell 0 0");

                    //---- resp_unit ----
                    resp_unit.setText("/min");
                    resp_display.add(resp_unit, "cell 1 0");
                }
                resp_panel.add(resp_display, "cell 0 1");

                //======== panel4 ========
                {
                    panel4.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[298,fill]",
                        // rows
                        "[]" +
                        "[]"));

                    //======== panel13 ========
                    {
                        panel13.setLayout(new MigLayout(
                            "hidemode 3,alignx center",
                            // columns
                            "[23,fill]" +
                            "[57,fill]" +
                            "[28,fill]" +
                            "[65,fill]",
                            // rows
                            "[]"));

                        //---- label19 ----
                        label19.setText("Plot");
                        panel13.add(label19, "cell 0 0");

                        //---- RESP_rate_display_interval ----
                        RESP_rate_display_interval.setEditable(false);
                        panel13.add(RESP_rate_display_interval, "cell 1 0");

                        //---- label20 ----
                        label20.setText("mins");
                        panel13.add(label20, "cell 2 0");

                        //---- RESP_rate_update_button ----
                        RESP_rate_update_button.setText("update");
                        RESP_rate_update_button.setEnabled(false);
                        RESP_rate_update_button.addActionListener(e -> RESP_rate_update_button(e));
                        panel13.add(RESP_rate_update_button, "cell 3 0");
                    }
                    panel4.add(panel13, "cell 0 1");
                }
                resp_panel.add(panel4, "cell 0 2");
            }
            value_display.add(resp_panel, "cell 0 3");

            //======== bp_panel ========
            {
                bp_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[319,fill]",
                    // rows
                    "[50]" +
                    "[123]" +
                    "[29]"));

                //---- bp_title ----
                bp_title.setText("Blood Pressure");
                bp_panel.add(bp_title, "cell 0 0");

                //======== bp_display ========
                {
                    bp_display.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[181,fill]" +
                        "[111,fill]",
                        // rows
                        "[142]"));

                    //======== bp_values ========
                    {
                        bp_values.setLayout(new MigLayout(
                            "hidemode 3",
                            // columns
                            "[72,fill]" +
                            "[36,fill]" +
                            "[71,fill]",
                            // rows
                            "[]"));

                        //======== sys_display_value ========
                        {
                            sys_display_value.setLayout(new MigLayout(
                                "hidemode 3",
                                // columns
                                "[54,fill]",
                                // rows
                                "[]"));
                        }
                        bp_values.add(sys_display_value, "cell 0 0");

                        //---- slash ----
                        slash.setText("/");
                        bp_values.add(slash, "cell 1 0");

                        //======== dia_display_value ========
                        {
                            dia_display_value.setLayout(new MigLayout(
                                "hidemode 3",
                                // columns
                                "[68,fill]",
                                // rows
                                "[]"));
                        }
                        bp_values.add(dia_display_value, "cell 2 0");
                    }
                    bp_display.add(bp_values, "cell 0 0");

                    //---- bp_unit ----
                    bp_unit.setText("mmHg");
                    bp_display.add(bp_unit, "cell 1 0");
                }
                bp_panel.add(bp_display, "cell 0 1");

                //======== panel5 ========
                {
                    panel5.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[325,fill]",
                        // rows
                        "[]"));

                    //======== panel14 ========
                    {
                        panel14.setLayout(new MigLayout(
                            "hidemode 3,alignx center",
                            // columns
                            "[23,fill]" +
                            "[57,fill]" +
                            "[28,fill]" +
                            "[65,fill]",
                            // rows
                            "[]"));

                        //---- label4 ----
                        label4.setText("Plot");
                        panel14.add(label4, "cell 0 0");

                        //---- BP_display_interval ----
                        BP_display_interval.setEditable(false);
                        panel14.add(BP_display_interval, "cell 1 0");

                        //---- label5 ----
                        label5.setText("mins");
                        panel14.add(label5, "cell 2 0");

                        //---- BP_update_button ----
                        BP_update_button.setText("update");
                        BP_update_button.setEnabled(false);
                        BP_update_button.addActionListener(e -> BP_update_button(e));
                        panel14.add(BP_update_button, "cell 3 0");
                    }
                    panel5.add(panel14, "cell 0 0");
                }
                bp_panel.add(panel5, "cell 0 2");
            }
            value_display.add(bp_panel, "cell 0 4");

            //======== temp_panel ========
            {
                temp_panel.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[383,fill]",
                    // rows
                    "[49]" +
                    "[95]" +
                    "[23]"));

                //---- temp_title ----
                temp_title.setText("Body Temperature");
                temp_panel.add(temp_title, "cell 0 0");

                //======== temp_display ========
                {
                    temp_display.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[181,fill]" +
                        "[111,fill]",
                        // rows
                        "[142]"));

                    //======== temp_display_value ========
                    {
                        temp_display_value.setLayout(new MigLayout(
                            "hidemode 3",
                            // columns
                            "[339,fill]",
                            // rows
                            "[198]"));
                    }
                    temp_display.add(temp_display_value, "cell 0 0");

                    //---- temp_unit ----
                    temp_unit.setText("\u2103");
                    temp_display.add(temp_unit, "cell 1 0");
                }
                temp_panel.add(temp_display, "cell 0 1");

                //======== panel6 ========
                {
                    panel6.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[288,fill]",
                        // rows
                        "[]"));

                    //======== panel15 ========
                    {
                        panel15.setLayout(new MigLayout(
                            "hidemode 3,alignx center",
                            // columns
                            "[23,fill]" +
                            "[57,fill]" +
                            "[28,fill]" +
                            "[65,fill]",
                            // rows
                            "[]"));

                        //---- label6 ----
                        label6.setText("Plot");
                        panel15.add(label6, "cell 0 0");

                        //---- Temp_display_interval ----
                        Temp_display_interval.setEditable(false);
                        panel15.add(Temp_display_interval, "cell 1 0");

                        //---- label7 ----
                        label7.setText("mins");
                        panel15.add(label7, "cell 2 0");

                        //---- Temp_update_button ----
                        Temp_update_button.setText("update");
                        Temp_update_button.setEnabled(false);
                        Temp_update_button.addActionListener(e -> Temp_update_button(e));
                        panel15.add(Temp_update_button, "cell 3 0");
                    }
                    panel6.add(panel15, "cell 0 0");
                }
                temp_panel.add(panel6, "cell 0 2");
            }
            value_display.add(temp_panel, "cell 0 5");

            //---- report_button ----
            report_button.setText("Reports");
            report_button.setBackground(Color.yellow);
            report_button.setEnabled(false);
            report_button.addActionListener(e -> report_button(e));
            value_display.add(report_button, "cell 0 6");
        }
        contentPane.add(value_display, BorderLayout.EAST);

        //======== plotPanel ========
        {
            plotPanel.setBackground(new Color(0x54a0ad));
            plotPanel.setLayout(new MigLayout(
                "fill,hidemode 3",
                // columns
                "[1362,fill]",
                // rows
                "[536]" +
                "[578]"));

            //======== ecg_table ========
            {
                ecg_table.setBackground(new Color(0x54a0ad));
                ecg_table.setLayout(new MigLayout(
                    "fill,hidemode 3",
                    // columns
                    "[1411,fill]",
                    // rows
                    "[242]" +
                    "[237]"));

                //======== ecg1 ========
                {
                    ecg1.setBackground(Color.white);
                    ecg1.setLayout(new MigLayout(
                        "fill,hidemode 3",
                        // columns
                        "[1500,fill]",
                        // rows
                        "[305]"));
                }
                ecg_table.add(ecg1, "cell 0 0");

                //======== ecg2 ========
                {
                    ecg2.setBackground(Color.white);
                    ecg2.setLayout(new MigLayout(
                        "fill,hidemode 3",
                        // columns
                        "[1500,fill]",
                        // rows
                        "[339]"));
                }
                ecg_table.add(ecg2, "cell 0 1");
            }
            plotPanel.add(ecg_table, "cell 0 0");

            //======== char_label_panel ========
            {
                char_label_panel.setBackground(new Color(0x54a0ad));
                char_label_panel.setLayout(new MigLayout(
                    "fill,hidemode 3",
                    // columns
                    "[900,fill]" +
                    "[900,fill]",
                    // rows
                    "[185]" +
                    "[185]" +
                    "[185]"));

                //======== resp_pattern_table ========
                {
                    resp_pattern_table.setBackground(Color.white);
                    resp_pattern_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[719,fill]",
                        // rows
                        "[252]"));
                }
                char_label_panel.add(resp_pattern_table, "cell 0 0");

                //======== resp_rate_table ========
                {
                    resp_rate_table.setBackground(Color.white);
                    resp_rate_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[944,fill]",
                        // rows
                        "[827]"));
                }
                char_label_panel.add(resp_rate_table, "cell 1 0");

                //======== sys_table ========
                {
                    sys_table.setBackground(Color.white);
                    sys_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[719,fill]",
                        // rows
                        "[252]"));
                }
                char_label_panel.add(sys_table, "cell 0 1");

                //======== dia_table ========
                {
                    dia_table.setBackground(Color.white);
                    dia_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[956,fill]",
                        // rows
                        "[218]"));
                }
                char_label_panel.add(dia_table, "cell 1 1");

                //======== body_temp_table ========
                {
                    body_temp_table.setBackground(Color.white);
                    body_temp_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[719,fill]",
                        // rows
                        "[252]"));
                }
                char_label_panel.add(body_temp_table, "cell 0 2");

                //======== heartrate_table ========
                {
                    heartrate_table.setBackground(Color.white);
                    heartrate_table.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[963,fill]",
                        // rows
                        "[217]"));
                }
                char_label_panel.add(heartrate_table, "cell 1 2");
            }
            plotPanel.add(char_label_panel, "cell 0 1");
        }
        contentPane.add(plotPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    public JPanel patientList_recordings;
    public JPanel NewPatient;
    private JLabel label1;
    public JButton add_new_patient;
    public JScrollPane patient;
    public JPanel patient_list;
    private JPanel panel2;
    public JButton recordings;
    public JPanel value_display;
    public JPanel ecg_interval_panel;
    private JLabel label12;
    private JPanel panel8;
    private JLabel label10;
    public JTextField ECG_display_interval;
    private JLabel label11;
    public JButton ECG_update_button;
    public JPanel resp_interval_panel;
    private JLabel label16;
    private JPanel panel12;
    private JLabel label17;
    public JTextField RESP_pattern_display_interval;
    private JLabel label18;
    public JButton RESP_pattern_update_button;
    public JPanel hr_panel;
    private JLabel hr_title;
    public JPanel hr_display;
    public JPanel hr_display_value;
    private JLabel hr_unit;
    private JPanel panel3;
    private JLabel label2;
    public JTextField HR_display_interval;
    private JLabel label3;
    public JButton HR_update_button;
    public JPanel resp_panel;
    private JLabel resp_title;
    public JPanel resp_display;
    public JPanel resp_display_value;
    private JLabel resp_unit;
    private JPanel panel4;
    private JPanel panel13;
    private JLabel label19;
    public JTextField RESP_rate_display_interval;
    private JLabel label20;
    public JButton RESP_rate_update_button;
    public JPanel bp_panel;
    private JLabel bp_title;
    public JPanel bp_display;
    public JPanel bp_values;
    public JPanel sys_display_value;
    private JLabel slash;
    public JPanel dia_display_value;
    private JLabel bp_unit;
    private JPanel panel5;
    private JPanel panel14;
    private JLabel label4;
    public JTextField BP_display_interval;
    private JLabel label5;
    public JButton BP_update_button;
    public JPanel temp_panel;
    private JLabel temp_title;
    public JPanel temp_display;
    public JPanel temp_display_value;
    private JLabel temp_unit;
    private JPanel panel6;
    private JPanel panel15;
    private JLabel label6;
    public JTextField Temp_display_interval;
    private JLabel label7;
    public JButton Temp_update_button;
    public JButton report_button;
    public JPanel plotPanel;
    public JPanel ecg_table;
    public JPanel ecg1;
    public JPanel ecg2;
    public JPanel char_label_panel;
    public JPanel resp_pattern_table;
    public JPanel resp_rate_table;
    public JPanel sys_table;
    public JPanel dia_table;
    public JPanel body_temp_table;
    public JPanel heartrate_table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
