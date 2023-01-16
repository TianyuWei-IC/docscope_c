package master;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * class to generate a pdf report for abnormalities
 */
public class CreatePDF {
    /**
     * take name, references, and abnormal times.
     * times should be 10 String lists, each element of one list should have
     * a start time and an end time (if the abnormal period ends)
     * @param name patient's name
     * @param ref patient's reference
     */
    public CreatePDF(String name, String ref, List<String> HeartRateH, List<String> HeartRateL,
                     List<String> TemperatureH, List<String> TemperatureL, List<String> RespRateH, List<String> RespRateL,
                     List<String> SystolicH, List<String> SystolicL, List<String> DiastolicH, List<String> DiastolicL) {
        //set directory
        String currDir = System.getProperty("user.dir");
        String home = System.getProperty("user.home");
        String desktop =  home + "/Desktop";
        String pdfPath = desktop + "/docScope_Urgent Timeline.pdf";
        String logoPath = currDir + "/src/main/resources/logo.jpg";
        //set current time
        String current_time = String.valueOf(new Timestamp(System.currentTimeMillis()).toLocalDateTime());
        //set text font
        Font titleFont = FontFactory.getFont(FontFactory.TIMES,30,Font.BOLD,BaseColor.RED);
        Font textFont = FontFactory.getFont(FontFactory.COURIER,10);
        Font paraFont = FontFactory.getFont(FontFactory.COURIER_BOLD,13);
        Font timeFont = FontFactory.getFont(FontFactory.COURIER,10);

        //document lay out and content
        Document document = new Document();
        try {
            //create a PDF document
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            //report generated time
            Paragraph currTime = new Paragraph("Generated time:" + current_time +"\n\n",textFont);
            currTime.setAlignment(Element.ALIGN_TOP);
            currTime.setAlignment(Element.ALIGN_RIGHT);
            document.add(currTime);

            //print the logo on the first page of the document
            Image img=Image.getInstance(logoPath);
            img.setAbsolutePosition(30,760);
            document.add(img);

            //print the report title
            Paragraph title = new Paragraph("*Urgent Timeline Report*",titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            //patient name & reference
            Paragraph pname = new Paragraph("\nPatient name: " + name,textFont);
            document.add(pname);
            Paragraph pref = new Paragraph("Patient reference: " + ref,textFont);
            document.add(pref);

            //insert the abnormal timeline of heart rate
            Paragraph HeartH = new Paragraph("\nHeart Rate High: ",paraFont);
            document.add(HeartH);
            for(String HeartRateHIT:HeartRateH){
                Paragraph timeInt1 = new Paragraph(HeartRateHIT,timeFont);
                timeInt1.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt1);
            }
            Paragraph HeartL = new Paragraph("Heart Rate Low: ",paraFont);
            document.add(HeartL);
            for(String HeartRateLIT:HeartRateL){
                Paragraph timeInt2 = new Paragraph(HeartRateLIT,timeFont);
                timeInt2.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt2);
            }
            //insert the abnormal timeline of temperature
            Paragraph TempH = new Paragraph("Temperature High: ",paraFont);
            document.add(TempH);
            for(String TemperatureHIT:TemperatureH){
                Paragraph timeInt3 = new Paragraph(TemperatureHIT,timeFont);
                timeInt3.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt3);
            }
            Paragraph TempL = new Paragraph("Temperature Low: ",paraFont);
            document.add(TempL);
            for(String TemperatureLIT:TemperatureL){
                Paragraph timeInt4 = new Paragraph(TemperatureLIT,timeFont);
                timeInt4.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt4);
            }
            //insert the abnormal timeline of respiratory rate
            Paragraph RaspH = new Paragraph("Respiratory Rate High: ",paraFont);
            document.add(RaspH);
            for(String RaspRateHIT:RespRateH){
                Paragraph timeInt5 = new Paragraph(RaspRateHIT,timeFont);
                timeInt5.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt5);
            }
            Paragraph RaspL = new Paragraph("Respiratory Rate Low: ",paraFont);
            document.add(RaspL);
            for(String RaspRateLIT:RespRateL){
                Paragraph timeInt6 = new Paragraph(RaspRateLIT,timeFont);
                timeInt6.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt6);
            }
            //insert the abnormal timeline of systolic pressure
            Paragraph SysH = new Paragraph("Systolic Pressure High: ",paraFont);
            document.add(SysH);
            for(String SystolicHIT:SystolicH){
                Paragraph timeInt7 = new Paragraph(SystolicHIT,timeFont);
                timeInt7.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt7);
            }
            Paragraph SysL = new Paragraph("Systolic Pressure Low: ",paraFont);
            document.add(SysL);
            for(String SystolicLIT:SystolicL){
                Paragraph timeInt8 = new Paragraph(SystolicLIT,timeFont);
                timeInt8.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt8);
            }
            //insert the abnormal timeline of diastolic pressure
            Paragraph DiaH = new Paragraph("Diastolic Pressure High: ",paraFont);
            document.add(DiaH);
            for(String DiastolicHIT:DiastolicH){
                Paragraph timeInt9 = new Paragraph(DiastolicHIT,timeFont);
                timeInt9.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt9);
            }
            Paragraph DiaL = new Paragraph("Diastolic Pressure Low: ",paraFont);
            document.add(DiaL);
            for(String DiastolicLIT:DiastolicL){
                Paragraph timeInt10 = new Paragraph(DiastolicLIT,timeFont);
                timeInt10.setAlignment(Element.ALIGN_CENTER);
                document.add(timeInt10);
            }

            //close the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
