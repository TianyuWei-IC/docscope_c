package master;

import Interface.GUI_test;
import Interface.Urgent;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.ZoneId;
import java.util.TimeZone;


public class Monitor {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            // Registers the driver
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
        }
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
                mainPanel.real_time_plots.setEnabled(false);
                mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainPanel.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }
}