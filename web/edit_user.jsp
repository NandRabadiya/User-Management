<%-- 
    Document   : edit_user
    Created on : Oct 18, 2024, 10:19:08 PM
    Author     : hp
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel='stylesheet' href='css/bootstrap.css'>
</head>
<body>
<%
    String query = "select name,email,mobile,dob,city,gender from user where id=?";
    int id = Integer.parseInt(request.getParameter("id"));
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch(Exception e) {
        e.printStackTrace();
    }
    
    try (Connection con = DriverManager.getConnection("jdbc:mysql:///usermgmt","root","Nand@2003");
         PreparedStatement ps = con.prepareStatement(query)) {
        
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
%>
    <div style='margin:auto;width:500px;margin-top:100px;'>
        <form action='edit?id=<%= id %>' method='post'>
            <table class='table table-hover table-striped'>
                <tr>
                    <td>Name</td>
                    <td><input type='text' name='name' value='<%= rs.getString(1) %>'></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type='email' name='email' value='<%= rs.getString(2) %>'></td>
                </tr>
                <tr>
                    <td>Mobile</td>
                    <td><input type='text' name='mobile' value='<%= rs.getString(3) %>'></td>
                </tr>
                <tr>
                    <td>DOB</td>
                    <td><input type='date' name='dob' value='<%= rs.getString(4) %>'></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type='text' name='city' value='<%= rs.getString(5) %>'></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><input type='text' name='gender' value='<%= rs.getString(6) %>'></td>
                </tr>
                <tr>
                    <td><button type='submit' class='btn btn-outline-success'>Save</button></td>
                    <td><button type='reset' class='btn btn-outline-danger'>Cancel</button></td>
                </tr>
            </table>
        </form>
        <a href='index.html'><button class='btn btn-outline-success'>Home</button></a>
    </div>
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
</body>
</html>
