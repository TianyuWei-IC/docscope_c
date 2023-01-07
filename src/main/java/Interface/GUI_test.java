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
        Patient_Recording recorder = new Patient_Recording(this);
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

//        SeriesChartPane current_ECG_I = (SeriesChartPane) this.ecg1.getComponent(0);
//        SeriesChartPane current_ECG_II = (SeriesChartPane) this.ecg1.getComponent(0);
        Display_Chart current_Temp = (Display_Chart) this.ecg2.getComponent(0);
        Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();
        Patient current_patient = current_temp_cl_display.patient;
        current_patient.temperature_interval = Double.valueOf(this.Temp_display_interval.getText());
        current_temp_cl_display.updater.cancel(true);
        Chart_Label_Display updated_temp_cl_display =  current_patient.load_chartLabel((long) floor(current_patient.temperature_interval*1000*60),"body temperature");
        this.ecg2.removeAll();
        this.ecg2.add(updated_temp_cl_display.display_chart);
        this.temp_display_value.removeAll();
        this.temp_display_value.add(updated_temp_cl_display.value_label);

        current_patient.panelTemperature = updated_temp_cl_display;
        current_temp_cl_display = null;
    }

    private void ECG_update_button(ActionEvent e) {
//        SeriesChartPane current_ECG_I = (SeriesChartPane) this.ecg1.getComponent(0);
//        SeriesChartPane current_ECG_II = (SeriesChartPane) this.ecg1.getComponent(0);
        SeriesChartPane current_ECG_I = (SeriesChartPane) this.ecg1.getComponent(0);
        Patient current_patient = current_ECG_I.find_patient();
        current_patient.ecg_interval = Double.valueOf(this.ECG_display_interval.getText());
        current_ECG_I.worker.cancel(true);
        SeriesChartPane updated_ECG_I =  current_patient.load_chart((long) floor(current_patient.ecg_interval*1000),"ecg1");
        this.ecg1.removeAll();
        this.ecg1.add(updated_ECG_I);

        current_patient.panelEcg1 = updated_ECG_I;
        current_ECG_I = null;

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
        panel1 = new JPanel();
        real_time_plots = new JButton();
        panel2 = new JPanel();
        recordings = new JButton();
        value_display = new JPanel();
        panel7 = new JPanel();
        label12 = new JLabel();
        panel8 = new JPanel();
        label10 = new JLabel();
        ECG_display_interval = new JTextField();
        label11 = new JLabel();
        ECG_update_button = new JButton();
        panel11 = new JPanel();
        label16 = new JLabel();
        panel12 = new JPanel();
        label17 = new JLabel();
        textField7 = new JTextField();
        label18 = new JLabel();
        button7 = new JButton();
        hr_panel = new JPanel();
        hr_title = new JLabel();
        hr_display = new JPanel();
        hr_display_value = new JPanel();
        hr_unit = new JLabel();
        panel3 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        label3 = new JLabel();
        button1 = new JButton();
        resp_panel = new JPanel();
        resp_title = new JLabel();
        resp_display = new JPanel();
        resp_display_value = new JPanel();
        resp_unit = new JLabel();
        panel4 = new JPanel();
        panel13 = new JPanel();
        label19 = new JLabel();
        textField8 = new JTextField();
        label20 = new JLabel();
        button8 = new JButton();
        bp_panel = new JPanel();
        bp_title = new JLabel();
        bp_display = new JPanel();
        bp_values = new JPanel();
        slash = new JLabel();
        bp_unit = new JLabel();
        panel5 = new JPanel();
        panel14 = new JPanel();
        label4 = new JLabel();
        textField2 = new JTextField();
        label5 = new JLabel();
        button2 = new JButton();
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
        plotPanel = new JPanel();
        ecg_table = new JPanel();
        ecg1 = new JPanel();
        ecg2 = new JPanel();

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
                "[309]" +
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

            //======== panel1 ========
            {
                panel1.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[349,fill]",
                    // rows
                    "[121]"));

                //---- real_time_plots ----
                real_time_plots.setText("Real-time Plots");
                real_time_plots.addActionListener(e -> real_time_plots(e));
                panel1.add(real_time_plots, "dock center");
            }
            patientList_recordings.add(panel1, "cell 0 3");

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[301,fill]",
                    // rows
                    "[257]"));

                //---- recordings ----
                recordings.setText("Recordings");
                recordings.addActionListener(e -> recordings(e));
                panel2.add(recordings, "cell 0 0,dock center");
            }
            patientList_recordings.add(panel2, "cell 0 4");
        }
        contentPane.add(patientList_recordings, BorderLayout.WEST);

        //======== value_display ========
        {
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

            //======== panel7 ========
            {
                panel7.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[345,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- label12 ----
                label12.setText("ECG Lead I and II");
                panel7.add(label12, "cell 0 0");

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
                    ECG_display_interval.setText("5");
                    panel8.add(ECG_display_interval, "cell 1 0");

                    //---- label11 ----
                    label11.setText("seconds");
                    panel8.add(label11, "cell 2 0");

                    //---- ECG_update_button ----
                    ECG_update_button.setText("update");
                    ECG_update_button.addActionListener(e -> ECG_update_button(e));
                    panel8.add(ECG_update_button, "cell 3 0");
                }
                panel7.add(panel8, "cell 0 1");
            }
            value_display.add(panel7, "cell 0 0");

            //======== panel11 ========
            {
                panel11.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[287,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- label16 ----
                label16.setText("Respiratory Pattern");
                panel11.add(label16, "cell 0 0");

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
                    panel12.add(textField7, "cell 1 0");

                    //---- label18 ----
                    label18.setText("seconds");
                    panel12.add(label18, "cell 2 0");

                    //---- button7 ----
                    button7.setText("update");
                    panel12.add(button7, "cell 3 0");
                }
                panel11.add(panel12, "cell 0 1");
            }
            value_display.add(panel11, "cell 0 1");

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
                    panel3.add(textField1, "cell 1 0");

                    //---- label3 ----
                    label3.setText("mins");
                    panel3.add(label3, "cell 2 0");

                    //---- button1 ----
                    button1.setText("update");
                    panel3.add(button1, "cell 3 0");
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
                        panel13.add(textField8, "cell 1 0");

                        //---- label20 ----
                        label20.setText("mins");
                        panel13.add(label20, "cell 2 0");

                        //---- button8 ----
                        button8.setText("update");
                        panel13.add(button8, "cell 3 0");
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

                        //---- slash ----
                        slash.setText("/");
                        bp_values.add(slash, "cell 1 0");
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
                        panel14.add(textField2, "cell 1 0");

                        //---- label5 ----
                        label5.setText("mins");
                        panel14.add(label5, "cell 2 0");

                        //---- button2 ----
                        button2.setText("update");
                        panel14.add(button2, "cell 3 0");
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
                        panel15.add(Temp_display_interval, "cell 1 0");

                        //---- label7 ----
                        label7.setText("mins");
                        panel15.add(label7, "cell 2 0");

                        //---- Temp_update_button ----
                        Temp_update_button.setText("update");
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
            report_button.addActionListener(e -> report_button(e));
            value_display.add(report_button, "cell 0 6");
        }
        contentPane.add(value_display, BorderLayout.EAST);

        //======== plotPanel ========
        {
            plotPanel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[1362,fill]",
                // rows
                "[530]" +
                "[742]"));

            //======== ecg_table ========
            {
                ecg_table.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[1411,fill]",
                    // rows
                    "[230]" +
                    "[241]"));

                //======== ecg1 ========
                {
                    ecg1.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[1392,fill]",
                        // rows
                        "[176]"));
                }
                ecg_table.add(ecg1, "cell 0 0");

                //======== ecg2 ========
                {
                    ecg2.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[1443,fill]",
                        // rows
                        "[247]"));
                }
                ecg_table.add(ecg2, "cell 0 1");
            }
            plotPanel.add(ecg_table, "cell 0 0");
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
    private JPanel panel1;
    public JButton real_time_plots;
    private JPanel panel2;
    public JButton recordings;
    public JPanel value_display;
    private JPanel panel7;
    private JLabel label12;
    private JPanel panel8;
    private JLabel label10;
    public JTextField ECG_display_interval;
    private JLabel label11;
    private JButton ECG_update_button;
    private JPanel panel11;
    private JLabel label16;
    private JPanel panel12;
    private JLabel label17;
    private JTextField textField7;
    private JLabel label18;
    private JButton button7;
    private JPanel hr_panel;
    private JLabel hr_title;
    public JPanel hr_display;
    public JPanel hr_display_value;
    private JLabel hr_unit;
    private JPanel panel3;
    private JLabel label2;
    private JTextField textField1;
    private JLabel label3;
    private JButton button1;
    private JPanel resp_panel;
    private JLabel resp_title;
    public JPanel resp_display;
    public JPanel resp_display_value;
    private JLabel resp_unit;
    private JPanel panel4;
    private JPanel panel13;
    private JLabel label19;
    private JTextField textField8;
    private JLabel label20;
    private JButton button8;
    private JPanel bp_panel;
    private JLabel bp_title;
    public JPanel bp_display;
    public JPanel bp_values;
    private JLabel slash;
    private JLabel bp_unit;
    private JPanel panel5;
    private JPanel panel14;
    private JLabel label4;
    private JTextField textField2;
    private JLabel label5;
    private JButton button2;
    private JPanel temp_panel;
    private JLabel temp_title;
    public JPanel temp_display;
    public JPanel temp_display_value;
    private JLabel temp_unit;
    private JPanel panel6;
    private JPanel panel15;
    private JLabel label6;
    public JTextField Temp_display_interval;
    private JLabel label7;
    private JButton Temp_update_button;
    private JButton report_button;
    public JPanel plotPanel;
    public JPanel ecg_table;
    public JPanel ecg1;
    public JPanel ecg2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
