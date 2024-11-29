package com.banco;
public class Administrador extends Usuario {
    private int nroAdmin;

    public Administrador(String nombre, String dni, String contrasena, int nroAdmin) {
        super(nombre, dni, contrasena);
        this.nroAdmin = nroAdmin;
    }

    public int getNroAdmin() {
        return nroAdmin;
    }
}
