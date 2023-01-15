package netRelated;

import Interface.GUI_test;
import com.google.gson.Gson;
import master.Patient;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.floor;

/**
 * This class contains all method needed to communicate with the servlet and the database
 */
public class netAction {
    /**
     * address of the database
     */
    public static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    /**
     * address of the servlet
     */
    public static String servletUrl="http://localhost:8080/docScope_s/data";
    /**
     * username of the database
     */
    public static String userName="postgres";
    /**
     * password of the database
     */
    public static String password="1234";

    /**
     * Give the response pack of real data between start time and end time
     * @param startTime corresponding time of the value before the first value needed
     * @param endTime corresponding time of the first value needed
     * @param initialTime time the servlet starts
     * @param type type of the data needed
     * @param interval sampling interval of data in milli seconds
     * @param ref reference of the patient
     * @return value needed and the corresponding time of the last value
     */
    public static responsePack recordData(long startTime, long endTime, long initialTime,
                                          String type, int interval,String ref) {
        //set the table name based on type, and change the type name to which the database is using
        String table = ref+"slow";
        if (type == "body temperature") {
            type = "temperature";
        } else if (type == "heart rate") {
            type = "heart";
        } else if (type == "systolic blood pressure") {
            type = "systolic";
        } else if (type == "diastolic blood pressure") {
            type = "diastolic";
        } else if (type == "respiratory rate") {
            type = "respiratory";
        }else table=ref+"fast";

        responsePack respPack = new responsePack();
        List<Double> values = new ArrayList<>();
        String orderEcg1 = "select " + type + " from " + table + " where id>? and id<=?";
        // calculate the id based on the given time
        int index1 = (int) floor((double) (startTime - initialTime) / interval);
        int index2 = (int) floor((double) (endTime - initialTime) / interval);
        try {
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(orderEcg1);
            s.setInt(1, index1);
            s.setInt(2, index2);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                //get the data from the database
                values.add(resultSet.getDouble(type));
            }
            s.close();
            conn.close();
        } catch (SQLException ignored) {}
        // In case the database does not have the data at end time,
        // return the actual time corresponding to the last value
        respPack.lastTime=startTime + (long) interval * (values.size());
        respPack.valueList=values;
        return respPack;
    }

    /**
     * Give the response pack of averaged data between start time and end time
     * @param startTime corresponding time of the value before the first value needed
     * @param endTime corresponding time of the first value needed
     * @param initialTime time the servlet starts
     * @param type type of the data needed
     * @param ref reference of the patient
     * @return value needed and the corresponding time of the last value
     */
    public static responsePack averageData(long startTime, long endTime, long initialTime,
                                           String type,String ref) {
        //set the table name based on type, and change the type name to which the database is using
        String table = ref+"slowaverage";
        if (type == "body temperature") {
            type = "temperature";
        } else if (type == "heart rate") {
            type = "heart";
        } else if (type == "systolic blood pressure") {
            type = "systolic";
        } else if (type == "diastolic blood pressure") {
            type = "diastolic";
        } else if (type == "respiratory rate") {
            type = "respiratory";
        }

        responsePack respPack = new responsePack();
        List<Double> values = new ArrayList<>();
        String orderEcg1 = "select " + type + " from " + table + " where id>? and id<=?";
        // calculate the id based on the given time
        int index1 = (int) floor((double)(startTime - initialTime) / 60000);
        int index2 = (int) floor((double)(endTime - initialTime) / 60000);
        try {
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(orderEcg1);
            s.setInt(1, index1);
            s.setInt(2, index2);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                //get the data from the database
                values.add(resultSet.getDouble(type));
            }
            s.close();
            conn.close();
        } catch (SQLException ignored) {}
        // In case the database does not have the data at end time,
        // return the actual time corresponding to the last value
        respPack.lastTime=startTime + (long) 60000 * (values.size());
        respPack.valueList=values;
        return respPack;
    }

    /**
     * to find the abnormal intervals for the given patient
     * @param patient find abnormal intervals for this Patient object
     * @return abnormal intervals in a list: Heart rate high and low, body temperature high and low,
     * respiratory rate high and low, systolic pressure high and low, diastolic pressure high and low
     */
    public static List<List<String>> findAbnormal(Patient patient){
        long initialTime=getInitialTime(patient.reference_value);
        String orderTime = "select * from "+patient.reference_value+"slowaverage";
        List<String> heartHigh=new ArrayList<>();
        List<String> heartLow=new ArrayList<>();
        List<String> tempHigh=new ArrayList<>();
        List<String> tempLow=new ArrayList<>();
        List<String> respHigh=new ArrayList<>();
        List<String> respLow=new ArrayList<>();
        List<String> sysHigh=new ArrayList<>();
        List<String> sysLow=new ArrayList<>();
        List<String> diaHigh=new ArrayList<>();
        List<String> diaLow=new ArrayList<>();
        Timestamp timestamp;
        try {
            //get all the average signals of the specific patient from the database
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();
            //boolean values to determine whether the abnormal happens
            boolean HeartHigh=false;
            boolean HeartLow=false;
            boolean TempHigh=false;
            boolean TempLow=false;
            boolean RespHigh=false;
            boolean RespLow=false;
            boolean SysHigh=false;
            boolean SysLow=false;
            boolean DiaHigh=false;
            boolean DiaLow=false;
            while (resultSet.next()) {
                if (resultSet.getDouble("heart")>patient.hr_max){
                    //Given this type of abnormal did not happen before, the corresponding time
                    // will add to the list of string as the abnormal starting time
                    if(!HeartHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        heartHigh.add(timestamp.toString().substring(0,16) + " - ");
                        HeartHigh = true;
                    }
                }
                else if (resultSet.getDouble("heart")<patient.hr_min) {
                    if(!HeartLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        heartLow.add(timestamp.toString().substring(0,16) + " - ");
                        HeartLow = true;
                    }
                }
                else{
                    if (HeartHigh){
                        //Given a normal value is found, and that specific abnormal was found before,
                        //the corresponding time will add to the list of string as the abnormal ending time
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        heartHigh.set(heartHigh.size()-1,heartHigh.get(heartHigh.size()-1)+timestamp.toString().substring(0,16));
                        HeartHigh=false;
                    } else if (HeartLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        heartLow.set(heartLow.size()-1,heartLow.get(heartLow.size()-1)+timestamp.toString().substring(0,16));
                        HeartLow=false;
                    }
                }

                if (resultSet.getDouble("temperature")>patient.temp_max){
                    if(!TempHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        tempHigh.add(timestamp.toString().substring(0,16) + " - ");
                        TempHigh = true;
                    }
                }
                else if (resultSet.getDouble("temperature")<patient.temp_min) {
                    if(!TempLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        tempLow.add(timestamp.toString().substring(0,16) + " - ");
                        TempLow = true;
                    }
                }
                else{
                    if (TempHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        tempHigh.set(tempHigh.size()-1,tempHigh.get(tempHigh.size()-1)+timestamp.toString().substring(0,16));
                        TempHigh=false;
                    } else if (TempLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        tempLow.set(tempLow.size()-1,tempLow.get(tempLow.size()-1)+timestamp.toString().substring(0,16));
                        TempLow=false;
                    }
                }
                
                if (resultSet.getDouble("respiratory")>patient.resp_max){
                    if(!RespHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        respHigh.add(timestamp.toString().substring(0,16) + " - ");
                        RespHigh = true;
                    }
                }
                else if (resultSet.getDouble("respiratory")<patient.resp_min) {
                    if(!RespLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        respLow.add(timestamp.toString().substring(0,16) + " - ");
                        RespLow = true;
                    }
                }
                else{
                    if (RespHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        respHigh.set(respHigh.size()-1,respHigh.get(respHigh.size()-1)+timestamp.toString().substring(0,16));
                        RespHigh=false;
                    } else if (RespLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        respLow.set(respLow.size()-1,respLow.get(respLow.size()-1)+timestamp.toString().substring(0,16));
                        RespLow=false;
                    }
                }

                if (resultSet.getDouble("systolic")>patient.sys_max){
                    if(!SysHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        sysHigh.add(timestamp.toString().substring(0,16) + " - ");
                        SysHigh = true;
                    }
                }
                else if (resultSet.getDouble("systolic")<patient.sys_min) {
                    if(!SysLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        sysLow.add(timestamp.toString().substring(0,16) + " - ");
                        SysLow = true;
                    }
                }
                else{
                    if (SysHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        sysHigh.set(sysHigh.size()-1,sysHigh.get(sysHigh.size()-1)+timestamp.toString().substring(0,16));
                        SysHigh=false;
                    } else if (SysLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        sysLow.set(sysLow.size()-1,sysLow.get(sysLow.size()-1)+timestamp.toString().substring(0,16));
                        SysLow=false;
                    }
                }

                if (resultSet.getDouble("diastolic")>patient.dia_max){
                    if(!DiaHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        diaHigh.add(timestamp.toString().substring(0,16) + " - ");
                        DiaHigh = true;
                    }
                }
                else if (resultSet.getDouble("diastolic")<patient.dia_min) {
                    if(!DiaLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        diaLow.add(timestamp.toString().substring(0,16) + " - ");
                        DiaLow = true;
                    }
                }
                else{
                    if (DiaHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        diaHigh.set(diaHigh.size()-1,diaHigh.get(diaHigh.size()-1)+timestamp.toString().substring(0,16));
                        DiaHigh=false;
                    } else if (DiaLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        diaLow.set(diaLow.size()-1,diaLow.get(diaLow.size()-1)+timestamp.toString().substring(0,16));
                        DiaLow=false;
                    }
                }
            }
            s.close();
        } catch (SQLException ignored) {}
        return Arrays.asList(heartHigh,heartLow,tempHigh,tempLow,respHigh,respLow,sysHigh,sysLow,diaHigh,diaLow);
    }

    /**
     * find the start time when the database begin to store the data of the specific patient
     * @param ref reference of that specific patient
     * @return correspond time of the first value in the database
     */
    public static long getInitialTime(String ref) {
        long initialTime = 0;
        String orderTime = "select initialtime from patientlist where reference=?";
        try {
            //get initial time from the database
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(orderTime);
            s.setString(1,ref);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                initialTime = resultSet.getLong(1);
            }
            s.close();
        } catch (SQLException ignored) {}
        return initialTime;
    }

    /**
     * get patients' information, if doctor have added them through this application before
     * get all the references in the database
     * @param patient_list panel to store patients' information in the application
     * @param gui the interface of the application
     * @return list of references
     */
    public static List<String> getPatientInformation(JPanel patient_list, GUI_test gui){
        String orderTime = "select * from patientlist";
        List<String> references = new ArrayList<>();
        try {
            //get patients' information from the database
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                // null first name means the doctor haven't set this patient before
                if (resultSet.getString("firstname") != null) {
                    patient_list.add(new Patient(resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            resultSet.getString("reference"),
                            resultSet.getString("gender"),
                            resultSet.getInt("yearbirth"),
                            resultSet.getDouble("temperaturelow"),
                            resultSet.getDouble("temperaturehigh"),
                            resultSet.getInt("heartlow"),
                            resultSet.getInt("hearthigh"),
                            resultSet.getInt("systoliclow"),
                            resultSet.getInt("systolichigh"),
                            resultSet.getInt("diastoliclow"),
                            resultSet.getInt("diastolichigh"),
                            resultSet.getInt("respiratorylow"),
                            resultSet.getInt("respiratoryhigh"),
                            gui));
                }
                //get all references existing int the database
                references.add(resultSet.getString("reference"));
            }
            s.close();
        } catch (SQLException ignored) {}
        return references;
    }

    /**
     * post the email address to the servlet
     * if the email address is null, try to get the email address stored in the servlet
     * @param emailAddress email address entered by the user
     * @return email address from the servlet (input is null) or from the inputs
     */
    public static String postEmailAddress(String emailAddress) {
        String newEmailAddress=null;
        Gson gson = new Gson();
        String message = gson.toJson(emailAddress);
        byte[] body = message.getBytes(StandardCharsets.UTF_8);
        try {
            //post the email address
            URL myURL = new URL(servletUrl);
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            //get the email address from the servlet
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                newEmailAddress = gson.fromJson(inputLine, String.class);
            }
            bufferedReader.close();
        }catch (Exception ignore){}
        if (newEmailAddress == null) {
            //null feedback from the servlet means the servlet has received the email address
            // or there is no email address in the servlet
            return emailAddress;
        }else return newEmailAddress;
        //not null feedback means a null email address is posted, and the servlet gives the stored email address
    }

    /**
     * update information of the specific patient to the database, and tell the servlet it has been updated
     * @param reference reference of the specific patient
     * @param threshold new thresholds needed to be updated
     * @param first_name first name of the patient
     * @param last_name last name of the patient
     * @param gender gender of the patient
     * @param year year of birth of the patient
     */
    public static void putReference(String reference,List<Double> threshold,
                                    String first_name,String last_name,String gender,int year) {
        // update the database
        String order="update patientList set temperaturehigh=?,temperaturelow=?,hearthigh=?," +
                "heartlow=?,systolichigh=?,systoliclow=?,diastolichigh=?,diastoliclow=?," +
                "respiratoryhigh=?,respiratorylow=?,firstname=?,lastname=?,gender=?,yearbirth=? " +
                "where reference='"+reference+"'";
        try {
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(order);
            for(int i=0;i<10;i++) {
                s.setDouble(i+1, threshold.get(i));
            }
            s.setString(11,first_name);
            s.setString(12,last_name);
            s.setString(13,gender);
            s.setInt(14,year);
            s.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException ignored) {}
        // tell the servlet who has been updated
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}
        Gson gson = new Gson();
        String message = gson.toJson(reference);
        var req = HttpRequest.newBuilder()
                .uri(URI.create(servletUrl))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(message))
                .build();
        try {
            //send the reference of the updated patient to the servlet
            HttpClient.newHttpClient().send(req, HttpResponse.BodyHandlers.ofString());
        }catch (Exception ignored){}
    }

    /**
     * delete the name of the specific patient from the database,
     * so that the servlet won't check the status of this patient.
     * but the database will still store that patient's data
     * @param ref reference of the specific patient
     */
    public static void deletePatient(String ref){
        //update the name of specific patient to null
        String order="update patientlist set firstname=null,lastname=null where reference=?";
        try {
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            PreparedStatement s = conn.prepareStatement(order);
            s.setString(1,ref);
            s.executeUpdate();
            s.close();
            conn.close();
        } catch (SQLException ignored) {}
    }
}

