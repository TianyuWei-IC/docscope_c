package Interface;

import master.Patient;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.Integer.parseInt;


/*

 * Created by JFormDesigner on Tue Dec 27 20:41:28 GMT 2022
 */


/**
 * @author Tianyu
 */
public class Patient_Editor extends JFrame {
    private GUI_test mainGUI;
    private Patient new_patient;
    public Patient_Editor(GUI_test mainGUI, Patient new_patient) {
        this.mainGUI = mainGUI;
        this.new_patient = new_patient;
        initComponents();

        this.first_name_field.setText(this.new_patient.first_name);
        this.last_name_field.setText(this.new_patient.last_name);
        System.out.println(this.new_patient.gender);
        if (this.new_patient.gender=="male"){
            this.male_button.setSelected(true);
        }else{
            this.female_button.setSelected(true);
        }

        this.ref_selector.setSelectedItem(this.new_patient.reference_value);
        this.year_selector.setSelectedItem(this.new_patient.year_of_birth);
        this.temp_min.setText(String.valueOf(this.new_patient.temp_min));
        this.temp_max.setText(String.valueOf(this.new_patient.temp_max));
        this.hr_min.setText(String.valueOf(this.new_patient.hr_min));
        this.hr_max.setText(String.valueOf(this.new_patient.hr_max));
        this.sys_min.setText(String.valueOf(this.new_patient.sys_min));
        this.sys_max.setText(String.valueOf(this.new_patient.sys_max));
        this.dia_min.setText(String.valueOf(this.new_patient.dia_min));
        this.dia_max.setText(String.valueOf(this.new_patient.dia_max));
        this.resp_min.setText(String.valueOf(this.new_patient.resp_min));
        this.resp_max.setText(String.valueOf(this.new_patient.resp_max));
        mainGUI.patient_list.add(this.new_patient);
        mainGUI.patient_list.updateUI();
    }

    private void save_button(ActionEvent e) {

//        String gender = "male";
//        if (female_button.isSelected()){
//            gender = "female";
//        }

        this.new_patient.first_name = this.first_name_field.getText();
        this.new_patient.last_name = this.last_name_field.getText();

        if (male_button.isSelected()){
            this.new_patient.gender = "male";
        }else{
            this.new_patient.gender = "female";
        }

        this.new_patient.reference_value = (String) this.ref_selector.getSelectedItem();
        this.new_patient.year_of_birth = (Integer) this.year_selector.getSelectedItem();
        this.new_patient.temp_max= Double.valueOf(this.temp_max.getText());
        this.new_patient.temp_min= Double.valueOf(this.temp_min.getText());
        this.new_patient.hr_max= Integer.valueOf(this.hr_max.getText());
        this.new_patient.hr_min= Integer.valueOf(this.hr_min.getText());
        this.new_patient.sys_max= Integer.valueOf(this.sys_max.getText());
        this.new_patient.sys_min= Integer.valueOf(this.sys_min.getText());
        this.new_patient.dia_max= Integer.valueOf(this.dia_max.getText());
        this.new_patient.dia_min= Integer.valueOf(this.dia_min.getText());
        this.new_patient.resp_max= Integer.valueOf(this.resp_max.getText());
        this.new_patient.resp_min= Integer.valueOf(this.resp_min.getText());


        String new_patient_full_name = this.new_patient.first_name+" "+this.new_patient.last_name;
        this.new_patient.setText("<html>" + new_patient_full_name.replaceAll("<break>", "<br>") + "</html>");
        mainGUI.patient_list.updateUI();
        this.dispose();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void delete_button(ActionEvent e) {
        mainGUI.patient_list.remove(this.new_patient);
        mainGUI.patient_list.updateUI();
        this.dispose();
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
        panel2 = new JPanel();
        save_button = new JButton();
        save_button.setOpaque(true);
        save_button.setBorderPainted(false);
        button1 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        //======== patient_editor_main_panel ========
        {
            patient_editor_main_panel.setLayout(new MigLayout(
                "hidemode 3",
                // columns
                "[810,fill]",
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
                title.setText("master.Patient");
                title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 6f));
                panel1.add(title, "cell 0 0");

                //---- patient_ref ----
                patient_ref.setText("master.Patient Reference: ");
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
                temp_min.setText("36.4");
                threshold.add(temp_min, "cell 2 1");

                //---- temp_max ----
                temp_max.setText("38.0");
                threshold.add(temp_max, "cell 3 1");

                //---- degree ----
                degree.setText("\u2103");
                threshold.add(degree, "cell 4 1");

                //---- heart_rate ----
                heart_rate.setText("Heart Rate:");
                threshold.add(heart_rate, "cell 0 2");

                //---- hr_min ----
                hr_min.setText("60");
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
                sys_max.setText("120");
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
                dia_max.setText("80");
                threshold.add(dia_max, "cell 3 4");

                //---- mmHg_low ----
                mmHg_low.setText("mmHg");
                threshold.add(mmHg_low, "cell 4 4");

                //---- respiratory_rate ----
                respiratory_rate.setText("Respiratory Rate:");
                threshold.add(respiratory_rate, "cell 0 5");

                //---- resp_min ----
                resp_min.setText("12");
                threshold.add(resp_min, "cell 2 5");

                //---- resp_max ----
                resp_max.setText("20");
                threshold.add(resp_max, "cell 3 5");

                //---- per_min ----
                per_min.setText("/min");
                threshold.add(per_min, "cell 4 5");
            }
            patient_editor_main_panel.add(threshold, "cell 0 3");

            //======== panel2 ========
            {
                panel2.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[382,fill]" +
                    "[358,fill]",
                    // rows
                    "[]"));

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
                panel2.add(save_button, "cell 0 0,align right center,grow 0 0");

                //---- button1 ----
                button1.setText("Delete This master.Patient");
                button1.setMaximumSize(new Dimension(130, 50));
                button1.setMinimumSize(new Dimension(130, 50));
                button1.setPreferredSize(new Dimension(130, 50));
                button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 2f));
                button1.addActionListener(e -> delete_button(e));
                panel2.add(button1, "cell 1 0");
            }
            patient_editor_main_panel.add(panel2, "cell 0 4");
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
    private JPanel panel2;
    public JButton save_button;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
