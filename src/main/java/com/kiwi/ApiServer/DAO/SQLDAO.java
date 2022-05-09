package com.kiwi.ApiServer.DAO;

import com.kiwi.ApiServer.DTO.Evaluation.EvaluationCategory;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    public ResultSet createInterview(String interview_name, String startDate , String startTime, int template) throws SQLException,ClassNotFoundException{
        String query = "INSERT INTO interview(interview_name,start_date, start_time, template) " +
                "VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,interview_name);
        pstmt.setString(2,startDate);
        pstmt.setString(3,startTime);
        pstmt.setString(4,Integer.toString(template));
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

    public ResultSet getUserFromEmail(String email) throws Exception {
        String query = "SELECT name, email, member_type FROM user where email = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,email);
        return pstmt.executeQuery();
    }

    public int getUserIdFromEmail(String email) throws Exception {
        String query = "SELECT id FROM user WHERE email = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,email);
        pstmt.executeQuery();

        ResultSet result = pstmt.executeQuery();

        int id = 0;
        if(result.next())
            id = result.getInt(1);
        return id;

    }


    public int insertEvaluation(String name,int user_id) throws Exception {
//        String query = "INSERT INTO interview_participant(interview_id, user_email) " +
//                "VALUES(?,?)";
        String query =  "INSERT INTO evaluation(name,user_id) " +
//                        "OUTPUT id " +
                        "VALUES (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,name);
        pstmt.setInt(2,user_id);
        pstmt.executeUpdate();

        int id = 0;
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()){
            if (generatedKeys.next())
                id = generatedKeys.getInt(1);
            else
                throw new SQLException("Creating evaluation failed");
        }
        return id;
    }

    public int insertEvaluationQuestion(int evaluationId, int type, String title, String category) throws Exception {
        String query = "INSERT INTO evaluation_category(evaluation_id, type, title, category) " +
                "VALUES (?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, Integer.toString(evaluationId));
        pstmt.setString(2, Integer.toString(type));
        pstmt.setString(3, title);
        pstmt.setString(4, category);
        pstmt.executeUpdate();

        int id = 0;
        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()){
            if (generatedKeys.next())
                id = generatedKeys.getInt(1);
            else
                throw new SQLException("Creating evaluation category failed");
        }
        return id;
    }

    public List<Integer> getEvaluationIdListFromUserId(int user_id) throws Exception{
        String query = "SELECT id FROM evaluation " +
                "WHERE user_id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,user_id);

        ResultSet result = pstmt.executeQuery();
        List<Integer> evaluationIdList = new ArrayList<>();
        while(result.next()){
            int evaluationId = result.getInt("id");
            evaluationIdList.add(evaluationId);
        }

        return evaluationIdList;

    }

    public void insertEvaluationChoice(int question_id, String data) throws Exception {
        String query = "INSERT INTO evaluation_choice(question_num,data) " +
                "VALUES (?,?)";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1,question_id);
        pstmt.setString(2,data);
        pstmt.executeUpdate();
    }

    public String getNameFromEvaluation(String evaluationId) throws Exception{
        String query =  "SELECT name " +
                        "FROM evaluation " +
                        "WHERE id = (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, evaluationId);
        ResultSet result = pstmt.executeQuery();

        String name = "";
        if(result.next())
            name = result.getString(1);
        return name;
    }

    public List<EvaluationCategory> getEvaluationCategoryFromId(String evaluationId) throws Exception{
        List<EvaluationCategory> evaluationCategoryList = new ArrayList<>();
        String query = "SELECT question_id, evaluation_id, type, title, category " +
                "FROM evaluation_category " +
                "WHERE evaluation_id = (?)";

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, evaluationId);
        ResultSet result = pstmt.executeQuery();

        while(result.next()){
            EvaluationCategory evaluationCategory = new EvaluationCategory();
            evaluationCategory.setQuestion_id(result.getInt(1));
            evaluationCategory.setEvaluation_id(result.getInt(2));
            evaluationCategory.setType(result.getInt(3));
            evaluationCategory.setTitle(result.getString(4));
            evaluationCategory.setCategory(result.getString(5));

            evaluationCategoryList.add(evaluationCategory);
        }

        return evaluationCategoryList;
    }

    public List<String> getEvaluationChoiceFromQuestionId(int question_id) throws Exception{
        String query = "SELECT data " +
                "FROM evaluation_choice " +
                "WHERE question_id = (?)";
        List<String> data = new ArrayList<>();

        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, question_id);

        ResultSet result = pstmt.executeQuery();
        while(result.next()){
            data.add(result.getString(1));
        }

        return data;
    }


}
