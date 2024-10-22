package Banco; // Especifica que esta clase pertenece al paquete "Banco"

import javax.swing.JOptionPane; // Importa la clase JOptionPane para mostrar cuadros de diálogo en la interfaz gráfica

// La clase CuentaAhorros extiende de la clase abstracta Cuenta e implementa la interfaz MovimientosCuenta
public class CuentaAhorros extends Cuenta implements MovimientosCuenta {

    private double tasaInteres; // Variable para almacenar la tasa de interés de la cuenta de ahorros

    // Constructor que inicializa el saldo inicial y la tasa de interés
    public CuentaAhorros(double saldoInicial, double tasaInteres) {
        super(saldoInicial); // Llama al constructor de la clase base (Cuenta) para establecer el saldo inicial
        this.tasaInteres = tasaInteres; // Inicializa la tasa de interés
    }

    // Método para calcular el interés basado en el saldo y la tasa de interés
    public double calcularInteres() {
        return (tasaInteres / 100) * saldo; // Calcula el interés multiplicando la tasa por el saldo
    }

    // Implementación del método depositar de la interfaz MovimientosCuenta
    @Override
    public void depositar(double monto) {
        saldo += monto; // Incrementa el saldo con el monto depositado
    }

    // Implementación del método retirar de la interfaz MovimientosCuenta
    @Override
    public void retirar(double monto) {
        // Verifica si el monto a retirar excede el saldo disponible
        if (monto > saldo) {
            JOptionPane.showMessageDialog(null, "El valor a retirar excede el saldo de la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            saldo -= monto; // Si no excede, resta el monto del saldo
        }
    }

    // Método para consultar el saldo, sumando el saldo actual y los intereses acumulados
    public double consultarSaldo() {
        return saldo + calcularInteres(); // Devuelve el saldo más los intereses
    }

    // Sobrescribe el método toString para representar la información de la cuenta como una cadena
    @Override
    public String toString() {
        return "CuentaAhorros{" +
                "numeroCuenta=" + getNumeroCuenta() + // Devuelve el número de cuenta
                ", saldo=" + saldo + // Devuelve el saldo
                '}'; // Cierra la cadena con la representación del objeto
    }
}
