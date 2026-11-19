package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.validation.UsuarioValidacion;

@Service 

public class UsuarioServicio {

    //Para programar los servicios necesito los repositorios
    // Inyeccion de dependencias
    @Autowired 
    private IUsuarioRepositorio repositorio;

    @Autowired 
    private UsuarioValidacion validador;

    //Metodo para guardar un usuario
    public Usuario guardar(Usuario datos){
    this.validador.validarCorreo(datos.getCorreo());
    this.validador.validarUsuarioNuevo(datos);
    this.validador.validarNombre(datos.getNombre());
    this.validador.validarContraseña(datos.getContrasena_hash());
     return this.repositorio.save(datos);
    }

    //Metodo para buscar por id
    public Usuario buscarPorId(UUID id){
        Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
            return usuarioBuscado.get();
        }
    }

    //Metodo para buscar todos
    public List<Usuario> buscarTodos(){
        return this.repositorio.findAll();
    }

    //Metodo para modificar
    public Usuario modificar (UUID id, Usuario datos){
         Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
           //1. precargar la info que existe actualmente en la base de datos
           Usuario usuarioEncontrado = usuarioBuscado.get();

           //2. espacio para modificar solo las propiedades permitidas
           this.validador.validarNombre(datos.getNombre());
           usuarioEncontrado.setNombre(datos.getNombre());
           usuarioEncontrado.setRol(datos.getRol());

           //3. espacio para volver a guardar el usuario que se acaba de modificar
           return this.repositorio.save(usuarioEncontrado);
        }
    }

    //Metodo para eliminar
    public boolean eliminar (UUID id){
          Optional<Usuario> usuarioBuscado = this.repositorio.findById(id);
        if (!usuarioBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
            this.repositorio.deleteById(id);
            return true;
        }
    }
    //Metodo para buscar por departamento y rol

    //Metodo para buscar por correo

    //Metodo para buscar por rol

    //Metodo para verificar Correo
    

}

    


