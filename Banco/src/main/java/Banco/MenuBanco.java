package Banco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuBanco extends JFrame {
    private List<CuentaAhorros> cuentasAhorros = new ArrayList<>();
    private List<CuentaCheques> cuentasCheques = new ArrayList<>();

    public MenuBanco() {
        setTitle("Menú Banco");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Seleccione la opción deseada:");
        label.setBounds(50, 20, 300, 30);
        add(label);

        String[] opciones = {
                "1. Crear cuenta de ahorros",
                "2. Crear cuenta de cheques",
                "3. Depositar en cuenta de ahorros",
                "4. Retirar de cuenta de ahorros",
                "5. Consultar saldo de cuenta de ahorros",
                "6. Depositar en cuenta de cheques",
                "7. Retirar de cuenta de cheques",
                "8. Consultar saldo de cuenta de cheques",
                "9. Listar saldos de cuentas",
                "10. Salir"
        };

        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setBounds(50, 60, 300, 30);
        add(comboBox);

        JButton button = new JButton("Seleccionar");
        button.setBounds(150, 100, 100, 30);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = comboBox.getSelectedIndex() + 1;
                switch (opcion) {
                    case 1:
                        crearCuentaAhorros();
                        break;
                    case 2:
                        crearCuentaCheques();
                        break;
                    case 3:
                        depositarAhorros();
                        break;
                    case 4:
                        retirarAhorros();
                        break;
                    case 5:
                        consultarSaldoAhorros();
                        break;
                    case 6:
                        depositarCheques();
                        break;
                    case 7:
                        retirarCheques();
                        break;
                    case 8:
                        consultarSaldoCheques();
                        break;
                    case 9:
                        listarSaldosCuentas();
                        break;
                    case 10:
                        System.exit(0);
                        break;
                }
            }
        });
    }

    private void crearCuentaAhorros() {
        double saldoInicial = obtenerValor("Ingrese el saldo inicial de la cuenta de ahorros:", true);
        double tasaInteres = obtenerValor("Ingrese la tasa de interés (%):", false);
        CuentaAhorros nuevaCuenta = new CuentaAhorros(saldoInicial, tasaInteres);
        cuentasAhorros.add(nuevaCuenta);
        JOptionPane.showMessageDialog(this, "Cuenta de ahorros creada exitosamente.");
    }

    private void crearCuentaCheques() {
        double saldoInicial = obtenerValor("Ingrese el saldo inicial de la cuenta de cheques:", true);
        double cuota = obtenerValor("Ingrese la cuota por transacción:", false);
        CuentaCheques nuevaCuenta = new CuentaCheques(saldoInicial, cuota);
        cuentasCheques.add(nuevaCuenta);
        JOptionPane.showMessageDialog(this, "Cuenta de cheques creada exitosamente.");
    }

    private void depositarAhorros() {
        if (cuentasAhorros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de ahorros creadas.");
            return;
        }
        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasAhorros.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasAhorros.get(i)).append("\n");
        }
        int selectedIndex = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta de ahorros para depositar:\n" + options));
        if (selectedIndex < 1 || selectedIndex > cuentasAhorros.size()) {
            JOptionPane.showMessageDialog(this, "Selección no válida.");
            return;
        }
        double monto = obtenerValor("Ingrese el monto a depositar en la cuenta de ahorros:", true);
        cuentasAhorros.get(selectedIndex - 1).depositar(monto);
        JOptionPane.showMessageDialog(this, "Depósito realizado en cuenta de ahorros.");
    }

    private void retirarAhorros() {
        if (cuentasAhorros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de ahorros creadas.");
            return;
        }
        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasAhorros.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasAhorros.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog("Seleccione la cuenta de ahorros para retirar:\n" + options);
        if (!esNumero(input)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
            return;
        }

        int selectedIndex = Integer.parseInt(input);
        if (selectedIndex < 1 || selectedIndex > cuentasAhorros.size()) {
            JOptionPane.showMessageDialog(this, "Selección no válida.");
            return;
        }

        double monto = obtenerValor("Ingrese el monto a retirar de la cuenta de ahorros:", true);

        // Validar que el monto no exceda el saldo disponible
        if (monto > cuentasAhorros.get(selectedIndex - 1).consultarSaldo()) {
            JOptionPane.showMessageDialog(this, "No se puede retirar más del saldo disponible.");
            return;
        }

        cuentasAhorros.get(selectedIndex - 1).retirar(monto);
        JOptionPane.showMessageDialog(this, "Retiro realizado de cuenta de ahorros.");
    }


    private void consultarSaldoAhorros() {
        if (cuentasAhorros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de ahorros creadas.");
            return;
        }
        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasAhorros.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasAhorros.get(i)).append("\n");
        }
        int selectedIndex = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta de ahorros para consultar el saldo:\n" + options));
        if (selectedIndex < 1 || selectedIndex > cuentasAhorros.size()) {
            JOptionPane.showMessageDialog(this, "Selección no válida.");
            return;
        }
        double saldo = cuentasAhorros.get(selectedIndex - 1).consultarSaldo();
        JOptionPane.showMessageDialog(this, "Saldo de cuenta de ahorros: " + saldo);
    }

    private void depositarCheques() {
        if (cuentasCheques.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de cheques creadas.");
            return;
        }
        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasCheques.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasCheques.get(i)).append("\n");
        }
        int selectedIndex = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta de cheques para depositar:\n" + options));
        if (selectedIndex < 1 || selectedIndex > cuentasCheques.size()) {
            JOptionPane.showMessageDialog(this, "Selección no válida.");
            return;
        }
        double monto = obtenerValor("Ingrese el monto a depositar en la cuenta de cheques:", true);
        cuentasCheques.get(selectedIndex - 1).depositar(monto);
        JOptionPane.showMessageDialog(this, "Depósito realizado en cuenta de cheques.");
    }

    private void retirarCheques() {
        if (cuentasCheques.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de cheques creadas.");
            return;
        }

        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasCheques.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasCheques.get(i)).append("\n");
        }

        // Asegúrate de que el usuario ingrese un número válido
        int selectedIndex = -1;
        while (selectedIndex < 1 || selectedIndex > cuentasCheques.size()) {
            try {
                String input = JOptionPane.showInputDialog("Seleccione la cuenta de cheques para retirar:\n" + options);
                selectedIndex = Integer.parseInt(input);
                if (selectedIndex < 1 || selectedIndex > cuentasCheques.size()) {
                    JOptionPane.showMessageDialog(this, "Selección no válida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
            }
        }

        double monto = obtenerValor("Ingrese el monto a retirar de la cuenta de cheques:", true);
        CuentaCheques cuentaSeleccionada = cuentasCheques.get(selectedIndex - 1);

        double cuota = cuentaSeleccionada.getCuota(); // Obtener la cuota por transacción
        double totalRetiro = monto + cuota; // Sumar el monto a retirar más la cuota

        if (cuentaSeleccionada.consultarSaldo() >= totalRetiro) {
            cuentaSeleccionada.retirar(monto); // Retirar el monto
            JOptionPane.showMessageDialog(this, "Retiro de " + monto + " realizado de cuenta de cheques. Comisión de " + cuota + " aplicada.");
        } else {
            JOptionPane.showMessageDialog(this, "El valor a retirar más la comisión excede el saldo de la cuenta.");
        }
    }


    private void consultarSaldoCheques() {
        if (cuentasCheques.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cuentas de cheques creadas.");
            return;
        }
        StringBuilder options = new StringBuilder();
        for (int i = 0; i < cuentasCheques.size(); i++) {
            options.append(i + 1).append(". ").append(cuentasCheques.get(i)).append("\n");
        }
        int selectedIndex = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la cuenta de cheques para consultar el saldo:\n" + options));
        if (selectedIndex < 1 || selectedIndex > cuentasCheques.size()) {
            JOptionPane.showMessageDialog(this, "Selección no válida.");
            return;
        }
        double saldo = cuentasCheques.get(selectedIndex - 1).consultarSaldo();
        JOptionPane.showMessageDialog(this, "Saldo de cuenta de cheques: " + saldo);
    }

    private void listarSaldosCuentas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cuentas de Ahorros:\n");
        for (CuentaAhorros cuenta : cuentasAhorros) {
            sb.append(cuenta.toString()).append("\n");
        }
        sb.append("Cuentas de Cheques:\n");
        for (CuentaCheques cuenta : cuentasCheques) {
            sb.append(cuenta.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Saldos de Cuentas", JOptionPane.INFORMATION_MESSAGE);
    }

    private double obtenerValor(String mensaje, boolean esMonto) {
        double valor = 0;
        boolean esValido = false;
        while (!esValido) {
            String input = JOptionPane.showInputDialog(mensaje);
            if (esMonto) {
                if (esNumero(input)) {
                    valor = Double.parseDouble(input);
                    if (valor >= 0) { // Para montos debe ser mayor o igual a 0
                        esValido = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "El monto debe ser un valor positivo.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un monto válido.");
                }
            } else {
                if (esNumero(input)) {
                    valor = Double.parseDouble(input);
                    esValido = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un valor numérico válido.");
                }
            }
        }
        return valor;
    }

    private boolean esNumero(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        new MenuBanco().setVisible(true);
    }
}
