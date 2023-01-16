/*
 * Created by JFormDesigner on Sat Jan 14 23:15:06 GMT 2023
 */

package user_interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 *  this is a JFrame showing the doctors after saving or the patient info, it might take several seconds to communicate
 *  with the server.
 *
 */
public class Loading_Notice extends JFrame {
    public Loading_Notice() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license 
        label1 = new JLabel();

        //======== this ========
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[548,fill]",
            // rows
            "[88]"));

        //---- label1 ----
        label1.setText("Handling the information of the patient, it might take several seconds...");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(label1, "cell 0 0,align center center,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license 
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
