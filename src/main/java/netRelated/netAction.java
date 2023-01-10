package netRelated;

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
    public static responsePack recordFastData(long startTime, long endTime, long initialTime, String type){

        List<Double> values = new ArrayList<>();
        responsePack respPack = new responsePack();
        Connection conn = null;
        PreparedStatement s = null;
        String orderEcg1 = "select "+
                type+
                " from ecgresp where id>? and id<=?";
        int index1 = (int) floor((startTime - initialTime) / 2);
        int index2 = (int) floor((endTime - initialTime) / 2);
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
            respPack.setLastTime(startTime+2*(values.size()));
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
    public static responsePack recordSlowData(long startTime, long endTime, long initialTime){

        List<Double> values = new ArrayList<>();
        responsePack respPack = new responsePack();
        Connection conn = null;
        PreparedStatement s = null;
        String type="temperature";
        int interval=1000;
        String order = "select "+
                type+
                " from other where id>? and id<=?";
        int index1 = (int) floor((startTime - initialTime) / interval);
        int index2 = (int) floor((endTime - initialTime) / interval);
        if (index1 <= 0) {
            System.out.println("empty");
        }
        try {
            conn = DriverManager.getConnection(dbUrl, "postgres", "1234");
            s = conn.prepareStatement(order);
            s.setInt(1, index1);
            s.setInt(2, index2);
        } catch (SQLException e) {
            System.out.println("statement fail in value");
        }
        try {
            ResultSet resultSet = s.executeQuery();
            while (resultSet.next()) {
                values.add(resultSet.getDouble("temperature"));
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
    public static long getInitialTime(){

        Connection conn = null;
        PreparedStatement s = null;
        long initialTime=0;

        String orderTime = "select initialtime from patientlist where reference='patient1'";
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

