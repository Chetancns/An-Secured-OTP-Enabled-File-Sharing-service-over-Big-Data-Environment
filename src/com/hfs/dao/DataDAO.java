package com.hfs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hfs.model.Data;
import com.hfs.util.DBConnection;

public class DataDAO
{

   public void write(Data model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("insert into data values (?,?,?,?,?,?) ");
         ps.setString(1, model.getId());
         ps.setString(2, model.getUsername());
         ps.setString(3, model.getFilename());
         ps.setString(4, model.getMimetype());
         ps.setString(5, model.getVersion());
         ps.setTimestamp(6, model.getEntrytime());
         ps.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
   }

   
   public void reupload(Data model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("update data set filename=?, mimetype=?, version=version+1, entrytime=? where id=? ");
         
         ps.setString(1, model.getFilename());
         ps.setString(2, model.getMimetype());
         ps.setTimestamp(3, model.getEntrytime());
         ps.setString(4, model.getId());
         ps.execute();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
   }

   public List<Data> read(String email) throws Exception
   {
      Connection con = null;
      List<Data> result = new ArrayList<Data>();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from data where username='" + email + "' ");
         while (rs.next())
         {
            Data model = new Data();
            model.setEntrytime(rs.getTimestamp("entrytime"));
            model.setFilename(rs.getString("filename"));
            model.setId(rs.getString("id"));
            model.setMimetype(rs.getString("mimetype"));
            model.setUsername(rs.getString("username"));
            model.setVersion(rs.getString("version"));
            result.add(model);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
      return result;
   }

   public Data getDetails(String id) throws Exception
   {
      Connection con = null;
      Data model = new Data();
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from data where id='" + id + "' ");
         rs.next();
         model.setEntrytime(rs.getTimestamp("entrytime"));
         model.setFilename(rs.getString("filename"));
         model.setId(rs.getString("id"));
         model.setMimetype(rs.getString("mimetype"));
         model.setUsername(rs.getString("username"));
         model.setVersion(rs.getString("version"));

      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
      return model;
   }

   public void delete(String id) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("delete from data where id='" + id + "' ");
         con.createStatement().execute("delete from data_sharing where id='"+id+"' ");
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
   }

   public void updateVersion(String id) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select version from data where id='" + id + "'");
         rs.next();
         int version = Integer.parseInt(rs.getString("version"));

         con.createStatement().execute("update data set version='" + String.valueOf(version + 1) + "' where id='" + id + "'");
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
      finally
      {
         con.close();
      }
   }
}
