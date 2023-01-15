/*
 * Created by JFormDesigner on Sat Jan 14 19:43:50 GMT 2023
 */
package user_interface;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * this is a JFrame that informs the doctor
 */
public class Report_Notice extends JFrame {
    public Report_Notice() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        label1 = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setMinimumSize(new Dimension(500, 130));
        setPreferredSize(new Dimension(500, 130));
        setMaximumSize(new Dimension(500, 130));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3,align center center",
            // columns
            "[506,fill]",
            // rows
            "[247]"));

        //---- label1 ----
        label1.setText("The report has been downloaded to your Desktop!");
        label1.setFont(new Font("Arial", Font.PLAIN, 17));
        label1.setForeground(Color.black);
        label1.setBackground(Color.white);
        contentPane.add(label1, "cell 0 0,align center center,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
