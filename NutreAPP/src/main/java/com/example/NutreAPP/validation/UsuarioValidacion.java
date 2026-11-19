package com.example.NutreAPP.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Usuario;
import com.example.NutreAPP.repositories.IUsuarioRepositorio;

@Component 
public class UsuarioValidacion {

    @Autowired
    private IUsuarioRepositorio repositorio;

    //Validaciones de  formatos (tipos de datos que no son,  configuraciones de un dato no esperados, campos obligatorios quer no llegan, cantidad de letras menor a la indicada )

    //1. Validar que un campo sea obligatorio
    public void validarTextoObligatorio(String valor, String campo){

      if (valor == null || valor.isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El campo "+campo+" es obligatorio");
      }  
    }

    //Validar la longitud de un nombre
    public void validarNombre (String nombre){
      if (nombre.trim().length()<3 || nombre.trim().length()>100) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre debe tener entre 3 y 100 caracteres");
      }
    }


   //Expresiones regulares para validar que un correo  si sea un correo
   //algo@algo.algo

   public void validarCorreo(String correo) {
    if (!correo.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no cumple el formato esperado");

    }

   }

   //Validar que  la contraseña tenga minimo 8 caracteres
   public void validarContraseña(String contraseña) {
      if (contraseña.length() < 8) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña debe de ser mas grande de 8 caracteres");
      }

   }

    //Validaciones del negocio (que cumpla con los parametros, si el correo ya existe para que volverlo a guardar,)
    
    //1. Validar el correo que un usuario se quiere registrar no exista en la base de datos
    public void validarUsuarioNuevo (Usuario datos){
      String correoEntregado = datos.getCorreo();
      if(this.repositorio.existsByCorreo(correoEntregado)){
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo entregado ya esta en la Base de Datos");
      }
    }


}
