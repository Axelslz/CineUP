package com.example.cineup.threads;

import com.example.cineup.models.Cliente;
import com.example.cineup.models.SistemaReserva;
import com.example.cineup.models.Asiento;
import javafx.application.Platform;

public class ReservaThread implements Runnable {
    private Cliente cliente;
    private SistemaReserva sistemaReserva;

    public ReservaThread(Cliente cliente, SistemaReserva sistemaReserva) {
        this.cliente = cliente;
        this.sistemaReserva = sistemaReserva;
    }

    @Override
    public void run() {
        Asiento asientoReservado = sistemaReserva.reservarAsiento(cliente);
        if (asientoReservado != null) {
            Platform.runLater(() -> cliente.aparecerEnAsiento(asientoReservado));
        }
    }
}
