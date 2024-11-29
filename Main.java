package com.banco;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Cuenta cuenta1 = new Cuenta(1001, 5000);
        Cuenta cuenta2 = new Cuenta(1002, 3000);
        Cuenta cuenta3 = new Cuenta(1003, 7000);
        Cuenta cuenta4 = new Cuenta(1004, 10000);
        Cuenta cuenta5 = new Cuenta(1005, 8000);

        Cliente cliente1 = new Cliente("Valentina Juan", "42054937", "2654", cuenta1);
        Cliente cliente2 = new Cliente("Veronica Pesaresi", "42054937", "8765", cuenta2);
        Cliente cliente3 = new Cliente("Carlos Garcia", "42054937", "9876", cuenta3);
        Cliente cliente4 = new Cliente("Lucia Fernandez", "42054937", "9876", cuenta4);
        Cliente cliente5 = new Cliente("Pedro Sanchez", "42054937", "9876", cuenta5);

        Administrador admin = new Administrador("Admin", "00000000", "42054937", 1);


        Usuario usuarioLogueado = null;

        while (usuarioLogueado == null) {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre de usuario:");
            String contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña:");

            usuarioLogueado = Usuario.login(nombre, contrasena);

            if (usuarioLogueado == null) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Inténtelo de nuevo.");
            }
        }

        JOptionPane.showMessageDialog(null, "¡Bienvenido, " + usuarioLogueado.getNombre() + "!");


        if (usuarioLogueado instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioLogueado;
            mostrarMenuCliente(cliente);
        } else if (usuarioLogueado instanceof Administrador) {
            mostrarMenuAdministrador((Administrador) usuarioLogueado);
        }
    }

    private static void mostrarMenuCliente(Cliente cliente) {
        String[] opciones = {"Depositar", "Retirar", "Transferir", "Ver movimientos", "Salir"};
        boolean salir = false;

        while (!salir) {
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                    "Menú de Cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, opciones, opciones[0]);

            switch (opcion) {
                case 0:
                    double montoDepositar = solicitarMonto("Ingrese el monto a depositar:");
                    if (cliente.depositar(montoDepositar)) {
                        JOptionPane.showMessageDialog(null, "Depósito realizado con éxito. Saldo actual: $" + cliente.getCuenta().getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar el depósito.");
                    }
                    break;
                case 1:
                    double montoRetirar = solicitarMonto("Ingrese el monto a retirar:");
                    if (cliente.retirar(montoRetirar)) {
                        JOptionPane.showMessageDialog(null, "Retiro realizado con éxito. Saldo actual: $" + cliente.getCuenta().getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al realizar el retiro. Saldo insuficiente.");
                    }
                    break;
                case 2:
                    String destinatarioNombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del destinatario:");
                    Cliente destinatario = buscarClientePorNombre(destinatarioNombre);

                    if (destinatario != null) {
                        double montoTransferir = solicitarMonto("Ingrese el monto a transferir:");
                        if (cliente.transferir(destinatario, montoTransferir)) {
                            JOptionPane.showMessageDialog(null, "Transferencia realizada con éxito a " + destinatario.getNombre());
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al realizar la transferencia. Saldo insuficiente.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un cliente con el nombre ingresado.");
                    }
                    break;
                case 3:
                    StringBuilder historial = new StringBuilder("Movimientos de la cuenta:\n");
                    for (Movimiento movimiento : cliente.getCuenta().getMovimientos()) {
                        historial.append(movimiento).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, historial.toString());
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    salir = true;
                    break;
            }
        }
    }

    private static void mostrarMenuAdministrador(Administrador admin) {
        JOptionPane.showMessageDialog(null, "Funcionalidades de administrador aún no implementadas.");
    }

    private static double solicitarMonto(String mensaje) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, mensaje);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }

    private static Cliente buscarClientePorNombre(String nombre) {
        for (Usuario usuario : Usuario.usuarios) {
            if (usuario instanceof Cliente && usuario.getNombre().equalsIgnoreCase(nombre)) {
                return (Cliente) usuario;
            }
        }
        return null;
    }
}
