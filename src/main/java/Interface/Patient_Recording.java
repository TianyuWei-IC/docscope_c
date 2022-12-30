package Interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Thu Dec 29 18:21:53 GMT 2022
 */



/**
 * @author Tianyu
 */
public class Patient_Recording extends JFrame {
    private GUI_test mainGUI;
    public Patient_Recording(GUI_test mainGUI) {
        initComponents();
        this.mainGUI = mainGUI;
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
        date_select_panel = new JPanel();
        year_label = new JLabel();
        Integer[] years = new Integer[10];
            for (int i=0; i < 10; i++){
            	int the_year = -i+2023;
                years[i]=the_year;
        }
        year_selector = new JComboBox(years);
        month_label = new JLabel();
        Integer[] month = new Integer[12];
            for (int i=0; i < 12; i++){
            	int the_month = i+1;
                month[i]=the_month;
        }
        month_selector = new JComboBox(month);
        day_label = new JLabel();
        Integer[] days = new Integer[31];
            for (int i=0; i < 31; i++){
            	int the_day = i+1;
                days[i]=the_day;
        }
        day_selector = new JComboBox(days);
        time_intervel_select_panel = new JPanel();
        start_time_label = new JLabel();
        start_time_hour = new JTextField();
        colon = new JLabel();
        start_time_min = new JTextField();
        end_time_label = new JLabel();
        end_time_hour = new JTextField();
        colon2 = new JLabel();
        end_time_min = new JTextField();
        generate_button = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[718,fill]",
            // rows
            "[53]" +
            "[29]" +
            "[34]" +
            "[35]" +
            "[56]"));

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

        //======== date_select_panel ========
        {
            date_select_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[43,fill]" +
                "[99,fill]" +
                "[51,fill]" +
                "[95,fill]" +
                "[31,fill]" +
                "[103,fill]",
                // rows
                "[56]"));

            //---- year_label ----
            year_label.setText("Year:");
            date_select_panel.add(year_label, "cell 0 0");
            date_select_panel.add(year_selector, "cell 1 0");

            //---- month_label ----
            month_label.setText("Month:");
            date_select_panel.add(month_label, "cell 2 0");
            date_select_panel.add(month_selector, "cell 3 0");

            //---- day_label ----
            day_label.setText("Day:");
            date_select_panel.add(day_label, "cell 4 0");
            date_select_panel.add(day_selector, "cell 5 0");
        }
        contentPane.add(date_select_panel, "cell 0 2");

        //======== time_intervel_select_panel ========
        {
            time_intervel_select_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[73,fill]" +
                "[51,fill]" +
                "[4,fill]" +
                "[39,fill]" +
                "[60,fill]" +
                "[50,fill]" +
                "[0,fill]" +
                "[48,fill]",
                // rows
                "[46]"));

            //---- start_time_label ----
            start_time_label.setText("Start Time:");
            time_intervel_select_panel.add(start_time_label, "cell 0 0");
            time_intervel_select_panel.add(start_time_hour, "cell 1 0");

            //---- colon ----
            colon.setText(":");
            time_intervel_select_panel.add(colon, "cell 2 0");
            time_intervel_select_panel.add(start_time_min, "cell 3 0");

            //---- end_time_label ----
            end_time_label.setText("End Time:");
            time_intervel_select_panel.add(end_time_label, "cell 4 0");
            time_intervel_select_panel.add(end_time_hour, "cell 5 0");

            //---- colon2 ----
            colon2.setText(":");
            time_intervel_select_panel.add(colon2, "cell 6 0");
            time_intervel_select_panel.add(end_time_min, "cell 7 0");
        }
        contentPane.add(time_intervel_select_panel, "cell 0 3");

        //---- generate_button ----
        generate_button.setText("Generate");
        contentPane.add(generate_button, "cell 0 4");
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
    private JPanel date_select_panel;
    private JLabel year_label;
    private JComboBox year_selector;
    private JLabel month_label;
    private JComboBox month_selector;
    private JLabel day_label;
    private JComboBox day_selector;
    private JPanel time_intervel_select_panel;
    private JLabel start_time_label;
    private JTextField start_time_hour;
    private JLabel colon;
    private JTextField start_time_min;
    private JLabel end_time_label;
    private JTextField end_time_hour;
    private JLabel colon2;
    private JTextField end_time_min;
    private JButton generate_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
