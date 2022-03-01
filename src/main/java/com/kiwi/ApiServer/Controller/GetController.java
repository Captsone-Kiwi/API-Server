package com.kiwi.ApiServer.Controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

@RestController
public class GetController {
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
