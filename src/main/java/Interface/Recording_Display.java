/*
 * Created by JFormDesigner on Sun Jan 08 19:48:24 GMT 2023
 */

package Interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class Recording_Display extends JFrame {
    public Recording_Display() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        name_info = new JPanel();
        Patient_name = new JLabel();
        signal_type = new JLabel();
        time_info = new JPanel();
        Date = new JLabel();
        start_time = new JLabel();
        to_label = new JLabel();
        end_time = new JLabel();
        A1 = new JPanel();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[1145,fill]",
            // rows
            "[]" +
            "[]" +
            "[459]"));

        //======== name_info ========
        {
            name_info.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[169,fill]" +
                "[195,fill]",
                // rows
                "[44]"));

            //---- Patient_name ----
            Patient_name.setText("text");
            name_info.add(Patient_name, "cell 0 0");

            //---- signal_type ----
            signal_type.setText("text");
            name_info.add(signal_type, "cell 1 0");
        }
        contentPane.add(name_info, "cell 0 0");

        //======== time_info ========
        {
            time_info.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[155,fill]" +
                "[63,fill]" +
                "[34,fill]" +
                "[96,fill]",
                // rows
                "[]"));

            //---- Date ----
            Date.setText("text");
            time_info.add(Date, "cell 0 0");

            //---- start_time ----
            start_time.setText("text");
            time_info.add(start_time, "cell 1 0");

            //---- to_label ----
            to_label.setText("to");
            time_info.add(to_label, "cell 2 0");

            //---- end_time ----
            end_time.setText("text");
            time_info.add(end_time, "cell 3 0");
        }
        contentPane.add(time_info, "cell 0 1");

        //======== A1 ========
        {
            A1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[1326,fill]",
                // rows
                "[340]"));
        }
        contentPane.add(A1, "cell 0 2");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    public JPanel name_info;
    public JLabel Patient_name;
    public JLabel signal_type;
    public JPanel time_info;
    public JLabel Date;
    public JLabel start_time;
    private JLabel to_label;
    public JLabel end_time;
    public JPanel A1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}