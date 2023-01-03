package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.*;

import net.miginfocom.swing.*;


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
    }


    private void add_new_patient(ActionEvent e) {
        Patient_Adder editor = new Patient_Adder(this);
        this.add_new_patient.setEnabled(false);
        editor.setVisible(true);
    }

    private void report_button(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei
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
        hr_panel = new JPanel();
        hr_title = new JLabel();
        hr_display = new JPanel();
        hr_display_value = new JPanel();
        hr_unit = new JLabel();
        resp_panel = new JPanel();
        resp_title = new JLabel();
        resp_display = new JPanel();
        resp_display_value = new JPanel();
        resp_unit = new JLabel();
        bp_panel = new JPanel();
        bp_title = new JLabel();
        bp_display = new JPanel();
        bp_values = new JPanel();
        slash = new JLabel();
        bp_unit = new JLabel();
        temp_panel = new JPanel();
        temp_title = new JLabel();
        temp_display = new JPanel();
        temp_display_value = new JPanel();
        temp_unit = new JLabel();
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
                "[300,fill]",
                // rows
                "[240]" +
                "[242]" +
                "[253]" +
                "[231]" +
                "[93]"));

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
            }
            value_display.add(hr_panel, "cell 0 0");

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
            }
            value_display.add(resp_panel, "cell 0 1");

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
            }
            value_display.add(bp_panel, "cell 0 2");

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
            }
            value_display.add(temp_panel, "cell 0 3");

            //---- report_button ----
            report_button.setText("Reports");
            report_button.addActionListener(e -> report_button(e));
            value_display.add(report_button, "cell 0 4");
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
                        "[1363,fill]",
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
    // Generated using JFormDesigner Educational license - Tianyu Wei
    public JPanel patientList_recordings;
    public JPanel NewPatient;
    private JLabel label1;
    public JButton add_new_patient;
    public JScrollPane patient;
    public JPanel patient_list;
    private JPanel panel1;
    public JButton real_time_plots;
    private JPanel panel2;
    private JButton recordings;
    public JPanel value_display;
    private JPanel hr_panel;
    private JLabel hr_title;
    public JPanel hr_display;
    public JPanel hr_display_value;
    private JLabel hr_unit;
    private JPanel resp_panel;
    private JLabel resp_title;
    public JPanel resp_display;
    public JPanel resp_display_value;
    private JLabel resp_unit;
    private JPanel bp_panel;
    private JLabel bp_title;
    public JPanel bp_display;
    public JPanel bp_values;
    private JLabel slash;
    private JLabel bp_unit;
    private JPanel temp_panel;
    private JLabel temp_title;
    public JPanel temp_display;
    public JPanel temp_display_value;
    private JLabel temp_unit;
    private JButton report_button;
    public JPanel plotPanel;
    public JPanel ecg_table;
    public JPanel ecg1;
    public JPanel ecg2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
