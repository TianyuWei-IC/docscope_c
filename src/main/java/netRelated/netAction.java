package netRelated;

import Interface.GUI_test;
import com.google.gson.Gson;
import master.Patient;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
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


public class netAction {
    public static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
//    public static String ref="alpha";

    public static void databaseUpdate(String order) {
        Connection conn = null;
        PreparedStatement s = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            s = conn.prepareStatement(order);
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println("execute fail in time");
        }
        try {
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("end connection fail");
        }
    }

    public static responsePack recordData(long startTime, long endTime, long initialTime,
                                          String type, int interval,String ref) {
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
        List<Double> values = new ArrayList<>();
        responsePack respPack = new responsePack();
        Connection conn = null;
        PreparedStatement s = null;
        String orderEcg1 = "select " + type + " from " + table + " where id>? and id<=?";
        int index1 = (int) floor((startTime - initialTime) / interval);
        int index2 = (int) floor((endTime - initialTime) / interval);
        if (index1 <= 0) {
            System.out.println("empty");
        }
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            s = conn.prepareStatement(orderEcg1);
            s.setInt(1, index1);
            s.setInt(2, index2);
        } catch (SQLException e) {
            System.out.println("statement fail in value");
        }
        try {
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                values.add(resultSet.getDouble(type));
            }
            respPack.setLastTime(startTime + interval * (values.size()));
