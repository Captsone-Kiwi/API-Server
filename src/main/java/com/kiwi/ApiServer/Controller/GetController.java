package com.kiwi.ApiServer.Controller;

import com.kiwi.ApiServer.DAO.SQLDAO;
import com.kiwi.ApiServer.DTO.Evaluation.*;
import com.kiwi.ApiServer.DTO.User;
import com.kiwi.ApiServer.Response.SingleResult;
import com.kiwi.ApiServer.Security.JwtTokenProvider;
import com.kiwi.ApiServer.Table.interview;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GetController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/user/test")
    public String test(){
        return "success";
    }

    @GetMapping("/getInterview")
    public SingleResult getInterview(HttpServletRequest request) throws Exception{
        SingleResult res = new SingleResult();
        List<interview> data = new ArrayList<>();
        SQLDAO sqldao = new SQLDAO();

        String token = request.getHeader("X-AUTH-TOKEN");
        System.out.println(token);
        String user = jwtTokenProvider.getUser(token);
        System.out.println(user);

        ResultSet interview_list = sqldao.getInterviewList(user);
        while(interview_list.next()){
            String interviewId = interview_list.getString("interview_id");
            ResultSet interview_tmp = sqldao.getInterviewFromId(interviewId);
            while(interview_tmp.next()){
                interview tmp = new interview();
                tmp.setId(interview_tmp.getLong(1));
                tmp.setInterview_name(interview_tmp.getString(2));
                tmp.setStartDate(interview_tmp.getString(3));
                tmp.setStartTime(interview_tmp.getString(4));
                tmp.setTemplate(interview_tmp.getInt(5));
                data.add(tmp);
            }
        }
        res.setData(200);
        res.setMessage("success");
        res.setData(data);
        return res;
    }

    @GetMapping("/getUsername")
    public SingleResult getUsername(HttpServletRequest request) throws Exception{
        SingleResult result = new SingleResult();
        SQLDAO sqldao = new SQLDAO();
        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUser(token);

        ResultSet username = sqldao.getUsernameFromEmail(email);
        while(username.next()){
            result.setData(username.getString(1));
//            System.out.println(username.getString(1));
        }
        result.setResult(200);
        result.setMessage("success");
        return result;
    }

    @GetMapping("/getUser")
    public SingleResult getUser(HttpServletRequest request) throws Exception {
        SingleResult result = new SingleResult();
        SQLDAO sqldao = new SQLDAO();
        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUser(token);

       ResultSet userList = sqldao.getUserFromEmail(email);
       User user = new User();
       while(userList.next()){
           user.setName(userList.getString(1));
           user.setEmail(userList.getString(2));
           user.setMemberType(userList.getInt(3));
       }
       result.setResult(200);
       result.setMessage("success");
       result.setData(user);
        return result;
    }

    @GetMapping("/deleteInterview")
    public SingleResult deleteInterview(@RequestParam String id) throws Exception{
        SingleResult result = new SingleResult();
        SQLDAO sqldao = new SQLDAO();
        sqldao.deleteInterview(id);

        result.setResult(200);
        result.setMessage("success");
        return result;
    }

    @GetMapping("/participant")
    public SingleResult getParticipantList(@RequestParam String id) throws Exception {
        SingleResult result = new SingleResult();

        SQLDAO sqldao = new SQLDAO();
        ResultSet participantList = sqldao.getParticipantFromInterviewId(id);
        List<String> data = new ArrayList<>();

        while(participantList.next()){
            String participant = participantList.getString("user_email");
            data.add(participant);
        }

        result.setResult(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    @GetMapping("/getEvaluationIdList")
    public SingleResult getEvaluationIdList(HttpServletRequest request) throws Exception{
        SingleResult result = new SingleResult();
        SQLDAO sqldao = new SQLDAO();

        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUser(token);
        int user_id = sqldao.getUserIdFromEmail(email);

        List<Integer> evaluationIdList = sqldao.getEvaluationIdListFromUserId(user_id);

        result.setResult(200);
        result.setMessage("SUCCESS");
        result.setData(evaluationIdList);
        return result;
    }


    @GetMapping("/getEvaluation")
    public SingleResult getEvaluation(@RequestParam String evaluationId) throws Exception {
        SingleResult result = new SingleResult();
        SQLDAO sqldao = new SQLDAO();
        Evaluation evaluation = new Evaluation();
        evaluation.setEvaluationList(new ArrayList<>());

        String name = sqldao.getNameFromEvaluation(evaluationId);
        evaluation.setName(name);

        List<EvaluationCategory> evaluationCategoryList = sqldao.getEvaluationCategoryFromId(evaluationId);
        for(EvaluationCategory evaluationCategory : evaluationCategoryList){
            boolean containsCategory = false;
            for(EvaluationList evaluationList : evaluation.getEvaluationList()){
                if(evaluationList.getCategory().equals(evaluationCategory.getCategory())){
                    containsCategory = true;

                    EvaluationQuestion evaluationQuestion = new EvaluationQuestion();
                    evaluationQuestion.setTitle(evaluationCategory.getTitle());
                    evaluationQuestion.setType(evaluationCategory.getType());

//                    if(evaluationQuestion.getType() == 1){
//                        List<String> data = sqldao.getEvaluationChoiceFromQuestionId(evaluationCategory.getQuestion_id());
//                        evaluationQuestion.setData(data);
//                    }

                    evaluationList.getQuestions().add(evaluationQuestion);

                    break;
                }
            }
            if(!containsCategory){
                EvaluationList evaluationList = new EvaluationList();
                evaluationList.setQuestions(new ArrayList<>());
                EvaluationQuestion evaluationQuestion = new EvaluationQuestion();

                String category = evaluationCategory.getCategory();
                evaluationQuestion.setTitle(evaluationCategory.getTitle());
                evaluationQuestion.setType(evaluationCategory.getType());

//                if(evaluationQuestion.getType() == 1){
//                    List<String> data = sqldao.getEvaluationChoiceFromQuestionId(evaluationCategory.getQuestion_id());
//                    evaluationQuestion.setData(data);
//                }

                evaluationList.setCategory(category);
                evaluationList.getQuestions().add(evaluationQuestion);
                evaluation.getEvaluationList().add(evaluationList);
            }
        }
        result.setResult(200);
        result.setMessage("SUCCESS");
        result.setData(evaluation);
        return result;
    }

    @GetMapping("/getResume")
    public ResponseEntity<InputStreamResource> getResume(@RequestParam String name){
        String path = "./uploads/";
        String file_name = name + ".pdf";
        System.out.println(file_name);
        File file = new File(path + file_name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +file_name);
        InputStreamResource resource = null;
        try{
            resource = new InputStreamResource(new FileInputStream(file));
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);

    }

    @GetMapping("/viewpdf")
    public ResponseEntity<InputStreamResource> getViewPdf() {
        System.out.println("connected");

        String path = "./uploads/";
        String file_name = "CV2.pdf";
        File file = new File(path + file_name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +file_name);
        InputStreamResource resource = null;
        try{
            resource = new InputStreamResource(new FileInputStream(file));
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
