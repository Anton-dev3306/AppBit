package com.appbit.seguridad.service.impl;

import com.appbit.candidatos.entity.Candidato;
import com.appbit.empresas.entity.Empresa;
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
import java.util.stream.Collectors;

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

        Set<String> rolesNames = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());

        return new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre(), rolesNames);
    }

    @Override
    public AuthResponseDTO register(RegisterDTO request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String rolInput = request.getRol();
        if (rolInput == null || rolInput.trim().isEmpty()) {
            rolInput = "CANDIDATO";
        }
        String rolName = rolInput.trim().toUpperCase();
        if (rolName.equals("ENCARGADO")) {
            rolName = "EMPRESA";
        }

        if (!rolName.equals("CANDIDATO") && !rolName.equals("EMPRESA") && !rolName.equals("USER")) {
            throw new IllegalArgumentException("Rol no válido. Debe ser CANDIDATO, EMPRESA o ENCARGADO");
        }

        Usuario usuario;
        if (rolName.equals("CANDIDATO")) {
            usuario = new Candidato();
        } else if (rolName.equals("EMPRESA")) {
            usuario = new Empresa();
        } else {
            usuario = new Usuario();
        }

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);

        final String finalRolName = rolName;
        Rol targetRol = rolRepository.findByNombre(finalRolName)
                .orElseGet(() -> {
                    Rol newRol = new Rol();
                    newRol.setNombre(finalRolName);
                    return rolRepository.save(newRol);
                });

        Set<Rol> roles = new HashSet<>();
        roles.add(targetRol);
        usuario.setRoles(roles);

        usuarioRepository.save(usuario);

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        Set<String> rolesNames = usuario.getRoles().stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());

        return new AuthResponseDTO(token, usuario.getEmail(), usuario.getNombre(), rolesNames);
    }
}
