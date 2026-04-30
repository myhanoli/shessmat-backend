package com.hanoli.demojwt.Auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.Jwt.JwtService;
import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        System.out.println("password:" + request.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        Usuario userEntity = userRepository.findByUsername(request.getUsername()).orElseThrow();
        UserDTO userDTO = UserDTO.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getNombre() + " " + userEntity.getApellidoPat() + " " + userEntity.getApellidoMat())
                .roles(userEntity.getRoles() != null ? new ArrayList<>(userEntity.getRoles()) : List.of())
                .build();
        String token = jwtService.getToken(userEntity);
        return AuthResponse.builder()
                .user(userDTO)
                .token(token)
                .expiresIn(jwtService.getExpiresIn())
                .build();

    }



    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getFirstname())
                .apellidoPat(request.getLastname())
                .email(request.getEmail())
                .roles(Set.of("user"))
                .build();

        userRepository.save(user);

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getNombre() + " " + user.getApellidoPat() + " " + user.getApellidoMat())
                .roles(new ArrayList<>(user.getRoles()))
                .build();

        return AuthResponse.builder()
                .user(userDTO)
                .token(jwtService.getToken(user))
                .expiresIn(jwtService.getExpiresIn())
                .build();

    }
}