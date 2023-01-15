/*
 * Created by JFormDesigner on Sun Jan 15 21:43:58 GMT 2023
 */

package user_interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class Login_Error extends JFrame {
    public Login_Error() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        label1 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(400, 200));
        setPreferredSize(new Dimension(400, 200));
        setMaximumSize(new Dimension(400, 200));
        setResizable(false);
        setAlwaysOnTop(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[579,fill]",
            // rows
            "[288]"));

        //---- label1 ----
        label1.setText("ERROR : Wrong Addresses, Username or Password");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
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
