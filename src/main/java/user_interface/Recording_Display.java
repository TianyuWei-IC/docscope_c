/*
 * Created by JFormDesigner on Sun Jan 08 19:48:24 GMT 2023
 */

package user_interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * this is a JFrame that display the recorded signal
 */
public class Recording_Display extends JFrame {
    public Recording_Display() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license 
        name_info = new JPanel();
        panel1 = new JPanel();
        Patient_name = new JLabel();
        signal_type = new JLabel();
        time_info = new JPanel();
        panel2 = new JPanel();
        Date = new JLabel();
        start_time = new JLabel();
        to_label = new JLabel();
        end_time = new JLabel();
        notice = new JLabel();
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
                "[fill]" +
                "[155,fill]" +
                "[195,fill]",
                // rows
                "[44]"));

            //======== panel1 ========
            {
                panel1.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]",
                    // rows
                    "[]"));
            }
            name_info.add(panel1, "cell 0 0");

            //---- Patient_name ----
            Patient_name.setText("text");
            Patient_name.setFont(new Font("Arial", Font.PLAIN, 16));
            name_info.add(Patient_name, "cell 1 0");

            //---- signal_type ----
            signal_type.setText("text");
            signal_type.setFont(new Font("Arial", Font.PLAIN, 16));
            name_info.add(signal_type, "cell 2 0");
        }
        contentPane.add(name_info, "cell 0 0");

        //======== time_info ========
        {
            time_info.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[fill]" +
                "[155,fill]" +
                "[51,fill]" +
                "[34,fill]" +
                "[60,fill]" +
                "[fill]" +
                "[fill]" +
                "[444,fill]",
                // rows
                "[]"));

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[fill]",
                    // rows
                    "[]"));
            }
            time_info.add(panel2, "cell 0 0");

            //---- Date ----
            Date.setText("text");
            Date.setFont(new Font("Arial", Font.PLAIN, 14));
            time_info.add(Date, "cell 1 0");

            //---- start_time ----
            start_time.setText("text");
            start_time.setFont(new Font("Arial", Font.PLAIN, 14));
            time_info.add(start_time, "cell 2 0");

            //---- to_label ----
            to_label.setText("to");
            to_label.setFont(new Font("Arial", Font.PLAIN, 14));
            time_info.add(to_label, "cell 3 0");

            //---- end_time ----
            end_time.setText("text");
            end_time.setFont(new Font("Arial", Font.PLAIN, 14));
            time_info.add(end_time, "cell 4 0");

            //---- notice ----
            notice.setText("* If you see nothing showing, it is very likely there is no recording during that period, please adjust and try again");
            notice.setFont(new Font("Arial", Font.BOLD, 12));
            notice.setForeground(Color.red);
            time_info.add(notice, "cell 7 0");
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
        contentPane.add(A1, "cell 0 2,gapy 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license 
    public JPanel name_info;
    private JPanel panel1;
    public JLabel Patient_name;
    public JLabel signal_type;
    public JPanel time_info;
    private JPanel panel2;
    public JLabel Date;
    public JLabel start_time;
    private JLabel to_label;
    public JLabel end_time;
    private JLabel notice;
    public JPanel A1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
