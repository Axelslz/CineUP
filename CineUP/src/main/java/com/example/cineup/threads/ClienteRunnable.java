package com.example.cineup.threads;

import com.example.cineup.models.Cliente;
import javafx.application.Platform;

public class ClienteRunnable implements Runnable {
    private Cliente cliente;

    public ClienteRunnable(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            // Aquí empieza la lógica para aparecer en el sistema de reserva
            Platform.runLater(() -> cliente.aparecerEnSistemaReserva());

            // Esperamos que el cliente interactúe con el sistema de reserva
            // y con los empleados, esto se maneja dentro de la clase Cliente.
            // La lógica específica de pausas y transiciones debe estar en Cliente.

            // La clase Cliente ya debería manejar la lógica para aparecer en
            // el sistema de reserva, interactuar con los empleados y sentarse.
            // Esta clase Runnable simplemente inicia ese proceso.

            // Si necesitas realizar pausas o esperas, debes hacerlo dentro de Cliente,
            // probablemente usando PauseTransition o algún otro mecanismo de JavaFX
            // para no bloquear este hilo.

        } catch (Exception e) {
            // Manejo de excepciones si algo sale mal en el proceso
            e.printStackTrace();
        }
    }
}
