package com.hfs.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hfs.core.HFSService;
import com.hfs.dao.DataDAO;
import com.hfs.model.Data;
import com.hfs.model.User;
import com.hfs.util.Constants;
import com.oreilly.servlet.MultipartRequest;

public class FileUploadServlet extends HttpServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
      User uModel = (User) req.getSession().getAttribute("user");
      new MultipartRequest(req, Constants.TEMP_FILE_UPLOAD);
      File folder = new File(Constants.TEMP_FILE_UPLOAD);
      File file = folder.listFiles()[0];
      String prefix = String.valueOf(System.currentTimeMillis());
      File newFile = new File(Constants.TEMP_FILE_UPLOAD + File.separator + prefix + "_" + file.getName());
      file.renameTo(newFile);

      HFSService hfsService = new HFSService();
      try
      {
         hfsService.put(newFile.getName());
      }
      catch (Exception e1)
      {
         e1.printStackTrace();
         resp.sendRedirect("addfile.jsp?msg=Error: " + e1.getMessage());
      }

      newFile.delete();
      file.delete();

      DataDAO dDao = new DataDAO();
      Data dModel = new Data();
      dModel.setEntrytime(new Timestamp(System.currentTimeMillis()));
      dModel.setFilename(newFile.getName());
      dModel.setId(prefix);
      dModel.setMimetype(newFile.getName().substring(newFile.getName().lastIndexOf(".")+1));
      dModel.setUsername(uModel.getEmail());
      dModel.setVersion("1");
      try
      {
         dDao.write(dModel);
         resp.sendRedirect("addfile.jsp?msg=File Upload to HDFS successful");
      }
      catch (Exception e2)
      {
         e2.printStackTrace();
         resp.sendRedirect("addfile.jsp?msg=Error: " + e2.getMessage());
      }
   }

}
