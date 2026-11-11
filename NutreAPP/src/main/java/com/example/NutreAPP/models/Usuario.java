package com.example.NutreAPP.models;

import java.util.UUID;

import com.example.NutreAPP.models.helpers.Departamento;
import com.example.NutreAPP.models.helpers.Rol;

public class Usuario {
private UUID id;
private String nombre;
private String correo;
private Rol rol;
private Departamento departamento;
private String contrasena_hash;
public Usuario() {
}
public Usuario(UUID id, String nombre, String correo, Rol rol, Departamento departamento, String contrasena_hash) {
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.rol = rol;
    this.departamento = departamento;
    this.contrasena_hash = contrasena_hash;
}
public UUID getId() {
    return id;
}
public void setId(UUID id) {
    this.id = id;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getCorreo() {
    return correo;
}
public void setCorreo(String correo) {
    this.correo = correo;
}
public Rol getRol() {
    return rol;
}
public void setRol(Rol rol) {
    this.rol = rol;
}
public Departamento getDepartamento() {
    return departamento;
}
public void setDepartamento(Departamento departamento) {
    this.departamento = departamento;
}
public String getContrasena_hash() {
    return contrasena_hash;
}
public void setContrasena_hash(String contrasena_hash) {
    this.contrasena_hash = contrasena_hash;
}


}
