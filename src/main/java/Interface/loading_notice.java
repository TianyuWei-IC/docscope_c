/*
 * Created by JFormDesigner on Sat Jan 14 20:03:20 GMT 2023
 */

package Interface;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author Tianyu
 */
public class loading_notice extends Window {
    public loading_notice(Window owner) {
        super(owner);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        loading_text = new JLabel();

        //======== this ========
        setAlwaysOnTop(true);
        setBackground(Color.gray);
        setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
        setVisible(true);
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[766,fill]",
            // rows
            "[144]"));

        //---- loading_text ----
        loading_text.setText("Saving the information of the patient, it might take several seconds...");
        loading_text.setForeground(Color.black);
        loading_text.setBackground(Color.white);
        add(loading_text, "cell 0 0,align center center,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JLabel loading_text;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
