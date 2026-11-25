package com.example.NutreAPP.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.NutreAPP.models.Video;
import com.example.NutreAPP.repositories.IVideo;
import com.example.NutreAPP.validation.VideoValidacion;

@Service 
public class VideoServicio {

   @Autowired 
   private IVideo repositorio;

   @Autowired 
   private VideoValidacion validador;
 
   //Metodo para guardar
   public Video guardar (Video datos){
    this.validador.validarTitulo(datos.getTitulo());
    this.validador.validarUrl(datos.getUrl());
    this.validador.validarCategoria(datos.getCategoria());
    this.validador.validarDescripcion(datos.getDescripcion());
     return this.repositorio.save(datos);
   }
     

    //Metodo para buscar por id
      public Video buscarPorId(UUID id){
        Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
            return videoBuscado.get();
        }
    }

    //Metodo para buscar todos
    public List<Video> buscarTodos (){
      return this.repositorio.findAll();
    }

    //Metodo para modificar
    public Video modificar (UUID id, Video datos){
         Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
           //1. precargar la info que existe actualmente en la base de datos
           Video videoEncontrado = videoBuscado.get();

           //2. espacio para modificar solo las propiedades permitidas
           this.validador.validarTitulo(datos.getTitulo());
           videoEncontrado.setTitulo(datos.getTitulo());
           videoEncontrado.setUrl(datos.getUrl());

           //3. espacio para volver a guardar el usuario que se acaba de modificar
           return this.repositorio.save(videoEncontrado);
        }
    }

    //Metodo para eliminar

    public boolean eliminar (UUID id){
          Optional<Video> videoBuscado = this.repositorio.findById(id);
        if (!videoBuscado.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id no encontrado");
        } else {
            this.repositorio.deleteById(id);
            return true;
        }
    }

    //Metodo para buscar por categoria
    
    //Metodo para buscar por titulo

    //Metodo para buscar por descripcion
    
    //Metodo para buscar por url
    
    //Metodo para buscar por titulo y categoria
    
}
