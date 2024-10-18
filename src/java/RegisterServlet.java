/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
        private final static String query="insert into user(name,email,mobile,dob,city,gender) values (?,?,?,?,?,?)";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter pw= res.getWriter();
        try {
            pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
            
            String name = req.getParameter("userName");
            String email= req.getParameter("email");
            String dob = req.getParameter("dob");
            String city = req.getParameter("city");
            String mobile = req.getParameter("mobile");
            String gender = req.getParameter("gender");
            
            Class.forName("com.mysql.cj.jdbc.Driver");
          Connection con= DriverManager.getConnection("jdbc:mysql:///usermgmt","root","Nand@2003");
        //    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","Nand@2003");
              PreparedStatement ps = con.prepareStatement(query);
              {
            //set the values
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobile);
            ps.setString(4, dob);
            ps.setString(5, city);
            ps.setString(6, gender);
            //execute the query}
            int count = ps.executeUpdate();
           pw.println("<div class=\"card\" style=\"margin: auto; width: 300px; margin-top: 100px;\">");
            if(count==1) {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Registered Successfully</h2>");
            }else {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Not Registered</h2>");
            }
        }
            
        }catch(SQLException se) {
            pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
            se.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        } pw.println("</div>");
           pw.println("<a href='index.html'><div class=\"d-flex justify-content-center\">\n" +
"    <button class=\"btn btn-outline-success\">Home</button>\n" +
"  </div></a>");
       
        //close the stram
        pw.close();  
            
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
