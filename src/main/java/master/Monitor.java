package master;

import Interface.GUI_test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Monitor {
    public static String[] referenceList = new String[]{"patient1","patient2"};
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
        //this.referenceList=new String[]{"patient1","patient2"};
        new Monitor();
    }
    public Monitor() throws IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                GUI_test mainPanel=new GUI_test();
                mainPanel.setVisible(true);
                mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainPanel.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}