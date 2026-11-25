package com.example.NutreAPP.validation;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.repositories.IUsuarioRepositorio;
import com.example.NutreAPP.repositories.IVideo;
import com.example.NutreAPP.repositories.IVisualizacion;

@Component 
public class VisualizacionValidacion {

    @Autowired 
    private  IVisualizacion repositorio;



// V1. La fecha de visualizacion es obligatoria.                     -> 400
//
// V2. La fecha NO puede ser futura (nadie ve un video manana).      -> 400
//     Pista: fecha.isAfter(LocalDate.now())
//
// V3. El campo completado es obligatorio (true o false, no null).   -> 400
//
// V4. El usuario y el video deben EXISTIR en la BD.                 -> 404
//     Pista: inyectar IUsuarioRepositorio e IVideoRepositorio y
//     usar existsById(id)

public void validarVisualizacion(LocalDate fecha){
 if (fecha == null) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo obligatorio");
 }
}

public void validarFecha(LocalDate fecha){
    if (!fecha.isAfter(LocalDate.now())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Debe de ser la fecha de actual, mas no la futura");
    }
}

public void validarCompletado (Boolean completado){
    if (completado == false) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Campo obligatorio");
    }
}

public void validarUsuarioYVideo(IUsuarioRepositorio usuario, IVideo video){
  if (usuario.existsById(null) && video.existsById(null)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No esta en la base de datos tu usuario y video");
  }
}
}
