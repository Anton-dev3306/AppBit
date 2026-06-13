package com.appbit.seguridad.service;

import com.appbit.seguridad.dto.request.LoginRequestDTO;
import com.appbit.seguridad.dto.request.RegisterDTO;
import com.appbit.seguridad.dto.response.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(LoginRequestDTO request);
    AuthResponseDTO register(RegisterDTO request);
}