package Interface;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.*;
import master.Patient;
import net.miginfocom.swing.*;
import netRelated.netAction;

import static java.lang.Double.parseDouble;
/*
 * Created by JFormDesigner on Thu Dec 29 18:21:53 GMT 2022
 */



/**
 * @author Tianyu
 */
public class Patient_Recording extends JFrame {
    private GUI_test mainGUI;
    private Patient current_patient;
    public Patient_Recording(GUI_test mainGUI, Patient current_patient) {
        initComponents();
        this.mainGUI = mainGUI;
        this.current_patient = current_patient;
    }

    private void PatientRecordingWindowClosing(WindowEvent e) {
        this.mainGUI.recordings.setEnabled(true);
    }

    private void generate_button(ActionEvent e) {
        Recording_Result display_window = new Recording_Result();
        display_window.Patient_name.setText(this.current_patient.first_name+" "+this.current_patient.last_name);
        String Date = String.valueOf(dateChooser1.getDate());
        Date = Date.substring(4,10)+Date.substring(23,28);
        display_window.Date.setText(Date);
        display_window.signal_type.setText((String) signal_selector.getSelectedItem());
        display_window.start_time.setText(start_time_hour.getSelectedItem()+":"+start_time_min.getSelectedItem());
        display_window.end_time.setText(end_time_hour.getSelectedItem()+":"+end_time_min.getSelectedItem());
        System.out.println(display_window.getComponentCount());
        //display_window.repaint();

        display_window.setVisible(true);

        //

    }

    private void start_time_hourItemStateChanged(ItemEvent e) {
       try {
            Integer start_hour = (Integer) start_time_hour.getSelectedItem();
            Integer start_min = (Integer) start_time_min.getSelectedItem();
            Integer end_hour = (Integer) end_time_hour.getSelectedItem();
            Integer end_min = (Integer) end_time_min.getSelectedItem();
            System.out.println("running");
            //System.out.println(!dateChooser1.getDate().toString().isEmpty());

            if (((start_hour<end_hour) | ((start_hour==end_hour) & (start_min<end_min)))& (!dateChooser1.getDate().toString().isEmpty())){
                generate_button.setEnabled(true);
            }else{
                generate_button.setEnabled(false);
            }

        }catch(Exception error) {
            generate_button.setEnabled(false);
            System.out.println("!dateChooser1.getDate().toString().isEmpty()");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        recording_title = new JLabel();
        signal_select_label = new JLabel();
        String[] signal_pack = new String[8];
        signal_pack[0] = "ECG lead I";
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
        dateChooser1.setMinSelectableDate(new Date(netAction.getInitialTime()));
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
        label2 = new JLabel();
        generate_button = new JButton();

        //======== this ========
        setFont(new Font(Font.DIALOG, Font.PLAIN, 8));
        setPreferredSize(new Dimension(420, 300));
        setMinimumSize(new Dimension(420, 300));
        setMaximumSize(new Dimension(420, 300));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PatientRecordingWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[311,fill]",
            // rows
            "[53]" +
            "[29]" +
            "[34]" +
            "[35]" +
            "[19]" +
            "[54]"));

        //---- recording_title ----
        recording_title.setText("Recordings");
        recording_title.setFont(recording_title.getFont().deriveFont(recording_title.getFont().getSize() + 6f));
        contentPane.add(recording_title, "cell 0 0");

        //---- signal_select_label ----
        signal_select_label.setText("Please Select a Signal:");
        signal_select_label.setFont(signal_select_label.getFont().deriveFont(signal_select_label.getFont().getSize() + 2f));
        signal_select_label.setPreferredSize(new Dimension(180, 22));
        signal_select_label.setMinimumSize(new Dimension(180, 22));
        signal_select_label.setMaximumSize(new Dimension(180, 22));
        signal_select_label.setIconTextGap(0);
        contentPane.add(signal_select_label, "cell 0 1");
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
            label1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            panel1.add(label1, "cell 0 0");
            panel1.add(dateChooser1, "cell 1 0");
        }
        contentPane.add(panel1, "cell 0 2");

        //======== time_intervel_select_panel ========
        {
            time_intervel_select_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[62,fill]" +
                "[80,fill]" +
                "[20,fill]0" +
                "[26,fill]" +
                "[39,fill]" +
                "[60,fill]" +
                "[50,fill]" +
                "[fill]" +
                "[0,fill]" +
                "[48,fill]",
                // rows
                "[46]0" +
                "[]"));

            //---- start_time_label ----
            start_time_label.setText("Start Time:");
            time_intervel_select_panel.add(start_time_label, "cell 0 0");

            //---- start_time_hour ----
            start_time_hour.setMinimumSize(new Dimension(50, 30));
            start_time_hour.setPreferredSize(new Dimension(50, 30));
            start_time_hour.setMaximumSize(new Dimension(50, 30));
            start_time_hour.addItemListener(e -> start_time_hourItemStateChanged(e));
            time_intervel_select_panel.add(start_time_hour, "cell 1 0");

            //---- colon ----
            colon.setText(":");
            time_intervel_select_panel.add(colon, "cell 3 0");

            //---- start_time_min ----
            start_time_min.setMinimumSize(new Dimension(50, 30));
            start_time_min.setPreferredSize(new Dimension(50, 30));
            start_time_min.setMaximumSize(new Dimension(50, 30));
            time_intervel_select_panel.add(start_time_min, "cell 4 0");

            //---- end_time_label ----
            end_time_label.setText("End Time:");
            time_intervel_select_panel.add(end_time_label, "cell 5 0");

            //---- end_time_hour ----
            end_time_hour.setMinimumSize(new Dimension(50, 30));
            end_time_hour.setPreferredSize(new Dimension(50, 30));
            end_time_hour.setMaximumSize(new Dimension(50, 30));
            time_intervel_select_panel.add(end_time_hour, "cell 7 0");

            //---- colon2 ----
            colon2.setText(":");
            time_intervel_select_panel.add(colon2, "cell 8 0");

            //---- end_time_min ----
            end_time_min.setMinimumSize(new Dimension(50, 30));
            end_time_min.setPreferredSize(new Dimension(50, 30));
            end_time_min.setMaximumSize(new Dimension(50, 30));
            time_intervel_select_panel.add(end_time_min, "cell 9 0");
        }
        contentPane.add(time_intervel_select_panel, "cell 0 3");

        //---- label2 ----
        label2.setText("* For ECG,the recommend time interval is 10 seconds");
        contentPane.add(label2, "cell 0 4");

        //---- generate_button ----
        generate_button.setText("Generate");
        generate_button.setEnabled(false);
        generate_button.addActionListener(e -> generate_button(e));
        contentPane.add(generate_button, "cell 0 5");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
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
    private JLabel label2;
    private JButton generate_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
