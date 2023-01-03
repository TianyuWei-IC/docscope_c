/*
 * Created by JFormDesigner on Sun Jan 01 16:22:09 GMT 2023
 */

package Interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class Urgent extends JFrame {
    public Urgent() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        urgent_panel = new JPanel();
        urgent_title = new JPanel();
        urgent_icon = new JLabel();
        urgent_icon.setIcon(new ImageIcon("src/main/java/icons/urgent_small.png"));
        //label1.setIcon(new ImageIcon("D:/Prog3/docscope_c/src/main/java/icons/urgent.png"));
        urgent_label = new JLabel();
        abnormal_type = new JPanel();
        abnormal_label = new JLabel();
        abnormal_signal = new JLabel();
        high_low = new JLabel();
        detected_time_display = new JPanel();
        detect_label = new JLabel();
        urgent_time = new JLabel();

        String time = String.valueOf(java.time.LocalTime.now());
        urgent_time.setText(time.substring(0,8));
        on_patient_label = new JLabel();
        patient_name = new JLabel();
        immediate_action_table = new JLabel();

        //======== this ========
        setBackground(new Color(0xf60404));
        setForeground(new Color(0xf60404));
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[645,fill]",
            // rows
            "[551]"));

        //======== urgent_panel ========
        {
            urgent_panel.setForeground(Color.white);
            urgent_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[619,fill]",
                // rows
                "[104]" +
                "[82]" +
                "[85]" +
                "[57]" +
                "[110]"));

            //======== urgent_title ========
            {
                urgent_title.setMinimumSize(new Dimension(500, 155));
                urgent_title.setPreferredSize(new Dimension(500, 155));
                urgent_title.setMaximumSize(new Dimension(500, 155));
                urgent_title.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[108,fill]" +
                    "[309,fill]",
                    // rows
                    "[100]"));

                //---- urgent_icon ----
                urgent_icon.setMaximumSize(new Dimension(178, 155));
                urgent_icon.setMinimumSize(new Dimension(178, 155));
                urgent_icon.setPreferredSize(new Dimension(178, 155));
                urgent_title.add(urgent_icon, "cell 0 0");

                //---- urgent_label ----
                urgent_label.setText("URGENT");
                urgent_label.setFocusable(false);
                urgent_label.setFont(new Font("Arial", urgent_label.getFont().getStyle() | Font.BOLD, urgent_label.getFont().getSize() + 50));
                urgent_label.setMaximumSize(new Dimension(300, 83));
                urgent_label.setMinimumSize(new Dimension(300, 83));
                urgent_label.setPreferredSize(new Dimension(300, 83));
                urgent_title.add(urgent_label, "cell 1 0");
            }
            urgent_panel.add(urgent_title, "cell 0 0,alignx center,growx 0");

            //======== abnormal_type ========
            {
                abnormal_type.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[135,fill]" +
                    "[193,fill]" +
                    "[199,fill]",
                    // rows
                    "[]"));

                //---- abnormal_label ----
                abnormal_label.setText("ABNORMAL");
                abnormal_label.setFont(abnormal_label.getFont().deriveFont(abnormal_label.getFont().getSize() + 20f));
                abnormal_type.add(abnormal_label, "cell 0 0");

                //---- abnormal_signal ----
                abnormal_signal.setText("text");
                abnormal_signal.setFont(abnormal_signal.getFont().deriveFont(abnormal_signal.getFont().getSize() + 20f));
                abnormal_type.add(abnormal_signal, "cell 1 0");

                //---- high_low ----
                high_low.setText("text");
                high_low.setFont(high_low.getFont().deriveFont(high_low.getFont().getSize() + 20f));
                abnormal_type.add(high_low, "cell 2 0");
            }
            urgent_panel.add(abnormal_type, "cell 0 1");

            //======== detected_time_display ========
            {
                detected_time_display.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[142,fill]" +
                    "[102,fill]" +
                    "[171,fill]",
                    // rows
                    "[]"));

                //---- detect_label ----
                detect_label.setText("has been detected at ");
                detect_label.setFont(detect_label.getFont().deriveFont(detect_label.getFont().getSize() + 15f));
                detected_time_display.add(detect_label, "cell 0 0");

                //---- urgent_time ----
                urgent_time.setFont(urgent_time.getFont().deriveFont(urgent_time.getFont().getSize() + 15f));
                detected_time_display.add(urgent_time, "cell 1 0");

                //---- on_patient_label ----
                on_patient_label.setText("on patient : ");
                on_patient_label.setFont(on_patient_label.getFont().deriveFont(on_patient_label.getFont().getSize() + 15f));
                detected_time_display.add(on_patient_label, "cell 2 0");
            }
            urgent_panel.add(detected_time_display, "cell 0 2");

            //---- patient_name ----
            patient_name.setFont(patient_name.getFont().deriveFont(patient_name.getFont().getSize() + 15f));
            urgent_panel.add(patient_name, "cell 0 3");

            //---- immediate_action_table ----
            immediate_action_table.setText("Immediate action is required!");
            immediate_action_table.setFont(immediate_action_table.getFont().deriveFont(immediate_action_table.getFont().getSize() + 15f));
            urgent_panel.add(immediate_action_table, "cell 0 4");
        }
        contentPane.add(urgent_panel, "cell 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JPanel urgent_panel;
    private JPanel urgent_title;
    private JLabel urgent_icon;
    private JLabel urgent_label;
    private JPanel abnormal_type;
    private JLabel abnormal_label;
    public JLabel abnormal_signal;
    public JLabel high_low;
    private JPanel detected_time_display;
    private JLabel detect_label;
    private JLabel urgent_time;
    private JLabel on_patient_label;
    public JLabel patient_name;
    private JLabel immediate_action_table;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
