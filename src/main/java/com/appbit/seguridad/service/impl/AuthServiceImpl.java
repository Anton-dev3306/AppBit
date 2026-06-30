package com.appbit.seguridad.service.impl;

import com.appbit.seguridad.JwtUtil;
import com.appbit.seguridad.dto.request.LoginRequestDTO;
import com.appbit.seguridad.dto.request.RegisterDTO;
import com.appbit.seguridad.dto.response.AuthResponseDTO;
import com.appbit.seguridad.entity.Rol;
import com.appbit.seguridad.entity.Usuario;
import com.appbit.seguridad.repository.RolRepository;
import com.appbit.seguridad.repository.UsuarioRepository;
import com.appbit.seguridad.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre());
    }

    @Override
    public AuthResponseDTO register(RegisterDTO request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);

        Rol userRol = rolRepository.findByNombre("USER")
                .orElseGet(() -> {
                    Rol newRol = new Rol();
                    newRol.setNombre("USER");
                    return rolRepository.save(newRol);
                });

        Set<Rol> roles = new HashSet<>();
        roles.add(userRol);
        usuario.setRoles(roles);

        usuarioRepository.save(usuario);

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre());
    }
}
