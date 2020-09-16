package com.hfs.model;

import java.sql.Timestamp;

public class Data
{
   private String id;
   private String username;
   private String filename;
   private String mimetype;
   private String version;
   private Timestamp entrytime;

   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getFilename()
   {
      return filename;
   }

   public void setFilename(String filename)
   {
      this.filename = filename;
   }

   public String getMimetype()
   {
      return mimetype;
   }

   public void setMimetype(String mimetype)
   {
      this.mimetype = mimetype;
   }

   public String getVersion()
   {
      return version;
   }

   public void setVersion(String version)
   {
      this.version = version;
   }

   public Timestamp getEntrytime()
   {
      return entrytime;
   }

   public void setEntrytime(Timestamp entrytime)
   {
      this.entrytime = entrytime;
   }

}
