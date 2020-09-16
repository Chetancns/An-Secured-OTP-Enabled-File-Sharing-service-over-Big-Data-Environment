package com.hfs.core;

import java.io.File;

import com.hfs.util.Constants;

public class HFSService
{
   public void put(String filename) throws Exception
   {
      String command = Constants.HADOOP_INSTALLATION_PATH + "/hadoop fs -put " + Constants.TEMP_FILE_UPLOAD + File.separator + filename + " " + Constants.DATA_PATH;
      RunCommand.run(command);
   }

   public void remove(String filename) throws Exception
   {
      String command = Constants.HADOOP_INSTALLATION_PATH + "/hadoop fs -rm " + Constants.DATA_PATH + File.separator + filename;
      RunCommand.run(command);
   }

   public void get(String filename) throws Exception
   {
      String command = Constants.HADOOP_INSTALLATION_PATH + "/hadoop fs -get " + Constants.DATA_PATH + File.separator + filename + " " + Constants.TEMP_FILE_DOWNLOAD;
      RunCommand.run(command);
   }

}
