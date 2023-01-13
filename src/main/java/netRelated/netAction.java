package netRelated;

import com.google.gson.Gson;

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
import java.util.List;

import static java.lang.Math.floor;


public class netAction {
    public static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    public static void databaseUpdate(String order){
        Connection conn=null;
        PreparedStatement s=null;
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
    public static responsePack recordData(long startTime, long endTime,long initialTime,String type,int interval){
        String table="alphafast";
        if(type=="body temperature"){
            type="temperature";
            table="alphaslow";
        } else if (type == "heart rate") {
            type="heart";
            table="alphaslow";
        } else if (type == "systolic blood pressure") {
            type="systolic";
            table="alphaslow";
        } else if (type == "diastolic blood pressure") {
            type="diastolic";
            table="alphaslow";
        } else if (type == "respiratory rate") {
            type="respiratory";
            table="alphaslow";
        }
        List<Double> values = new ArrayList<>();
        responsePack respPack = new responsePack();
        Connection conn = null;
        PreparedStatement s = null;
        String orderEcg1 = "select "+ type+ " from "+ table+ " where id>? and id<=?";
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
            respPack.setLastTime(startTime+interval*(values.size()));
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
    public static long getInitialTime(){

        Connection conn = null;
        PreparedStatement s = null;
        long initialTime=0;

        String orderTime = "select initialtime from patientlist where reference='alpha'";
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            s = conn.prepareStatement(orderTime);
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                initialTime = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("execute fail in time");
        }
        try {
            s.close();
        } catch (SQLException e) {
            System.out.println("end statement fail in time");
        }
        return initialTime;
    }
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
//    public static void putReference(String reference) throws IOException, InterruptedException {
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(reference);
//        var request= HttpRequest.newBuilder()
//                .uri(URI.create("http://localhost:8080/docScope_s/reader"))
//                .header("Content-Type","application/json")
//                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
//                .build();
//        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    }
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

