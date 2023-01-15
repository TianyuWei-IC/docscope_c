package Interface;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import java.util.List;
import chartPanel.Chart_Label_Display;
import chartPanel.Display_Chart;
import master.Patient;
import net.miginfocom.swing.*;
import netRelated.netAction;
import static java.awt.event.WindowEvent.WINDOW_CLOSED;
import static java.lang.Double.parseDouble;
/*

 * Created by JFormDesigner on Tue Dec 27 20:41:28 GMT 2022
 */

/**
 *
 * this is a JFrame that adds the patient into the patien_list
 */
public class Patient_Adder extends JFrame {
    private GUI_test mainGUI;
    private Boolean save_or_not = false;

    private loading_notice loading = new loading_notice();
    private ActionEvent click;

    public Patient_Adder(GUI_test mainGUI) {
        this.mainGUI = mainGUI;
        loading.setVisible(false);
        initComponents();
    }

    private void save_button(ActionEvent e) {
        this.click = e;
        System.out.println(loading.isVisible());
        loading.setVisible(true);
        loading.setAlwaysOnTop(true);
        this.save_or_not = true;
        this.dispose();
        WindowEvent event = new WindowEvent(this,WINDOW_CLOSED);
        PatientAdderWindowClosing(event);
    }

    private void enableDisplaySettings(){
        this.mainGUI.ECG_display_interval.setEditable(true);
        this.mainGUI.Temp_display_interval.setEditable(true);
        this.mainGUI.HR_display_interval.setEditable(true);
        this.mainGUI.BP_display_interval.setEditable(true);
        this.mainGUI.RESP_pattern_display_interval.setEditable(true);
        this.mainGUI.RESP_rate_display_interval.setEditable(true);

        this.mainGUI.ECG_update_button.setEnabled(true);
        this.mainGUI.HR_update_button.setEnabled(true);
        this.mainGUI.Temp_update_button.setEnabled(true);
        this.mainGUI.BP_update_button.setEnabled(true);
        this.mainGUI.RESP_pattern_update_button.setEnabled(true);
        this.mainGUI.RESP_rate_update_button.setEnabled(true);

        this.mainGUI.recordings.setEnabled(true);
        this.mainGUI.report_button.setEnabled(true);
    }

    private void thisWindowClosed(WindowEvent e) throws InterruptedException {
        if(save_or_not) {
            String gender = "male";
            if (female_button.isSelected()){
                gender = "female";
            }else{
                gender = "male";
            }
            if (mainGUI.patient_list.getComponentCount()!=0){
                Display_Chart current_Temp = (Display_Chart) mainGUI.body_temp_table.getComponent(0);
                Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();

                Patient previous_patient = current_temp_cl_display.patient;
                previous_patient.panelEcg1.worker.cancel(true);
                previous_patient.panelEcg2.worker.cancel(true);
                previous_patient.panelTemperature.updater.cancel(true);
                previous_patient.panelRespiratoryRate.updater.cancel(true);
                previous_patient.panelDiaBloodPressure.updater.cancel(true);
                previous_patient.panelSysBloodPressure.updater.cancel(true);
                previous_patient.panelRespiratoryPattern.worker.cancel(true);
                previous_patient.panelHeartRate.updater.cancel(true);
                previous_patient.setBackground(new Color(193, 211, 224));
                previous_patient.setOpaque(true);
                previous_patient.setBorderPainted(false);
            }else{
                enableDisplaySettings();
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
                    (int) parseDouble(hr_min.getText()),
                    (int) parseDouble(hr_max.getText()),
                    (int) parseDouble(sys_min.getText()),
                    (int) parseDouble(sys_max.getText()),
                    (int) parseDouble(dia_min.getText()),
                    (int) parseDouble(dia_max.getText()),
                    (int) parseDouble(resp_min.getText()),
                    (int) parseDouble(resp_max.getText()),
                    this.mainGUI
            );
            List<Double> threshold=Arrays.asList(new_patient.temp_max,
                    new_patient.temp_min,
                    (double)new_patient.hr_max,
                    (double)new_patient.hr_min,
                    (double)new_patient.sys_max,
                    (double)new_patient.sys_min,
                    (double)new_patient.dia_max,
                    (double)new_patient.dia_min,
                    (double)new_patient.resp_max,
                    (double)new_patient.resp_min);
            netAction.putReference(new_patient.reference_value, threshold,
                    new_patient.first_name, new_patient.last_name, gender, new_patient.year_of_birth);


            mainGUI.patient_list.add(new_patient);
            mainGUI.patient_list.updateUI();
            loading.dispose();

            // now make change the color on the Patient(as a button)
            Display_Chart current_Temp = (Display_Chart) mainGUI.body_temp_table.getComponent(0);
            Chart_Label_Display current_temp_cl_display = current_Temp.find_cl_display();
            Patient current_patient = current_temp_cl_display.patient;
            current_patient.setBackground(new Color(84, 160, 173));
            current_patient.setOpaque(true);
            current_patient.setBorderPainted(false);
        }else {
            loading.dispose();
        }
        this.mainGUI.add_new_patient.setEnabled(true);
    }

