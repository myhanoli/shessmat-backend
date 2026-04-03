package com.hanoli.demojwt.User;
import org.springframework.stereotype.Service;

import com.hanoli.demojwt.entity.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository; 

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
        Usuario user = Usuario.builder()
        .id((long)userRequest.getId())
        .nombre(userRequest.getNombre())
        .apellidoPat(userRequest.getApellidoPat())
        .apellidoMat(userRequest.getApellidoMat())
        .build();
        
        userRepository.updateUser(user.getId().intValue(), user.getNombre(), user.getApellidoPat(), user.getApellidoMat());

        return new UserResponse("El usuario se actualizó satisfactoriamente");
    }

    public UserDTO2 getUser(Integer id) {
        Usuario user= userRepository.findById(id).orElse(null);
       
        if (user!=null)
        {
            UserDTO2 userDTO = UserDTO2.builder()
            .id(user.id)
            .username(user.getUsername())
            .nombre(user.getNombre())
            .apellidoPat(user.getApellidoPat())
            .apellidoMat(user.getApellidoMat())
            .telefono(user.getTelefono())
            .direccion(user.getDireccion())
            .build();
            return userDTO;
        }
        return null;
    }
}
