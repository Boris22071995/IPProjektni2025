package net.etfbl.facultyapp.service;

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

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";

    public static String get(String path) {
        try {
            URL url = new URL(BASE_URL + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == 200) {
                return read(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String path, String json) {
        try {
            URL url = new URL(BASE_URL + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            if (con.getResponseCode() < 300) {
                return read(con);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void put(String path) {
        try {
            URL url = new URL(BASE_URL + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            con.getResponseCode(); // trigger request
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String read(HttpURLConnection con) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
    
    public static void put(String path, String json) {
        try {
            URL url = new URL(BASE_URL + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes("UTF-8"));
            }

            con.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void delete(String path) {
        try {
        	System.out.print("Da li sam na frontu za brisanje");
            URL url = new URL(BASE_URL + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("DELETE");
            con.getResponseCode(); // trigger request

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

