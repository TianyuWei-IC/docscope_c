package chartPanel;

import javax.swing.*;

public class Display_Chart extends JPanel {
    public Chart_Label_Display chartLabelDisplay;
    public Display_Chart(Chart_Label_Display chartLabelDisplay){
        this.chartLabelDisplay = chartLabelDisplay;
    }

    public Chart_Label_Display find_cl_display(){
        return chartLabelDisplay;
    }
}
