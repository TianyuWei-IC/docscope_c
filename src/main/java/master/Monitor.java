package master;

import user_interface.Server_Login;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
                // create the GUI and set visible and exit on close
//                GUI_test mainPanel=new GUI_test();
//                mainPanel.setVisible(true);
//                mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Server_Login login = new Server_Login();
                login.setVisible(true);
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}