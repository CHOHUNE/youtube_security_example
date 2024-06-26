package com.example.demo.service;

import com.example.demo.dto.JoinDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDto joinDto) {



        //db 에 이미 동일한 username 을 가진 회원이 존재 하는지 확인한 후에 이후 로직 진행 해야함
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if (isUser) {
            return ;
        }


        UserEntity user = new UserEntity();

        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);


    }

}
