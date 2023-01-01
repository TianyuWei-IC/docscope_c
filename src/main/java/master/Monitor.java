package master;

import Interface.Urgent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.ZoneId;
import java.util.TimeZone;

public class Monitor {
    public static void main(String[] args) throws IOException, InterruptedException {
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



//                GUI_test mainPanel=new GUI_test();
//                mainPanel.setVisible(true);
//                mainPanel.real_time_plots.setEnabled(false);
//                mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                mainPanel.setExtendedState(JFrame.MAXIMIZED_BOTH);

                Urgent newwar = new Urgent();
                newwar.setVisible(true);
                newwar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                inputData model = new inputData(tempData);
//
//                JFrame frame = new JFrame("Testing");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                JPanel newpan = new JPanel();
//                newpan.setLayout(new GridLayout(1, 1));
//                newpan.add(new SeriesChartPane(model,timestamp));
//
//                frame.add(newpan);
//                frame.setSize(500,600);
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);


            }
        });
    }
}