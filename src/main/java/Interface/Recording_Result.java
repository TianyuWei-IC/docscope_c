/*
 * Created by JFormDesigner on Sun Jan 08 19:48:24 GMT 2023
 */

package Interface;

import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class Recording_Result extends JFrame {
    public Recording_Result() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei 
        panel1 = new JPanel();
        Patient_name = new JLabel();
        signal_type = new JLabel();
        panel2 = new JPanel();
        Date = new JLabel();
        start_time = new JLabel();
        to_label = new JLabel();
        end_time = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[375,fill]",
            // rows
            "[]" +
            "[]" +
            "[207]"));

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[169,fill]" +
                "[195,fill]",
                // rows
                "[44]"));

            //---- Patient_name ----
            Patient_name.setText("text");
            panel1.add(Patient_name, "cell 0 0");

            //---- signal_type ----
            signal_type.setText("text");
            panel1.add(signal_type, "cell 1 0");
        }
        contentPane.add(panel1, "cell 0 0");

        //======== panel2 ========
        {
            panel2.setLayout(new MigLayout(
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
            panel2.add(Date, "cell 0 0");

            //---- start_time ----
            start_time.setText("text");
            panel2.add(start_time, "cell 1 0");

            //---- to_label ----
            to_label.setText("to");
            panel2.add(to_label, "cell 2 0");

            //---- end_time ----
            end_time.setText("text");
            panel2.add(end_time, "cell 3 0");
        }
        contentPane.add(panel2, "cell 0 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei 
    private JPanel panel1;
    public JLabel Patient_name;
    public JLabel signal_type;
    private JPanel panel2;
    public JLabel Date;
    public JLabel start_time;
    private JLabel to_label;
    public JLabel end_time;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
