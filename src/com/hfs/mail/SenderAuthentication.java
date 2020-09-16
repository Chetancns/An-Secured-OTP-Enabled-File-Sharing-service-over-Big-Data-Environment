package com.hfs.mail;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.hfs.util.Constants;

public class SenderAuthentication extends Authenticator
{
   @Override
   protected PasswordAuthentication getPasswordAuthentication()
   {

      Properties props = new Properties();
      try
      {
         props.load(new FileInputStream(new File(Constants.PROPERTIES_FILE)));
         return (new PasswordAuthentication(
                  props.getProperty("senderemail"),
                  props.getProperty("senderpassword")));

      }
      catch (Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }

}
