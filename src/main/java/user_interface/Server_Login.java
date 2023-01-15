/*
 * Created by JFormDesigner on Sun Jan 15 20:41:54 GMT 2023
 */

package user_interface;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import net.miginfocom.swing.*;

import static netRelated.netAction.*;

/**
 * get the addresses, username and password, and check whether they are valid
 * @author Tianyu
 */
public class Server_Login extends JFrame {
    public Server_Login() {
        initComponents();
    }
    private void login_save_(ActionEvent e) {
        try {
            //set the connections to the servlet and database
            URL url = new URL(this.servlet_address.getText());
            URLConnection conn = url.openConnection();
            conn.connect();
            Connection connection = DriverManager.getConnection(this.database_address.getText(), this.username.getText(), this.user_password.getText());
            connection.close();

            //save them if connected
            servletUrl=this.servlet_address.getText()+"/docScope_s/data";
            dbUrl=this.database_address.getText();
            userName=this.username.getText();
            password=this.user_password.getText();
            //start the main panel
            GUI_test mainPanel=new GUI_test();
            mainPanel.setVisible(true);
            mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        } catch (Exception t) {
            //pop warning window if connections failed
            Login_Error loginError = new Login_Error();
            loginError.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        label1 = new JLabel();
        servlet_address = new JTextField();
        label6 = new JLabel();
        label2 = new JLabel();
        database_address = new JTextField();
        panel1 = new JPanel();
        label4 = new JLabel();
        username = new JTextField();
        label5 = new JLabel();
        user_password = new JTextField();
        login_save_button = new JButton();
        login_save_button.setOpaque(true);
        login_save_button.setBorderPainted(false);

        //======== this ========
        setMaximumSize(new Dimension(800, 400));
        setMinimumSize(new Dimension(800, 400));
        setPreferredSize(new Dimension(800, 400));
        setResizable(false);
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[787,fill]",
            // rows
            "[]" +
            "[57]" +
            "[]" +
            "[69]" +
            "[70]" +
            "[85]"));

        //---- label1 ----
        label1.setText("Servlet Address");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(label1, "cell 0 0,align center center,grow 0 0");

        //---- servlet_address ----
        servlet_address.setText("http://localhost:8080/");
        servlet_address.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(servlet_address, "cell 0 1");

        //---- label6 ----
        label6.setText("/docScope_s/data");
        label6.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(label6, "cell 0 1");

        //---- label2 ----
        label2.setText("Database Address");
        label2.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(label2, "cell 0 2,align center center,grow 0 0");

        //---- database_address ----
        database_address.setText("jdbc:postgresql://localhost:5432/postgres");
        database_address.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(database_address, "cell 0 3");

        //======== panel1 ========
        {
            panel1.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[73,fill]" +
                "[288,fill]" +
                "[85,fill]" +
                "[283,fill]",
                // rows
                "[]"));

            //---- label4 ----
            label4.setText("Database Username");
            label4.setFont(new Font("Arial", Font.PLAIN, 14));
            panel1.add(label4, "cell 0 0,alignx center,growx 0");

            //---- username ----
            username.setText("postgres");
            username.setFont(new Font("Arial", Font.PLAIN, 16));
            panel1.add(username, "cell 1 0");

            //---- label5 ----
            label5.setText("password");
            label5.setFont(new Font("Arial", Font.PLAIN, 14));
            panel1.add(label5, "cell 2 0,alignx center,growx 0");

            //---- user_password ----
            user_password.setText("1234");
            user_password.setFont(new Font("Arial", Font.PLAIN, 16));
            panel1.add(user_password, "cell 3 0");
        }
        contentPane.add(panel1, "cell 0 4");

        //---- login_save_button ----
        login_save_button.setText("Log In");
        login_save_button.setMaximumSize(new Dimension(150, 30));
        login_save_button.setMinimumSize(new Dimension(150, 30));
        login_save_button.setPreferredSize(new Dimension(150, 30));
        login_save_button.setFont(new Font("Arial", Font.PLAIN, 16));
        login_save_button.setBackground(Color.gray);
        login_save_button.addActionListener(e -> login_save_(e));
        contentPane.add(login_save_button, "cell 0 5,align center center,grow 0 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JLabel label1;
    public JTextField servlet_address;
    private JLabel label6;
    private JLabel label2;
    public JTextField database_address;
    private JPanel panel1;
    private JLabel label4;
    public JTextField username;
    private JLabel label5;
    public JTextField user_password;
    public JButton login_save_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
