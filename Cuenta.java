package com.banco;
import java.util.LinkedList;

public class Cuenta {
    private int nroCuenta;
    private double saldo;
    private LinkedList<Movimiento> movimientos;

    public Cuenta(int nroCuenta, double saldo) {
        this.nroCuenta = nroCuenta;
        this.saldo = saldo;
        this.movimientos = new LinkedList<>();
    }

    public int getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(int nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LinkedList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void registrarMovimiento(String detalle) {
        movimientos.add(new Movimiento(this, detalle));
    }

    @Override
    public String toString() {
        return "Cuenta [nroCuenta=" + nroCuenta + ", saldo=" + saldo + "]";
    }
}

