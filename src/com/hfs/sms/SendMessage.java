package com.hfs.sms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.hfs.util.Constants;

public class SendMessage
{

   public static String sendSms(String number, String msg)
   {
      Properties props = new Properties();
      try
      {
         props.load(new FileInputStream(new File(Constants.PROPERTIES_FILE)));
         // Construct data
         String apiKey = "apikey=" + props.getProperty("smsapikey");
         String message = "&message=" + msg;
         String sender = "&sender=" + "TXTLCL";
         String numbers = "&numbers=" + number;

         // Send data
         HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
         String data = apiKey + numbers + message + sender;
         conn.setDoOutput(true);
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
         conn.getOutputStream().write(data.getBytes("UTF-8"));
         final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         final StringBuffer stringBuffer = new StringBuffer();
         String line;
         while ((line = rd.readLine()) != null)
         {
            stringBuffer.append(line);
         }
         rd.close();

         return stringBuffer.toString();
      }
      catch (Exception e)
      {
         System.out.println("Error SMS " + e);
         return "Error " + e;
      }
   }

}
