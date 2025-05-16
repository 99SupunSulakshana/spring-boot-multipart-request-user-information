package com.example.multipart_request_example.service.serviceimpl;

import com.example.multipart_request_example.dto.UserDTO;
import com.example.multipart_request_example.entity.User;
import com.example.multipart_request_example.repository.UserRepository;
import com.example.multipart_request_example.service.UserService;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String STORAGE_DIRECTORY = "D:\\spring boot serious\\multipart-request-example";

    @Override
    public UserDTO saveUserInfo(UserDTO userDTO, MultipartFile file) {
        try{
            if(file == null){
                throw new NullPointerException("file is empty.");
            }
            User user = modelMapper.map(userDTO, User.class);
            if(userRepository.existsByEmail(userDTO.getEmail())){
                throw new RuntimeException("User with this name already exists!");
            }
//            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get(STORAGE_DIRECTORY, fileName);
//            Files.copy(file.getInputStream(), filePath);

            var targetFile = new File(STORAGE_DIRECTORY + File.separator + file.getOriginalFilename());
            if(!Objects.equals(targetFile.getParent(), STORAGE_DIRECTORY)){
                throw new SecurityException("Unsupported filename");
            }
            Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            user.setProfileImagePath(targetFile.getPath());
            userRepository.save(user);
            return modelMapper.map(user, UserDTO.class);
        }catch (IOException e){
            throw new RuntimeException("Failed to save user", e);
        }
    }
}