//            System.out.println("returned size is "+values.size());
//            System.out.println("last time is " + respPack.lastTime);
//            System.out.println("start time is " + startTime);
//            System.out.println("end time is " + endTime);
        } catch (Exception e) {
            System.out.println("resultSet fail in value");
        }
        try {
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("end connection fail");
        }
        respPack.setValueList(values);
        return respPack;
    }
    public static responsePack averageData(long startTime, long endTime, long initialTime,
                                           String type,String ref) {
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
        List<Double> values = new ArrayList<>();
        responsePack respPack = new responsePack();
        Connection conn = null;
        PreparedStatement s = null;
        String orderEcg1 = "select " + type + " from " + table + " where id>? and id<=?";
        int index1 = (int) floor((startTime - initialTime) / 60000);
        int index2 = (int) floor((endTime - initialTime) / 60000);
        if (index1 <= 0) {
            System.out.println("empty in average");
        }
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            s = conn.prepareStatement(orderEcg1);
            s.setInt(1, index1);
            s.setInt(2, index2);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                values.add(resultSet.getDouble(type));
            }
            respPack.setLastTime(startTime + (long) 60000 * (values.size()));
            s.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("netAction fail in average");
        }
        respPack.setValueList(values);
        return respPack;
    }
    public static List<List<String>> findAbnormal(Patient patient){
        long initialTime=getInitialTime();
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
            Connection conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            PreparedStatement s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();
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
                    if(!HeartHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        heartHigh.add(timestamp.toString().substring(0,17) + " - ");
                        HeartHigh = true;
                    }
                }
                else if (resultSet.getDouble("heart")<patient.hr_min) {
                    if(!HeartLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        heartLow.add(timestamp.toString().substring(0,17) + " - ");
                        HeartLow = true;
                    }
                }
                else{
                    if (HeartHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        heartHigh.set(heartHigh.size()-1,heartHigh.get(heartHigh.size()-1)+timestamp.toString().substring(0,17));
                        HeartHigh=false;
                    } else if (HeartLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        heartLow.set(heartLow.size()-1,heartLow.get(heartLow.size()-1)+timestamp.toString().substring(0,17));
                        HeartLow=false;
                        System.out.println(resultSet.getDouble("heart")+"   "+patient.hr_min+"-"+patient.hr_max);
                    }
                }

                if (resultSet.getDouble("temperature")>patient.temp_max){
                    if(!TempHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        tempHigh.add(timestamp + " - ");
                        TempHigh = true;
                    }
                }
                else if (resultSet.getDouble("temperature")<patient.temp_min) {
                    if(!TempLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        tempLow.add(timestamp + " - ");
                        TempLow = true;
                    }
                }
                else{
                    if (TempHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        tempHigh.set(tempHigh.size()-1,tempHigh.get(tempHigh.size()-1)+timestamp);
                        TempHigh=false;
                    } else if (TempLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        tempLow.set(tempLow.size()-1,tempLow.get(tempLow.size()-1)+timestamp);
                        TempLow=false;
                    }
                }
                
                if (resultSet.getDouble("respiratory")>patient.resp_max){
                    if(!RespHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        respHigh.add(timestamp + " - ");
                        RespHigh = true;
                    }
                }
                else if (resultSet.getDouble("respiratory")<patient.resp_min) {
                    if(!RespLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        respLow.add(timestamp + " - ");
                        RespLow = true;
                    }
                }
                else{
                    if (RespHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        respHigh.set(respHigh.size()-1,respHigh.get(respHigh.size()-1)+timestamp);
                        RespHigh=false;
                    } else if (RespLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        respLow.set(respLow.size()-1,respLow.get(respLow.size()-1)+timestamp);
                        RespLow=false;
                    }
                }

                if (resultSet.getDouble("systolic")>patient.sys_max){
                    if(!SysHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        sysHigh.add(timestamp + " - ");
                        SysHigh = true;
                    }
                }
                else if (resultSet.getDouble("systolic")<patient.sys_min) {
                    if(!SysLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        sysLow.add(timestamp + " - ");
                        SysLow = true;
                    }
                }
                else{
                    if (SysHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        sysHigh.set(sysHigh.size()-1,sysHigh.get(sysHigh.size()-1)+timestamp);
                        SysHigh=false;
                    } else if (SysLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        sysLow.set(sysLow.size()-1,sysLow.get(sysLow.size()-1)+timestamp);
                        SysLow=false;
                    }
                }

                if (resultSet.getDouble("diastolic")>patient.dia_max){
                    if(!DiaHigh) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        diaHigh.add(timestamp + " - ");
                        DiaHigh = true;
                    }
                }
                else if (resultSet.getDouble("diastolic")<patient.dia_min) {
                    if(!DiaLow) {
                        timestamp = new Timestamp(initialTime + resultSet.getInt("id") * 60000L);
                        diaLow.add(timestamp + " - ");
                        DiaLow = true;
                    }
                }
                else{
                    if (DiaHigh){
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        diaHigh.set(diaHigh.size()-1,diaHigh.get(diaHigh.size()-1)+timestamp);
                        DiaHigh=false;
                    } else if (DiaLow) {
                        timestamp=new Timestamp(initialTime+resultSet.getInt("id")* 60000L);
                        diaLow.set(diaLow.size()-1,diaLow.get(diaLow.size()-1)+timestamp);
                        DiaLow=false;
                    }
                }
            }
            s.close();
        } catch (SQLException e) {
            System.out.println("end statement fail in time");
        }
        return Arrays.asList(heartHigh,heartLow,tempHigh,tempLow,respHigh,respLow,sysHigh,sysLow,diaHigh,diaLow);
    }
    //    public static responsePack recordDataTemp(long startTime, long endTime,long initialTime){
