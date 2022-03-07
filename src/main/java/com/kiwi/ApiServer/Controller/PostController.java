package com.kiwi.ApiServer.Controller;

import com.kiwi.ApiServer.DAO.UserRepository;
import com.kiwi.ApiServer.DTO.ResponseMessage;
import com.kiwi.ApiServer.DTO.User;
import com.kiwi.ApiServer.Response.SingleResult;
import com.kiwi.ApiServer.Security.JwtTokenProvider;
import com.kiwi.ApiServer.Service.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    @Autowired
    FileStorageService storageService;

//  password encoding -> 아직 적용 안함
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 회원가입
    @PostMapping(value = "/signup")
    public SingleResult join(@RequestBody Map<String, String> user) {
        SingleResult result = new SingleResult();
        Long id = userRepository.save(User.builder()
                .email(user.get("email"))
//                .password(passwordEncoder.encode(user.get("password")))
                .password(user.get("password"))
                .name(user.get("name"))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();

        result.setResult(200);
        result.setMessage("success");
        result.setData(id);
        return result;
    }

    // 로그인
    @PostMapping("/signin")
    public SingleResult login(@RequestBody Map<String, String> user) {
        SingleResult result = new SingleResult();

//        User member = userRepository.findByEmail(user.get("email"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));

        User member = userRepository.findByEmail(user.get("email"))
                .orElse(null);
        if(member == null){
//            System.out.println("null");
            result.setResult(400);
            result.setMessage("user not found");
        }else{
//            System.out.println(member.toString());

            if(!user.get("password").equals(member.getPassword())){
//                throw new IllegalArgumentException("잘못된 비밀번호입니다.");
                result.setResult(401);
                result.setMessage("wrong password");
            }else{
                result.setResult(200);
                result.setMessage("success");
                result.setData(jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
            }
        }
        return result;
    }

    @PostMapping(value = "upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
        String message = "";
        try{
            storageService.save(file);

            message = "Uploaded the file sucessfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch(Exception e){
            message = "Could not upload the file: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
