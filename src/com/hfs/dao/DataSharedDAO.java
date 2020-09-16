package com.hfs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hfs.model.DataSharing;
import com.hfs.util.DBConnection;

public class DataSharedDAO
{
   public void write(DataSharing model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("insert into data_sharing values (?,?,?) ");
         ps.setString(1, model.getId());
         ps.setString(2, model.getEmail());
         ps.setString(3, model.getAccesslevel());
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

   public void deleteAndWrite(DataSharing model) throws Exception
   {
      try
      {
         delete(model.getId(), model.getEmail());
         write(model);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw e;
      }
   }

   public List<DataSharing> getSharedFilesForUser(String email) throws Exception
   {
      List<DataSharing> result = new ArrayList<DataSharing>();

      Connection con = null;
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from data_sharing where email='" + email + "' ");
         while (rs.next())
         {
            DataSharing model = new DataSharing();
            model.setAccesslevel(rs.getString("accesslevel"));
            model.setEmail(rs.getString("email"));
            model.setId(rs.getString("id"));
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

   public List<DataSharing> getSharedUsersForFile(String id) throws Exception
   {
      List<DataSharing> result = new ArrayList<DataSharing>();

      Connection con = null;
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select * from data_sharing where id='" + id + "' ");
         while (rs.next())
         {
            DataSharing model = new DataSharing();
            model.setAccesslevel(rs.getString("accesslevel"));
            model.setEmail(rs.getString("email"));
            model.setId(rs.getString("id"));
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

   public void delete(String id, String email) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         con.createStatement().execute("delete from data_sharing where id='" + id + "' and email='" + email + "'  ");
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

   public void update(DataSharing model) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         PreparedStatement ps = con.prepareStatement("update data_sharing set accesslevel=? where id=? and email=?");
         ps.setString(2, model.getId());
         ps.setString(3, model.getEmail());
         ps.setString(1, model.getAccesslevel());
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

   public String getAccess(String email, String id) throws Exception
   {
      Connection con = null;
      try
      {
         con = DBConnection.connect();
         ResultSet rs = con.createStatement().executeQuery("select accesslevel from data_sharing where id='" + id + "' and email='" + email + "' ");
         rs.next();
         return rs.getString(1);
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
