package Banco; // Indica que esta interfaz pertenece al paquete "Banco"

// Definición de la interfaz MovimientosCuenta
public interface MovimientosCuenta {

    // Método abstracto para realizar depósitos. Las clases que implementen esta interfaz deben definir cómo se hace el depósito.
    void depositar(double monto);

    // Método abstracto para realizar retiros. Las clases que implementen esta interfaz deben definir cómo se hace el retiro.
    void retirar(double monto);
}

