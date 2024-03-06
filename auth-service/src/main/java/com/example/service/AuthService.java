package com.example.service;

import com.example.dto.request.ActivateStatusRequestDto;

import com.example.dto.request.LoginRequestDto;
import com.example.dto.request.RegisterRequestDto;
import com.example.entity.Auth;
import com.example.exception.AuthServiceException;
import com.example.exception.ErrorType;
import com.example.mapper.AuthMapper;
import com.example.repository.AuthServiceRepository;
import com.example.utility.CodeGenerator;
import com.example.utility.JwtTokenManager;
import com.example.utility.enums.eStatue;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;


import java.util.Optional;




@Service
@RequiredArgsConstructor
public class AuthService  {

    private  AuthServiceRepository authRepository; // final yazınca kullanamıyorum
    private JwtTokenManager jwtTokenManager;





    public RegisterRequestDto register(RegisterRequestDto dto){
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        // TODO
        authRepository.save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterRequest(auth);

    }



    public String login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(authOptional.isEmpty()){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        }
        if (authOptional.get().getStatus().equals(eStatue.ACTIVE)) {
            return jwtTokenManager.createToken(authOptional.get().getId())
                    .orElseThrow(()-> new AuthServiceException(ErrorType.TOKEN_NOT_CREATED));
        } else {
            throw new AuthServiceException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }

    }

    public Boolean activateStatus(ActivateStatusRequestDto dto) {
        Optional<Auth> optionalAuth = findById(dto.getAuthId());
        if(optionalAuth.isEmpty()){
            throw new AuthServiceException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getActivationCode().equals(dto.getActivationCode())){
            optionalAuth.get().setStatus(eStatue.ACTIVE);
            update(optionalAuth.get());
            return true;
        } else {
            throw new AuthServiceException(ErrorType.ACTIVATION_CODE_ERROR);
        }
    }

    private void update(Auth auth) {
        authRepository.save(auth);
    }

    private Optional<Auth> findById(Long authId) {
        return authRepository.findById(authId);
    }





}

