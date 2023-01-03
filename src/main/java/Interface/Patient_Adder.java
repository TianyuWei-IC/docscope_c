package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import master.Patient;
import net.miginfocom.swing.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


/*

 * Created by JFormDesigner on Tue Dec 27 20:41:28 GMT 2022
 */



/**
 * @author Tianyu
 */
public class Patient_Adder extends JFrame {
    private GUI_test mainGUI;
    public Patient_Adder(GUI_test mainGUI) {
        this.mainGUI = mainGUI;
        initComponents();
    }

    private void save_button(ActionEvent e) {

        String gender = "male";
        if (female_button.isSelected()){
            gender = "female";
        }else{
            gender = "male";
        }
        // creates a master.Patient object contains all patient's parameters
        Patient new_patient = new Patient(
                first_name_field.getText(),
                last_name_field.getText(),
                (String) ref_selector.getSelectedItem(),
                gender,
                (Integer) year_selector.getSelectedItem(),
                parseDouble(temp_min.getText()),
                parseDouble(temp_max.getText()),
                parseInt(hr_min.getText()),
                parseInt(hr_max.getText()),
                parseInt(sys_min.getText()),
                parseInt(sys_max.getText()),
                parseInt(dia_min.getText()),
                parseInt(dia_max.getText()),
                parseInt(resp_min.getText()),
                parseInt(resp_max.getText()),
                this.mainGUI
        );


        mainGUI.patient_list.add(new_patient);
        mainGUI.patient_list.updateUI();
        this.mainGUI.add_new_patient.setEnabled(true);
        this.dispose();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void PatientAdderWindowClosing(WindowEvent e) {
        this.mainGUI.add_new_patient.setEnabled(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei
        patient_editor_main_panel = new JPanel();
        panel1 = new JPanel();
        title = new JLabel();
        patient_ref = new JLabel();
        String[] patient_ref_pack = new String[3];
        patient_ref_pack[0] = "shuyu_yao_28/12/22";
        patient_ref_pack[1] = "jj_lin_08/10/22";
        patient_ref_pack[2] = "kyrie_sun_03/02/22";
        ref_selector = new JComboBox(patient_ref_pack);
        patient_profile = new JPanel();
        name = new JPanel();
        first_name = new JLabel();
        first_name_field = new JTextField();
        last_name = new JLabel();
        last_name_field = new JTextField();
        gender = new JPanel();
        gender_label = new JLabel();
        male_button = new JRadioButton();
        female_button = new JRadioButton();
        birthday = new JPanel();
        YoB = new JLabel();
        Integer[] years = new Integer[124];
            for (int i=0; i < 124; i++){
            	int the_year = -i+2023;
                years[i]=the_year;
        }
        year_selector = new JComboBox(years);
        reminder = new JLabel();
        threshold = new JPanel();
        min_value = new JLabel();
        max_value = new JLabel();
        body_temperature = new JLabel();
        temp_min = new JTextField();
        temp_max = new JTextField();
        degree = new JLabel();
        heart_rate = new JLabel();
        hr_min = new JTextField();
        hr_max = new JTextField();
        BPM = new JLabel();
        blood_pressure = new JLabel();
        systolic = new JLabel();
        sys_min = new JTextField();
        sys_max = new JTextField();
        mmHg_high = new JLabel();
        diastolic = new JLabel();
        dia_min = new JTextField();
        dia_max = new JTextField();
        mmHg_low = new JLabel();
        respiratory_rate = new JLabel();
        resp_min = new JTextField();
        resp_max = new JTextField();
        per_min = new JLabel();
        save_button = new JButton();
        save_button.setOpaque(true);
        save_button.setBorderPainted(false);

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PatientAdderWindowClosing(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== patient_editor_main_panel ========
        {
            patient_editor_main_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[815,fill]",
                // rows
                "[60]" +
                "[127]" +
                "[54]" +
                "[261]" +
                "[64]"));

            //======== panel1 ========
            {
                panel1.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[281,fill]" +
                    "[299,fill]",
                    // rows
                    "[104]"));

                //---- title ----
                title.setText("Patient");
                title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
                panel1.add(title, "cell 0 0");

                //---- patient_ref ----
                patient_ref.setText("Patient Reference: ");
                panel1.add(patient_ref, "cell 1 0");
                panel1.add(ref_selector, "cell 1 0");
            }
            patient_editor_main_panel.add(panel1, "cell 0 0");

            //======== patient_profile ========
            {
                patient_profile.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[720,fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]" +
                    "[]"));

