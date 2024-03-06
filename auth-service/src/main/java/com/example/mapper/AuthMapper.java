package com.example.mapper;

import com.example.dto.request.RegisterRequestDto;
import com.example.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromRegisterRequestToAuth(final RegisterRequestDto dto);
    RegisterRequestDto fromAuthToRegisterRequest(final Auth auth);




}
