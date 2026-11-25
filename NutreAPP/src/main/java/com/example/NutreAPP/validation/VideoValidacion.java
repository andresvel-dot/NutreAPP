package com.example.NutreAPP.validation;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.models.helpers.Categoria;
import com.example.NutreAPP.repositories.IVideo;

@Component 
public class VideoValidacion {

@Autowired 
private IVideo repositorio;
//// V1. Titulo obligatorio y entre 5 y 120 caracteres.               -> 400
//
// V2. La URL es obligatoria y debe empezar por "http://" o
//     "https://".                                                    -> 400
//     Pista: url.startsWith("https://")
//
// V3. La categoria es obligatoria (no puede llegar null).           -> 400
//
// V4. La descripcion es opcional, pero si viene no puede
//     superar 500 caracteres.                                        -> 400
//
// V5. No pueden existir dos videos con la misma URL.                -> 409
//     Al CREAR: usar existsByUrl(url)
//     Al MODIFICAR: usar la consulta SQL 5 de abajo (igual que
//     contarCorreoEnOtroUsuario en Usuario)
public void validarTitulo(String titulo){
    if(titulo == null || titulo.trim().length() < 5 || titulo.trim().length() > 120){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Debe de tener el título entre 5 y 120 caractéres, debe ser obligatorio");
    } 
}

public void validarUrl(String url){
    if (!(url != null && url.isBlank() && url.trim().startsWith("https://") && url.trim().startsWith("http://"))){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Url no identificada");
    }
    
}

public void validarCategoria(Categoria categoria){
 if (categoria == null){
  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede dejar este espacio vacio, debe de seleccionar una");
 }
}


public void validarDescripcion (String descripcion){
    if (descripcion != null && descripcion.trim().length() > 500) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supera el limite de palabras, deben de ser menor a 500 y se debe de llenar obligatoriamente");
    }
}

public void crearUrl(String url) {
   if (this.repositorio.existsByUrl(url.trim())) {
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "la url ya existe");
  }
} 
public void modificarUrl(String url, UUID id){
    List<Video> buscarUrl = this.repositorio.findByUrl(url);
    for (Video video : buscarUrl) {
       if (!video.getId().equals(id)) { 
   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puede modificar la Url, ya existe en otro video");
  }
    }
    
}



}