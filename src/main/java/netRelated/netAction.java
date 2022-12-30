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


public class netAction {
    public static responsePack postRequestData(requestPack reqPack,String web) throws IOException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(reqPack);
        // Set up the body data
        String message = jsonString;
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        URL myURL = new URL(web);
        HttpURLConnection conn = null;

        conn = (HttpURLConnection) myURL.openConnection();
// Set up the header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(body.length));
        conn.setDoOutput(true);

// Write the body of the request
        try (OutputStream outputStream = conn.getOutputStream()) {
            outputStream.write(body, 0, body.length);
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        String inputLine;
// Read the body of the response
        responsePack respPack = null;
        while ((inputLine = bufferedReader.readLine()) != null) {
//            System.out.println(inputLine);
            respPack=gson.fromJson(inputLine, responsePack.class);
            return respPack;
        }
        bufferedReader.close();

        return respPack;
    }
    public static void putReference(String reference) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String jsonString = gson.toJson(reference);
        var request= HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/docScope_s/reader"))
                .header("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
    public static void makeGetRequest() throws IOException {
        URL myURL = new URL("https://servlet10032.herokuapp.com/patients");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/html");
        conn.setRequestProperty("charset", "utf-8");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(myURL.openStream()));

        String inputLine;
// Read the body of the response
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();

    }
}
