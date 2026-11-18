package com.example.NutreAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Service 

public class UsuarioServicio {

    //Para programar los servicios necesito los repositorios
    // Inyeccion de dependencias
    @Autowired 
    private IUsuarioRepositorio repositorio;

    //Metodo para guardar un usuario
    public Usuario guardar(Usuario datos){
     return this.repositorio.save(datos);
    }


    //Metodo para buscar por id

    //Metodo para buscar todos

    //Metodo para modificar

    //Metodo para eliminar

    //Metodo para buscar por departamento y rol

    //Metodo para buscar por correo

    //Metodo para buscar por rol

    //Metodo para verificar Correo

}
