package com.example.multipart_request_example.service;

import com.example.multipart_request_example.dto.UserDTO;
import com.example.multipart_request_example.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    public UserDTO saveUserInfo(UserDTO user, MultipartFile file);
}
