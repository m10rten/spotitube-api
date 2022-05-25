package nl.han.oose.vdlei.spotitube.research;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.junit.jupiter.api.Test;

import javax.json.stream.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationIT {
  String token;
  @Test
  public void CorrectLoginTest() {
    ArrayList times = new ArrayList<>();
    JSONObject obj = new JSONObject();
    obj.put("user", "morty");
    obj.put("password", "mortypassword");
    for (int i = 0; i < 10; i++) {
      var start = new Date().getTime();
      try {
        URL url = new URL("http://localhost:8003/login");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(obj.toString());
        osw.flush();
        osw.close();
        os.close();
        con.connect();
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result2 = bis.read();
        while (result2 != -1) {
          buf.write((byte) result2);
          result2 = bis.read();
        }
        String result = buf.toString();
        JSONObject json = new JSONObject(result);
        String tokenResult = json.getString("token");
        token = tokenResult;
        System.out.println(token);
        assertEquals(con.getResponseCode(), 200);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        var end = new Date().getTime();
        var time = end - start;
        times.add(time);
      }
    }
    System.out.println(times);
  }

  @Test
  public void WrongLoginTest() {
    System.out.println(token);
    ArrayList times = new ArrayList<>();
    JSONObject obj = new JSONObject();
    obj.put("user", "wronguser");
    obj.put("password", "wrongpassword");
    for (int i = 0; i < 10; i++) {
      var start = new Date().getTime();
      try {
        URL url = new URL("http://localhost:8003/login");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(obj.toString());
        osw.flush();
        osw.close();
        os.flush();
        os.close();
        con.connect();
        BufferedInputStream bis;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        if (con.getResponseCode() > 299) {
          bis = new BufferedInputStream(con.getErrorStream());
        } else {
          bis = new BufferedInputStream(con.getInputStream());
          int result2 = bis.read();
          while (result2 != -1) {
            buf.write((byte) result2);
            result2 = bis.read();
          }
        }
        String result = buf.toString();
        System.out.println(result);
        assertEquals(con.getResponseCode(), 401);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        var end = new Date().getTime();
        var time = end - start;
        times.add(time);
      }
    }
    System.out.println(times);
  }


  @Test
  public void CorrectTokenPlaylistTest() {
    ArrayList times = new ArrayList<>();
    JSONObject obj = new JSONObject();
    obj.put("user", "morty");
    obj.put("password", "mortypassword");
    for (int i = 0; i < 10; i++) {
      var start = new Date().getTime();
      try {
        URL url = new URL("http://localhost:8003/login");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(obj.toString());
        osw.flush();
        osw.close();
        os.close();
        con.connect();
        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result21 = bis.read();
        while (result21 != -1) {
          buf.write((byte) result21);
          result21 = bis.read();
        }
        String result = buf.toString();
        JSONObject json = new JSONObject(result);
        String tokenResult = json.getString("token");
        token = tokenResult;
        System.out.println(token);
        URL url2 = new URL("http://localhost:8003/playlists?token=" + token);
        HttpURLConnection con2 = (HttpURLConnection) url.openConnection();
        con2.setDoOutput(true);
        con2.setRequestMethod("GET");
        con2.setRequestProperty("Content-Type", "application/json");
        OutputStream os2 = con2.getOutputStream();
        OutputStreamWriter osw2 = new OutputStreamWriter(os2, "UTF-8");
        osw2.flush();
        osw2.close();
        os2.flush();
        os2.close();
        con2.connect();
        BufferedInputStream bis2;
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        if (con.getResponseCode() > 299) {
          bis2 = new BufferedInputStream(con.getErrorStream());
        } else {
          bis2 = new BufferedInputStream(con.getInputStream());
          int result22 = bis2.read();
          while (result22 != -1) {
            buf2.write((byte) result22);
            result22 = bis2.read();
          }
        }
        String result2 = buf2.toString();
        System.out.println(result2);
        assertEquals(con.getResponseCode(), 200);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        var end = new Date().getTime();
        var time = end - start;
        times.add(time);
      }
    }
    System.out.println("correct token playlists:");
    System.out.println(times);
  }
}