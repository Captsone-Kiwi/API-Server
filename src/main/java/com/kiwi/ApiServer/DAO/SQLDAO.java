package com.kiwi.ApiServer.DAO;

import java.sql.*;


public class SQLDAO {
    private static Connection conn;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//
    private static final String URL = "JDBC:MYSQL://localhost:3306/tutum?useSSL=false&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
//
    private static final String PW = "qwer1234";

    public SQLDAO() throws Exception{
        conn = DriverManager.getConnection(URL,USER,PW);
    }

    public ResultSet selectTest() throws SQLException,ClassNotFoundException {
        String query = "select * from TEST";
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        return rs;
    }

    public ResultSet createInterview(String interview_name, String startTime, int template) throws SQLException,ClassNotFoundException{
        String query = "INSERT INTO interview(interview_name, start_time, template) " +
                "VALUES(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,interview_name);
        pstmt.setString(2,startTime);
        pstmt.setString(3,Integer.toString(template));
        pstmt.executeUpdate();

        query = "select id from interview where interview_name = (?) order by id";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1,interview_name);
        return pstmt.executeQuery();

    }

    public void insertInterviewParticipant(String interview_id, String user_email) throws Exception {
        String query = "INSERT INTO interview_participant(interview_id, user_email) " +
                "VALUES(?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, interview_id);
        pstmt.setString(2, user_email);
        pstmt.executeUpdate();
    }

    public ResultSet getInterviewList(String user_email) throws Exception{
        String query = "SELECT DISTINCT interview_id FROM interview_participant WHERE user_email = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,user_email);
        ResultSet res = pstmt.executeQuery();
        return res;
    }

    public ResultSet getInterviewFromId(String interviewId) throws Exception{
        String query = "SELECT * FROM interview WHERE id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,interviewId);
        ResultSet res = pstmt.executeQuery();
        return res;
    }

    public void deleteInterview(String id) throws Exception{
        String query = "DELETE FROM interview WHERE id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,id);
        pstmt.executeUpdate();

        query = "DELETE FROM interview_participant WHERE interview_id = (?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1,id);
        pstmt.executeUpdate();
    }

    public ResultSet getParticipantFromInterviewId(String id) throws Exception{
        String query = "SELECT user_email FROM interview_participant WHERE interview_id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,id);
        ResultSet result = pstmt.executeQuery();
        return result;
    }

    public ResultSet getUsernameFromEmail(String email) throws Exception{
        String query = "SELECT name FROM user WHERE email = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,email);
        return pstmt.executeQuery();
    }
}