    private void PatientAdderWindowClosing(WindowEvent e) {
        this.mainGUI.add_new_patient.setEnabled(true);
    }

    private void temp_minKeyPressed(KeyEvent e) {
        String temp_min_value = temp_min.getText();
        int l = temp_min_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8)|(e.getKeyCode()==46))& !temp_min_value.contains(".")& l<=4) {
            temp_min.setEditable(true);
        }else if(temp_min_value.contains(".")&((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& l<=4){
            temp_min.setEditable(true);
        }else if((e.getKeyCode()==8)& l==5){
            temp_min.setEditable(true);
        }else{
            temp_min.setEditable(false);
        }
    }

    private void temp_maxKeyPressed(KeyEvent e) {
        String temp_max_value = temp_max.getText();
        int l = temp_max_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8)|(e.getKeyCode()==46))& !temp_max_value.contains(".")& l<=4) {
            temp_max.setEditable(true);
        }else if(temp_max_value.contains(".")&((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& l<=4){
            temp_max.setEditable(true);
        }else if((e.getKeyCode()==8)& l==5){
            temp_max.setEditable(true);
        }else{
            temp_max.setEditable(false);
        }
    }

    private void temp_minKeyReleased(KeyEvent e) {
        value_check();
    }

    private void hr_minKeyPressed(KeyEvent e) {
        String hr_min_value = hr_min.getText();
        int l = hr_min_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            hr_min.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            hr_min.setEditable(true);
        }else{
            hr_min.setEditable(false);
        }

    }

    private void hr_maxKeyPressed(KeyEvent e) {
        String hr_max_value = hr_max.getText();
        int l = hr_max_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            hr_max.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            hr_max.setEditable(true);
        }else{
            hr_max.setEditable(false);
        }
    }
    private void sys_minKeyPressed(KeyEvent e) {
        String sys_min_value = sys_min.getText();
        int l = sys_min_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            sys_min.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            sys_min.setEditable(true);
        }else{
            sys_min.setEditable(false);
        }
    }

    private void sys_maxKeyPressed(KeyEvent e) {
        String sys_max_value = sys_max.getText();
        int l = sys_max_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            sys_max.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            sys_max.setEditable(true);
        }else{
            sys_max.setEditable(false);
        }
    }

    private void dia_minKeyPressed(KeyEvent e) {
        String dia_min_value = dia_min.getText();
        int l = dia_min_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            dia_min.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            dia_min.setEditable(true);
        }else{
            dia_min.setEditable(false);
        }
    }

    private void dia_maxKeyPressed(KeyEvent e) {
        String dia_max_value = dia_max.getText();
        int l = dia_max_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            dia_max.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            dia_max.setEditable(true);
        }else{
            dia_max.setEditable(false);
        }
    }

    private void resp_minKeyPressed(KeyEvent e) {
        String resp_min_value = resp_min.getText();
        int l = resp_min_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            resp_min.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            resp_min.setEditable(true);
        }else{
            resp_min.setEditable(false);
        }
    }

    private void resp_maxKeyPressed(KeyEvent e) {
        String resp_max_value = resp_max.getText();
        int l = resp_max_value.length();
        if (((e.getKeyChar() >= '0' && e.getKeyChar() <= '9')|(e.getKeyCode()==8))& (l<=2)) {
            resp_max.setEditable(true);
        }else if((e.getKeyCode()==8)& (l==3)){
            resp_max.setEditable(true);
        }else{
            resp_max.setEditable(false);
        }
    }

    private void value_check(){
        try {
            Double temp_min_value_double = parseDouble(temp_min.getText());
            Double temp_max_value_double = parseDouble(temp_max.getText());
            Double hr_min_value_double = parseDouble(hr_min.getText());
            Double hr_max_value_double = parseDouble(hr_max.getText());
            Double sys_min_value_double = parseDouble(sys_min.getText());
            Double sys_max_value_double = parseDouble(sys_max.getText());
            Double dia_min_value_double = parseDouble(dia_min.getText());
            Double dia_max_value_double = parseDouble(dia_max.getText());
            Double resp_min_value_double = parseDouble(resp_min.getText());
            Double resp_max_value_double = parseDouble(resp_max.getText());

            if((temp_min_value_double>temp_max_value_double)|
                    (hr_min_value_double>hr_max_value_double)|
                    (sys_min_value_double>sys_max_value_double)|
                    (dia_min_value_double>dia_max_value_double)|
                    (resp_min_value_double>resp_max_value_double)|
                    first_name_field.getText().isEmpty()|
                    last_name_field.getText().isEmpty())
            {
                save_button.setEnabled(false);
            }else{
                save_button.setEnabled(true);
            }

        }catch(Exception error) {
            save_button.setEnabled(false);
        }
    }

    private void temp_maxKeyReleased(KeyEvent e) {
        value_check();
    }

    private void hr_minKeyReleased(KeyEvent e) {
        value_check();
    }

    private void hr_maxKeyReleased(KeyEvent e) {
        value_check();
    }

    private void sys_minKeyReleased(KeyEvent e) {
        value_check();
    }

    private void sys_maxKeyReleased(KeyEvent e) {
        value_check();
    }

    private void dia_minKeyReleased(KeyEvent e) {
        value_check();
    }

    private void dia_maxKeyReleased(KeyEvent e) {
        value_check();
    }

    private void resp_minKeyReleased(KeyEvent e) {
        value_check();
    }

    private void resp_maxKeyReleased(KeyEvent e) {
        value_check();
    }

    private void first_name_fieldKeyReleased(KeyEvent e) {
        value_check();
    }

    private void last_name_fieldKeyReleased(KeyEvent e) {
        value_check();
    }


  


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
        patient_editor_main_panel = new JPanel();
        title_and_reference = new JPanel();
        title = new JLabel();
        patient_ref = new JLabel();
        ref_selector = new JComboBox(this.mainGUI.referenceList);
        patient_profile = new JPanel();
        name = new JPanel();
        first_name = new JLabel();
        first_name_field = new JTextField();
        last_name = new JLabel();
        last_name_field = new JTextField();
        label1 = new JLabel();
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
        label2 = new JLabel();
        label3 = new JLabel();
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
        //save_button.disableProperty().bind(this.first_name_field.isEmpty());

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    thisWindowClosed(e);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
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
                "[800,fill]",
                // rows
                "[60]" +
                "[127]" +
                "[7]" +
                "[261]" +
                "[64]"));

            //======== title_and_reference ========
            {
                title_and_reference.setLayout(new MigLayout(
                    "hidemode 3",
                    // columns
                    "[281,fill]" +
                    "[299,fill]",
                    // rows
                    "[104]"));

                //---- title ----
                title.setText("Patient");
                title.setFont(new Font("Arial", Font.PLAIN, 18));
                title_and_reference.add(title, "cell 0 0");

                //---- patient_ref ----
                patient_ref.setText("Patient Reference: ");
                patient_ref.setFont(new Font("Arial", Font.PLAIN, 12));
                title_and_reference.add(patient_ref, "cell 1 0");
                title_and_reference.add(ref_selector, "cell 1 0");
            }
            patient_editor_main_panel.add(title_and_reference, "cell 0 0");

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
                        "[211,fill]" +
                        "[fill]",
                        // rows
                        "[]"));

                    //---- first_name ----
                    first_name.setText("First Name:");
                    first_name.setFont(new Font("Arial", Font.PLAIN, 12));
                    name.add(first_name, "cell 0 0");

                    //---- first_name_field ----
                    first_name_field.setText("TEST");
                    first_name_field.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            first_name_fieldKeyReleased(e);
                        }
                    });
                    name.add(first_name_field, "cell 1 0");

                    //---- last_name ----
                    last_name.setText("Last Name:");
                    last_name.setFont(new Font("Arial", Font.PLAIN, 12));
                    name.add(last_name, "cell 2 0");

                    //---- last_name_field ----
                    last_name_field.setText("TEST");
                    last_name_field.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            last_name_fieldKeyReleased(e);
                        }
                    });
                    name.add(last_name_field, "cell 3 0");

                    //---- label1 ----
                    label1.setText("* First Name and Last name must be filled");
                    label1.setForeground(new Color(0xff0033));
                    label1.setFont(new Font("Arial", Font.PLAIN, 12));
                    name.add(label1, "cell 4 0");
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
                    gender_label.setFont(new Font("Arial", Font.PLAIN, 12));
                    gender.add(gender_label, "cell 0 0");

                    //---- male_button ----
                    male_button.setText("Male");
                    male_button.setSelected(true);
                    male_button.setFont(new Font("Arial", Font.PLAIN, 12));
                    gender.add(male_button, "cell 1 0");

                    //---- female_button ----
                    female_button.setText("Female");
                    female_button.setFont(new Font("Arial", Font.PLAIN, 12));
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
                    YoB.setFont(new Font("Arial", Font.PLAIN, 12));
                    birthday.add(YoB, "cell 0 1");
                    birthday.add(year_selector, "cell 1 1");
                }
                patient_profile.add(birthday, "cell 0 3");
            }
            patient_editor_main_panel.add(patient_profile, "cell 0 1");

            //---- reminder ----
            reminder.setText("The thresholds are set to be:");
            reminder.setFont(new Font("Arial", Font.PLAIN, 12));
            reminder.setHorizontalAlignment(SwingConstants.LEFT);
            patient_editor_main_panel.add(reminder, "cell 0 2");

            //---- label2 ----
            label2.setText("* Max must be greater than Min");
            label2.setForeground(new Color(0xff0033));
            label2.setFont(new Font("Arial", Font.PLAIN, 12));
            patient_editor_main_panel.add(label2, "cell 0 2");
            patient_editor_main_panel.add(label3, "cell 0 2");

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
                min_value.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(min_value, "cell 2 0");

                //---- max_value ----
                max_value.setText("Max");
                max_value.setHorizontalTextPosition(SwingConstants.CENTER);
                max_value.setHorizontalAlignment(SwingConstants.CENTER);
                max_value.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(max_value, "cell 3 0");

                //---- body_temperature ----
                body_temperature.setText("Body Temperature:");
                body_temperature.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(body_temperature, "cell 0 1");

                //---- temp_min ----
                temp_min.setText("35.0");
                temp_min.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        temp_minKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        temp_minKeyReleased(e);
                    }
                });
                threshold.add(temp_min, "cell 2 1");

                //---- temp_max ----
                temp_max.setText("38.0");
                temp_max.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        temp_maxKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        temp_maxKeyReleased(e);
                    }
                });
                threshold.add(temp_max, "cell 3 1");

                //---- degree ----
                degree.setText("\u2103");
                threshold.add(degree, "cell 4 1");

                //---- heart_rate ----
                heart_rate.setText("Heart Rate:");
                heart_rate.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(heart_rate, "cell 0 2");

                //---- hr_min ----
                hr_min.setText("50");
                hr_min.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        hr_minKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        hr_minKeyReleased(e);
                    }
                });
                threshold.add(hr_min, "cell 2 2");

                //---- hr_max ----
                hr_max.setText("110");
                hr_max.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        hr_maxKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        hr_maxKeyReleased(e);
                    }
                });
                threshold.add(hr_max, "cell 3 2");

                //---- BPM ----
                BPM.setText("BPM");
                threshold.add(BPM, "cell 4 2");

                //---- blood_pressure ----
                blood_pressure.setText("Blood Pressure:");
                blood_pressure.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(blood_pressure, "cell 0 3");

                //---- systolic ----
                systolic.setText("Systolic:");
                systolic.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(systolic, "cell 1 3");

                //---- sys_min ----
                sys_min.setText("85");
                sys_min.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        sys_minKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        sys_minKeyReleased(e);
                    }
                });
                threshold.add(sys_min, "cell 2 3");

                //---- sys_max ----
                sys_max.setText("145");
                sys_max.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        sys_maxKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        sys_maxKeyReleased(e);
                    }
                });
                threshold.add(sys_max, "cell 3 3");

                //---- mmHg_high ----
                mmHg_high.setText("mmHg");
                threshold.add(mmHg_high, "cell 4 3");

                //---- diastolic ----
                diastolic.setText("Diastolic:");
                diastolic.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(diastolic, "cell 1 4");

                //---- dia_min ----
                dia_min.setText("55");
                dia_min.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        dia_minKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        dia_minKeyReleased(e);
                    }
                });
                threshold.add(dia_min, "cell 2 4");

                //---- dia_max ----
                dia_max.setText("90");
                dia_max.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        dia_maxKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        dia_maxKeyReleased(e);
                    }
                });
                threshold.add(dia_max, "cell 3 4");

                //---- mmHg_low ----
                mmHg_low.setText("mmHg");
                threshold.add(mmHg_low, "cell 4 4");

                //---- respiratory_rate ----
                respiratory_rate.setText("Respiratory Rate:");
                respiratory_rate.setFont(new Font("Arial", Font.PLAIN, 12));
                threshold.add(respiratory_rate, "cell 0 5");

                //---- resp_min ----
                resp_min.setText("12");
                resp_min.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        resp_minKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        resp_minKeyReleased(e);
                    }
                });
                threshold.add(resp_min, "cell 2 5");

                //---- resp_max ----
                resp_max.setText("20");
                resp_max.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        resp_maxKeyPressed(e);
                    }
                    @Override
                    public void keyReleased(KeyEvent e) {
                        resp_maxKeyReleased(e);
                    }
                });
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
            save_button.setFont(new Font("Arial", Font.PLAIN, 14));
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
    // Generated using JFormDesigner Educational license - Tianyu Wei (天宇 魏)
    private JPanel patient_editor_main_panel;
    private JPanel title_and_reference;
    private JLabel title;
    private JLabel patient_ref;
    public JComboBox ref_selector;
    private JPanel patient_profile;
    private JPanel name;
    public JLabel first_name;
    public JTextField first_name_field;
    private JLabel last_name;
    public JTextField last_name_field;
    private JLabel label1;
    private JPanel gender;
    private JLabel gender_label;
    public JRadioButton male_button;
    public JRadioButton female_button;
    public JPanel birthday;
    private JLabel YoB;
    public JComboBox year_selector;
    private JLabel reminder;
    private JLabel label2;
    private JLabel label3;
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
