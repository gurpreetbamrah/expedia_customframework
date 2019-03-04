package api;

import runner.FrameWorkLauncher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RequestBuilder {

    public static String httpResponse = "";

    public String sendHTTPRequest(String param) throws IOException {
        String URLF = param;
        URL url = new URL(URLF.trim());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Error code:" + conn.getResponseCode());
        }
        Scanner scan = new Scanner(url.openStream());
        httpResponse = new String();
        while (scan.hasNext())
            httpResponse += scan.nextLine();
        scan.close();
        conn.disconnect();
        if (httpResponse != null) {
            FrameWorkLauncher.result.add("sendHTTPRequest,Pass");
            System.out.println("sendHTTPRequest:Pass");
        } else {
            FrameWorkLauncher.result.add("sendHTTPRequest,Fail");
            System.out.println("sendHTTPRequest:Fail");
        }

        return httpResponse;

    }
}
