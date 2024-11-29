package com.banco;
import java.util.LinkedList;

public abstract class Usuario {
    private String nombre;
    private String dni;
    private String contrasena;
    public static LinkedList<Usuario> usuarios = new LinkedList<>();

    public Usuario(String nombre, String dni, String contrasena) {
        this.nombre = nombre;
        this.dni = dni;
        this.contrasena = contrasena;
        usuarios.add(this);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getContrasena() {
        return contrasena;
    }

    public static Usuario login(String nombre, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }
}