                //======== name ========
                {
                    name.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[63,fill]" +
                        "[205,fill]" +
                        "[63,fill]" +
                        "[211,fill]",
                        // rows
                        "[]"));

                    //---- first_name ----
                    first_name.setText("First Name:");
                    name.add(first_name, "cell 0 0");
                    name.add(first_name_field, "cell 1 0");

                    //---- last_name ----
                    last_name.setText("Last Name:");
                    name.add(last_name, "cell 2 0");
                    name.add(last_name_field, "cell 3 0");
                }
                patient_profile.add(name, "cell 0 0");

                //======== gender ========
                {
                    gender.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[147,fill]" +
                        "[139,fill]" +
                        "[141,fill]",
                        // rows
                        "[]"));

                    //---- gender_label ----
                    gender_label.setText("Gender:");
                    gender.add(gender_label, "cell 0 0");

                    //---- male_button ----
                    male_button.setText("Male");
                    male_button.setSelected(true);
                    gender.add(male_button, "cell 1 0");

                    //---- female_button ----
                    female_button.setText("Female");
                    gender.add(female_button, "cell 1 0");
                }
                patient_profile.add(gender, "cell 0 2");

                //======== birthday ========
                {
                    birthday.setLayout(new MigLayout(
                        "hidemode 3",
                        // columns
                        "[108,fill]" +
                        "[134,fill]",
                        // rows
                        "[33]" +
                        "[]"));

                    //---- YoB ----
                    YoB.setText("Year of Birth:");
                    birthday.add(YoB, "cell 0 1");
                    birthday.add(year_selector, "cell 1 1");
                }
                patient_profile.add(birthday, "cell 0 3");
            }
            patient_editor_main_panel.add(patient_profile, "cell 0 1");

            //---- reminder ----
            reminder.setText("Based on the age of the patient, the thresholds are set to be:");
            reminder.setFont(reminder.getFont().deriveFont(reminder.getFont().getSize() + 2f));
            reminder.setHorizontalAlignment(SwingConstants.LEFT);
            patient_editor_main_panel.add(reminder, "cell 0 2");

            //======== threshold ========
            {
                threshold.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[220,fill]" +
                    "[63,fill]" +
                    "[120,fill]" +
                    "[110,fill]" +
                    "[105,fill]",
                    // rows
                    "[36]" +
                    "[39]" +
                    "[36]" +
                    "[38]" +
                    "[38]" +
                    "[37]"));

                //---- min_value ----
                min_value.setText("Min");
                min_value.setHorizontalTextPosition(SwingConstants.CENTER);
                min_value.setHorizontalAlignment(SwingConstants.CENTER);
                threshold.add(min_value, "cell 2 0");

                //---- max_value ----
                max_value.setText("Max");
                max_value.setHorizontalTextPosition(SwingConstants.CENTER);
                max_value.setHorizontalAlignment(SwingConstants.CENTER);
                threshold.add(max_value, "cell 3 0");

                //---- body_temperature ----
                body_temperature.setText("Body Temperature:");
                threshold.add(body_temperature, "cell 0 1");

                //---- temp_min ----
                temp_min.setText("35.0");
                threshold.add(temp_min, "cell 2 1");

                //---- temp_max ----
                temp_max.setText("40.0");
                threshold.add(temp_max, "cell 3 1");

                //---- degree ----
                degree.setText("\u2103");
                threshold.add(degree, "cell 4 1");

                //---- heart_rate ----
                heart_rate.setText("Heart Rate:");
                threshold.add(heart_rate, "cell 0 2");

                //---- hr_min ----
                hr_min.setText("40");
                threshold.add(hr_min, "cell 2 2");

                //---- hr_max ----
                hr_max.setText("100");
                threshold.add(hr_max, "cell 3 2");

                //---- BPM ----
                BPM.setText("BPM");
                threshold.add(BPM, "cell 4 2");

                //---- blood_pressure ----
                blood_pressure.setText("Blood Pressure:");
                threshold.add(blood_pressure, "cell 0 3");

                //---- systolic ----
                systolic.setText("Systolic:");
                threshold.add(systolic, "cell 1 3");

                //---- sys_min ----
                sys_min.setText("90");
                threshold.add(sys_min, "cell 2 3");

                //---- sys_max ----
                sys_max.setText("140");
                threshold.add(sys_max, "cell 3 3");

                //---- mmHg_high ----
                mmHg_high.setText("mmHg");
                threshold.add(mmHg_high, "cell 4 3");

                //---- diastolic ----
                diastolic.setText("Diastolic:");
                threshold.add(diastolic, "cell 1 4");

                //---- dia_min ----
                dia_min.setText("60");
                threshold.add(dia_min, "cell 2 4");

                //---- dia_max ----
                dia_max.setText("90");
                threshold.add(dia_max, "cell 3 4");

                //---- mmHg_low ----
                mmHg_low.setText("mmHg");
                threshold.add(mmHg_low, "cell 4 4");

                //---- respiratory_rate ----
                respiratory_rate.setText("Respiratory Rate:");
                threshold.add(respiratory_rate, "cell 0 5");

                //---- resp_min ----
                resp_min.setText("10");
                threshold.add(resp_min, "cell 2 5");

                //---- resp_max ----
                resp_max.setText("35");
                threshold.add(resp_max, "cell 3 5");

                //---- per_min ----
                per_min.setText("/min");
                threshold.add(per_min, "cell 4 5");
            }
            patient_editor_main_panel.add(threshold, "cell 0 3");

            //---- save_button ----
            save_button.setText("Save");
            save_button.setPreferredSize(new Dimension(130, 50));
            save_button.setMinimumSize(new Dimension(130, 50));
            save_button.setMaximumSize(new Dimension(130, 50));
            save_button.setHorizontalTextPosition(SwingConstants.CENTER);
            save_button.setForeground(Color.white);
            save_button.setFont(save_button.getFont().deriveFont(save_button.getFont().getSize() + 2f));
            save_button.setBackground(new Color(0x3164f4));
            save_button.addActionListener(e -> save_button(e));
            patient_editor_main_panel.add(save_button, "cell 0 4,align center center,grow 0 0");
        }
        contentPane.add(patient_editor_main_panel);
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(male_button);
        buttonGroup1.add(female_button);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - Tianyu Wei
    private JPanel patient_editor_main_panel;
    private JPanel panel1;
    private JLabel title;
    private JLabel patient_ref;
    public JComboBox ref_selector;
    private JPanel patient_profile;
    private JPanel name;
    public JLabel first_name;
    public JTextField first_name_field;
    private JLabel last_name;
    public JTextField last_name_field;
    private JPanel gender;
    private JLabel gender_label;
    public JRadioButton male_button;
    public JRadioButton female_button;
    public JPanel birthday;
    private JLabel YoB;
    public JComboBox year_selector;
    private JLabel reminder;
    private JPanel threshold;
    private JLabel min_value;
    private JLabel max_value;
    private JLabel body_temperature;
    public JTextField temp_min;
    public JTextField temp_max;
    private JLabel degree;
    private JLabel heart_rate;
    public JTextField hr_min;
    public JTextField hr_max;
    private JLabel BPM;
    private JLabel blood_pressure;
    private JLabel systolic;
    public JTextField sys_min;
    public JTextField sys_max;
    private JLabel mmHg_high;
    private JLabel diastolic;
    public JTextField dia_min;
    public JTextField dia_max;
    private JLabel mmHg_low;
    private JLabel respiratory_rate;
    public JTextField resp_min;
    public JTextField resp_max;
    private JLabel per_min;
    public JButton save_button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
