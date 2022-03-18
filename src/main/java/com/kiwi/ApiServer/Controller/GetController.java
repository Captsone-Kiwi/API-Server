package com.kiwi.ApiServer.Controller;

import com.kiwi.ApiServer.DAO.SQLDAO;
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
                tmp.setStartTime(interview_tmp.getString(3));
                tmp.setTemplate(interview_tmp.getInt(4));
                data.add(tmp);
            }
        }
        res.setData(200);
        res.setMessage("success");
        res.setData(data);
        return res;
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
