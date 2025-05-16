package com.example.multipart_request_example.controller;

import com.example.multipart_request_example.dto.UserDTO;
import com.example.multipart_request_example.service.UserService;
import com.example.multipart_request_example.util.response.StandardResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<StandardResponse> saveUserInfo(
            @RequestPart("user") String userJson,
            @RequestPart("file")MultipartFile file) throws JsonProcessingException {
        UserDTO userDTO = new ObjectMapper().readValue(userJson, UserDTO.class);
        UserDTO userDTO1 = userService.saveUserInfo(userDTO, file);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        200,
                        "Successfully!",
                        userDTO1
                ),
                HttpStatus.CREATED
        );
    }
}
