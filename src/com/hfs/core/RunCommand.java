package com.hfs.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunCommand
{
   public static String run(String cmd)
   {
      String s;
      Process p;
      String result = "";
      try
      {
         p = Runtime.getRuntime().exec(cmd);
         BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
         while ((s = br.readLine()) != null)
         {
            result += s;
            result += "@-@-";
         }
         p.waitFor();
         p.destroy();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return result;
   }

}
