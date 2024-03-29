package com.wikiT.demo.service;


import com.wikiT.demo.domain.User;
import com.wikiT.demo.dto.AddUserRequest;
import com.wikiT.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User submit(AddUserRequest request){

        if(userRepository.findByEmail(request.getEmail()).isEmpty()){
            return userRepository.save(User.builder()
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(request.getPassword()))
                    .build());
        }
        else{
            return null;
        }
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found email"));
    }
}
