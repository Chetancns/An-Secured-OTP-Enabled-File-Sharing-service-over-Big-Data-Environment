package com.hfs.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hfs.mail.MailThread;
import com.hfs.model.User;
import com.hfs.sms.SendMessage;

public class OTP
{

   public static String generateVerificationCodeForEmail()
   {
      String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
               + "0123456789"
               + "abcdefghijklmnopqrstuvxyz";
      StringBuilder sb = new StringBuilder(10);
      for (int i = 0; i < 10; i++)
      {
         int index = (int) (alphaNumericString.length() * Math.random());
         sb.append(alphaNumericString.charAt(index));
      }
      return sb.toString();
   }

   public static String generateVerificationCodeForMobile()
   {
      String numericString = "0123456789";
      StringBuilder sb = new StringBuilder(6);
      for (int i = 0; i < 6; i++)
      {
         int index = (int) (numericString.length() * Math.random());
         sb.append(numericString.charAt(index));
      }
      return sb.toString();
   }

   public void generateOTP(HttpServletRequest req)
   {
      User user = (User) req.getSession().getAttribute("user");
      String mobileOTP = generateVerificationCodeForMobile();
      String emailOTP = generateVerificationCodeForEmail();
      List<String> rcv = new ArrayList<String>();
      rcv.add(user.getEmail());
      new MailThread("Hello, <br/>Your Email OTP for accessing File on HDFS is : <br/>" + emailOTP, "OTP for Accessing File on HDFS", rcv);
      SendMessage.sendSms(user.getMobile(), "Your mobile OTP for accessing File on HDFS is: " + mobileOTP);

      System.out.println("Email OTP: " + emailOTP);
      System.out.println("Mobile OTP: " + mobileOTP);

      req.getSession().setAttribute("mobileOTP", mobileOTP);
      req.getSession().setAttribute("emailOTP", emailOTP);
   }

   public boolean verifyOTP(HttpServletRequest req, String mobileOTP, String emailOTP)
   {
      String trueEmailOTP = (String) req.getSession().getAttribute("emailOTP");
      String trueMobileOTP = (String) req.getSession().getAttribute("mobileOTP");
      if (trueEmailOTP.equals(emailOTP) && trueMobileOTP.equals(mobileOTP))
         return true;
      else
         return false;
   }
}
