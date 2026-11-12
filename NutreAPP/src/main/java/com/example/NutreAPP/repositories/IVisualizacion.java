package com.example.NutreAPP.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.NutreAPP.models.Visualizacion;
import java.time.LocalDate;


@Repository 
public interface IVisualizacion extends JpaRepository <Visualizacion, UUID> {

List<Visualizacion> findByFecha_visualizacion(LocalDate fecha_visualizacion);

List<Visualizacion> findByCompletadoTrue(Boolean completado);

List <Visualizacion> findByCompletadoFalse(Boolean completado);
}
