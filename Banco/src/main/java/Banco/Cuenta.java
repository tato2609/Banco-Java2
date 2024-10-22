package Banco;

public abstract class Cuenta {
    protected double saldo;
    private static int contadorCuentas = 0; // Contador para las cuentas
    private final int numeroCuenta; // Número de cuenta único

    public Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
        contadorCuentas++; // Incrementar contador al crear una cuenta
        this.numeroCuenta = contadorCuentas; // Asignar número de cuenta
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta; // Método para obtener el número de cuenta
    }
}




