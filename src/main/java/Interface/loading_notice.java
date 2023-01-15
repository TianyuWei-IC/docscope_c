/*
 * Created by JFormDesigner on Sat Jan 14 23:15:06 GMT 2023
 */

package Interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class loading_notice extends JFrame {
    public loading_notice() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
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
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
