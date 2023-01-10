/*
 * Created by JFormDesigner on Thu Jan 05 23:03:55 GMT 2023
 */

package Interface;

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
        // Generated using JFormDesigner Educational license - Tianyu Wei 
        recordimg_frame = new JFrame();
        panel1 = new JPanel();
        Patient_name = new JLabel();
        signal_type = new JLabel();
        panel2 = new JPanel();
        date = new JLabel();
        start_time = new JLabel();
        to_label = new JLabel();
        end_time = new JLabel();
        chart_generated = new JPanel();

        //======== recordimg_frame ========
        {
            var recordimg_frameContentPane = recordimg_frame.getContentPane();
            recordimg_frameContentPane.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[786,fill]",
                // rows
                "[97]" +
                "[58]" +
                "[146]"));

            //======== panel1 ========
            {
                panel1.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[255,fill]" +
                    "[293,fill]",
                    // rows
                    "[81]"));

                //---- Patient_name ----
                Patient_name.setText("text");
                panel1.add(Patient_name, "cell 0 0");

                //---- signal_type ----
                signal_type.setText("text");
                panel1.add(signal_type, "cell 1 0");
            }
            recordimg_frameContentPane.add(panel1, "cell 0 0");

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[196,fill]" +
                    "[184,fill]" +
                    "[189,fill]" +
                    "[177,fill]",
                    // rows
                    "[41]"));

                //---- date ----
                date.setText("text");
                panel2.add(date, "cell 0 0");

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
            recordimg_frameContentPane.add(panel2, "cell 0 1");

            //======== chart_generated ========
            {
                chart_generated.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[1134,fill]",
                    // rows
                    "[450]"));
            }
            recordimg_frameContentPane.add(chart_generated, "cell 0 2");
            recordimg_frame.pack();
            recordimg_frame.setLocationRelativeTo(recordimg_frame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei 
    private JFrame recordimg_frame;
    private JPanel panel1;
    private JLabel Patient_name;
    private JLabel signal_type;
    private JPanel panel2;
    private JLabel date;
    private JLabel start_time;
    private JLabel to_label;
    private JLabel end_time;
    private JPanel chart_generated;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
