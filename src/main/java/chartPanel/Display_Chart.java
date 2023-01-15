package chartPanel;

import javax.swing.*;

public class Display_Chart extends JPanel {
    public Chart_Label_Display chartLabelDisplay;

    /**
     * this is the container for the XYChart in Chart_Label_Display
     */
    public Display_Chart(Chart_Label_Display chartLabelDisplay){
        this.chartLabelDisplay = chartLabelDisplay;
    }

    /**
     * this method enables the access to the display chart on MainGUI, so that it can further access to the patient
     * that is displaying, this is key to the switch between patients
     */
    public Chart_Label_Display find_cl_display(){
        return chartLabelDisplay;
    }
}
