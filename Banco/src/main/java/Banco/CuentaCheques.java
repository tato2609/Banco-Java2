package Banco; // Define el paquete al que pertenece la clase

import javax.swing.JOptionPane; // Importa la clase JOptionPane para mostrar cuadros de diálogo de la interfaz gráfica

// La clase CuentaCheques extiende de la clase Cuenta e implementa la interfaz MovimientosCuenta
public class CuentaCheques extends Cuenta implements MovimientosCuenta {

    private double cuotaPorTransaccion; // Variable que almacena la cuota cobrada por cada transacción
    private static int contadorCheques = 0; // Variable estática que lleva la cuenta del número total de cheques creados
    private final int numeroCheque; // Variable que almacena el número único del cheque

    // Constructor que recibe el saldo inicial y la cuota por transacción
    public CuentaCheques(double saldoInicial, double cuotaPorTransaccion) {
        super(saldoInicial); // Llama al constructor de la clase base (Cuenta) para establecer el saldo inicial
        this.cuotaPorTransaccion = cuotaPorTransaccion; // Inicializa la cuota por transacción
        contadorCheques++; // Incrementa el contador de cheques
        this.numeroCheque = contadorCheques; // Asigna el número de cheque basado en el contador
    }

    // Implementación del método depositar de la interfaz MovimientosCuenta
    @Override
    public void depositar(double monto) {
        // Al depositar, se suma el monto ingresado al saldo, pero se resta la cuota por transacción
        saldo += (monto - cuotaPorTransaccion);
    }

    // Implementación del método retirar de la interfaz MovimientosCuenta
    @Override
    public void retirar(double monto) {
        // Si el monto a retirar más la cuota excede el saldo disponible, se muestra un mensaje de error
        if (monto + cuotaPorTransaccion > saldo) {
            JOptionPane.showMessageDialog(null, "El valor a retirar más la comisión excede el saldo de la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si el saldo es suficiente, se resta el monto a retirar más la cuota de transacción
            saldo -= (monto + cuotaPorTransaccion);
        }
    }

    // Método para consultar el saldo de la cuenta
    public double consultarSaldo() {
        return saldo; // Retorna el saldo actual de la cuenta
    }

    // Método que devuelve la cuota por transacción
    public double getCuota() {
        return cuotaPorTransaccion; // Devuelve la cuota por transacción
    }

    // Sobrescribe el método toString para devolver una representación legible de la cuenta de cheques
    @Override
    public String toString() {
        return "CuentaCheques{" +
                "numeroCuenta=" + getNumeroCuenta() + // Devuelve el número de cuenta
                ", numeroCheque=" + numeroCheque + // Devuelve el número único del cheque
                ", saldo=" + saldo + // Devuelve el saldo actual
                '}'; // Cierra la cadena con la representación del objeto
    }
}
