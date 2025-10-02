package com.example.wbm.implementation;

import com.example.wbm.model.dto.CDUsuarioDTO;
import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;
import com.example.wbm.model.mapStructure.IUsuarioMapper;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IUsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements IUsuarioServicio {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario leerUsuario() {
        return null;
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO personaDTO) {
        return null;
    }

    @Override
    public boolean UsuarioExistente(String correo) {


        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);

        if(usuarioOptional.isPresent()){
            return true;
        }

        return false;
    }



    @Transactional(readOnly = true)
    @Override
    public List<CDUsuarioDTO> leerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            return Collections.emptyList();
        }
        return usuarios.stream()
                .map(usuarioMapper::toCDDto)
                .toList();
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO crear(CDUsuarioDTO cdUsuarioDTO) {
        if (UsuarioExistente(cdUsuarioDTO.getCorreo())) {
            return new FormResponseSuccessDTO("El usuario que intenta ingresar ya existe", false);
        }


        Usuario usuario = usuarioMapper.toEntity(cdUsuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        return new FormResponseSuccessDTO("El usuario fue creado con éxito", true);
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO editarUsuario(CDUsuarioDTO cdUsuarioDTO) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(cdUsuarioDTO.getIdUsuario());

        if (usuarioOpt.isEmpty()) {
            return new FormResponseSuccessDTO("No se puede guardar un usuario que no existe. ERROR", false);
        }

        Usuario usuario = usuarioOpt.get();
        // Solo modificamos campos de Usuario
        usuario.setNombre(cdUsuarioDTO.getNombre());
        usuario.setCorreo(cdUsuarioDTO.getCorreo());
        usuario.setPassword(cdUsuarioDTO.getPassword());
        usuario.setEstado(cdUsuarioDTO.getEstado());

        usuarioRepository.save(usuario);

        return new FormResponseSuccessDTO("Se modificó el usuario de forma correcta", true);
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO cambiarEstadoUsuario(Integer idUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);

        if (usuarioOpt.isEmpty()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado del usuario. ERROR", false);
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setEstado(usuario.getEstado() == 1 ? 0 : 1);
        usuarioRepository.save(usuario);

        return new FormResponseSuccessDTO("Estado del usuario actualizado correctamente", true);
    }

    @Transactional
    public CDUsuarioDTO leerUsuarioPorId(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toCDDto(usuario);  // <-- aquí usas tu mapper
    }



}