//
//        List<Double> values = new ArrayList<>();
//        responsePack respPack = new responsePack();
//        Connection conn = null;
//        PreparedStatement s = null;
//
//        String order = "select temperature from alphaslow where id>? and id<=?";
//        int index1 = (int) floor((startTime - initialTime) / 1000);
//        int index2 = (int) floor((endTime - initialTime) / 1000);
//        if (index1 <= 0) {
//            System.out.println("empty");
//        }
//        try {
//            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
//            s = conn.prepareStatement(order);
////                    ResultSet.TYPE_SCROLL_INSENSITIVE,
////                    ResultSet.CONCUR_READ_ONLY);
//            s.setInt(1, index1);
//            s.setInt(2, index2);
//        } catch (SQLException e) {
//            System.out.println("statement fail in value");
//        }
//        try {
//            ResultSet resultSet = s.executeQuery();
//            while (resultSet.next()) {
//                values.add(resultSet.getDouble("temperature"));
//            }
//            respPack.setLastTime(startTime+1000*(values.size()));
////            System.out.println("returned size is "+values.size());
////            System.out.println("last time is " + respPack.lastTime);
////            System.out.println("start time is " + startTime);
////            System.out.println("end time is " + endTime);
//        } catch (Exception e) {
//            System.out.println("resultSet fail in value");
//        }
////        System.out.println(values);
//        try {
//            s.close();
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println("end connection fail");
//        }
//        respPack.setValueList(values);
//        return respPack;
//    }
    public static long getInitialTime() {
        long initialTime = 0;
        String orderTime = "select initialtime from patientlist where reference='alpha'";
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            PreparedStatement s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                initialTime = resultSet.getLong(1);
            }
            s.close();
        } catch (SQLException e) {
            System.out.println("end statement fail in time");
        }
        return initialTime;
    }
    public static List<String> getPatientInformation(JPanel patient_list, GUI_test gui){
        String orderTime = "select * from patientlist";
        List<String> references = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            PreparedStatement s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("firstname") == null) {}
                else {
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
                references.add(resultSet.getString("reference"));
            }

            s.close();
        } catch (SQLException e) {
            System.out.println("end statement fail in time");
        }
        return references;
    }
    //    public static responsePack postRequestData(requestPack reqPack,String web) throws IOException {
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(reqPack);
//        // Set up the body data
//        String message = jsonString;
//        byte[] body = message.getBytes(StandardCharsets.UTF_8);
//
//        URL myURL = new URL(web);
//        HttpURLConnection conn = null;
//
//        conn = (HttpURLConnection) myURL.openConnection();
//// Set up the header
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Accept", "application/json");
//        conn.setRequestProperty("charset", "utf-8");
//        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
//        conn.setDoOutput(true);
//
//// Write the body of the request
//        try (OutputStream outputStream = conn.getOutputStream()) {
//            outputStream.write(body, 0, body.length);
//        }
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//
//        String inputLine;
//// Read the body of the response
//        responsePack respPack = null;
//        while ((inputLine = bufferedReader.readLine()) != null) {
////            System.out.println(inputLine);
//            respPack=gson.fromJson(inputLine, responsePack.class);
//            return respPack;
//        }
//        bufferedReader.close();
//
//        return respPack;
//    }
    public static void putReference(String reference,List<Double> threshold,
                                    String first_name,String last_name,String gender,int year) {
        updateThreshold(reference,threshold,first_name,last_name,gender,year);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("sleep fail");
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(reference);
        var request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/docScope_s/data"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        try {
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }catch (Exception ignore){}
    }
    public static void updateThreshold(String ref,List<Double> threshold,
                                       String first_name,String last_name,String gender,int year){
        String order="update patientList set temperaturehigh=?,temperaturelow=?,hearthigh=?," +
                "heartlow=?,systolichigh=?,systoliclow=?,diastolichigh=?,diastoliclow=?," +
                "respiratoryhigh=?,respiratorylow=?,firstname=?,lastname=?,gender=?,yearbirth=? where reference='"+ref+"'";
        try {
            Connection conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
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
        } catch (SQLException e) {
            System.out.println("end connection fail");
        }
    }
//    public static void makeGetRequest() throws IOException {
//        URL myURL = new URL("https://servlet10032.herokuapp.com/patients");
//        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "text/html");
//        conn.setRequestProperty("charset", "utf-8");
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(myURL.openStream()));
//
//        String inputLine;
//// Read the body of the response
//        while ((inputLine = in.readLine()) != null) {
//            System.out.println(inputLine);
//        }
//        in.close();
//
//    }
}

