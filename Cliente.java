package com.banco;
public class Cliente extends Usuario {
    private Cuenta cuenta;

    public Cliente(String nombre, String dni, String contrasena, Cuenta cuenta) {
        super(nombre, dni, contrasena);
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public boolean depositar(double monto) {
        if (monto > 0) {
            cuenta.setSaldo(cuenta.getSaldo() + monto);
            cuenta.registrarMovimiento("Depósito de $" + monto);
            return true;
        }
        return false;
    }

    public boolean retirar(double monto) {
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            cuenta.setSaldo(cuenta.getSaldo() - monto);
            cuenta.registrarMovimiento("Retiro de $" + monto);
            return true;
        }
        return false;
    }

    public boolean transferir(Cliente destinatario, double monto) {
        if (monto > 0 && monto <= cuenta.getSaldo()) {
            this.retirar(monto);
            destinatario.depositar(monto);
            cuenta.registrarMovimiento("Transferencia de $" + monto + " a " + destinatario.getNombre());
            return true;
        }
        return false;
    }
}
