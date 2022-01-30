package com.kiwi.ApiServer.DAO;

import java.sql.*;

public class SQLDAO {
    private static Connection conn;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "JDBC:MYSQL://localhost:3306/?useSSL=false&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
//    private static final String PW = "";

    public SQLDAO() throws Exception{
        conn = DriverManager.getConnection(URL,USER,PW);
    }

    public ResultSet selectTest() throws SQLException,ClassNotFoundException {
        String query = "select * from TEST";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

}
