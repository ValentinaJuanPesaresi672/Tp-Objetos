package com.banco;
import java.time.LocalDateTime;

public class Movimiento {
    private LocalDateTime fechaHora;
    private Cuenta cuenta;
    private String detalle;

    public Movimiento(Cuenta cuenta, String detalle) {
        this.fechaHora = LocalDateTime.now();
        this.cuenta = cuenta;
        this.detalle = detalle;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public String getDetalle() {
        return detalle;
    }

    @Override
    public String toString() {
        return "[" + fechaHora + "] " + detalle;
    }
}

