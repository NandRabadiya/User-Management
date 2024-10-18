<%-- 
    Document   : edit_record
    Created on : Oct 18, 2024, 10:31:46 PM
    Author     : hp
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Record</title>
    <link rel='stylesheet' href='css/bootstrap.css'>
</head>
<body>
<%
    String query = "update user set name=?,email=?,mobile=?,dob=?,city=?,gender=? where id=?";
    
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String mobile = request.getParameter("mobile");
    String dob = request.getParameter("dob");
    String city = request.getParameter("city");
    String gender = request.getParameter("gender");
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch(Exception e) {
        e.printStackTrace();
    }
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt","root","Nand@2003");
         PreparedStatement ps = con.prepareStatement(query)) {
        
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, mobile);
        ps.setString(4, dob);
        ps.setString(5, city);
        ps.setString(6, gender);
        ps.setInt(7, id);
        
        int count = ps.executeUpdate();
%>
    <div class='card' style='margin:auto;width:300px;margin-top:100px'>
<%
        if(count == 1) {
%>
        <h2 class='bg-danger text-light text-center'>Record Edited Successfully</h2>
<%
        } else {
%>
        <h2 class='bg-danger text-light text-center'>Record Not Edited</h2>
<%
        }
    } catch(SQLException se) {
%>
    <h2 class='bg-danger text-light text-center'><%= se.getMessage() %></h2>
<%
        se.printStackTrace();
    } catch(Exception e) {
        e.printStackTrace();
    }
%>
    <a href='index.html'><button class='btn btn-outline-success'>Home</button></a>
    &nbsp; &nbsp;
    <a href='showdata'><button class='btn btn-outline-success'>Show User</button></a>
    </div>
</body>
</html>