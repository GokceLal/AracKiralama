package com.example.controller;

import com.example.dto.request.LoginRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.constant.RestApiUrls.LOGIN;
import static com.example.constant.RestApiUrls.REGISTER;
import static org.apache.naming.ResourceRef.AUTH;



@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {

    private final AuthService authService;
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterRequestDto> register (@RequestBody @Valid RegisterRequestDto dto){

        return ResponseEntity.ok(authService.register(dto));

    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }


}
