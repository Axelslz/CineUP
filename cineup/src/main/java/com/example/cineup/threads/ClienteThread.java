package com.example.cineup.threads;

import com.example.cineup.models.Cliente;
import com.example.cineup.models.SistemaReserva;
import javafx.application.Platform;

public class ClienteThread implements Runnable {
    private Cliente cliente;
    private SistemaReserva sistemaReserva;

    public ClienteThread(Cliente cliente, SistemaReserva sistemaReserva) {
        this.cliente = cliente;
        this.sistemaReserva = sistemaReserva;
    }

    @Override
    public void run() {
        cliente.aparecerEnSistemaReserva();
    }
}