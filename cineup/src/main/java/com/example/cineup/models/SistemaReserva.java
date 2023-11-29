package com.example.cineup.models;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SistemaReserva extends Rectangle {
    private final AtomicInteger contadorClientes = new AtomicInteger(0);
    private final Asiento[] asientos;
    private final Queue<Cliente> colaClientes = new LinkedList<>();

    public SistemaReserva(double x, double y, int numAsientos) {
        super(30, 30, Color.RED);
        setTranslateX(x);
        setTranslateY(y);
        this.asientos = new Asiento[numAsientos];

        for (int i = 0; i < asientos.length; i++) {
            double asientoX = 100 + (i % 7) * 60;
            double asientoY = 200 + (i / 7) * 60;
            asientos[i] = new Asiento(asientoX, asientoY);
        }
    }

    public synchronized Asiento reservarAsiento(Cliente cliente) {
        while (colaClientes.peek() != cliente) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        colaClientes.poll();
        notifyAll();

        if (contadorClientes.getAndIncrement() >= 100) {
            return null;
        }

        Random random = new Random();
        List<Asiento> asientosDisponibles = Arrays.stream(asientos)
                .filter(a -> !a.isReservado())
                .collect(Collectors.toList());

        if (!asientosDisponibles.isEmpty()) {
            Asiento asiento = asientosDisponibles.get(random.nextInt(asientosDisponibles.size()));
            asiento.reservar();
            return asiento;
        }
        return null;
    }

    public synchronized void agregarClienteALaCola(Cliente cliente) {
        colaClientes.add(cliente);
        while (colaClientes.peek() != cliente) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        colaClientes.poll();
        notifyAll();
    }

    public synchronized void notificarLiberacionAsiento() {
        notifyAll();
    }

    public void inicializarAsientos(Pane cinePane) {
        Rectangle fondoSala = new Rectangle(600, 300, Color.DARKSLATEGRAY);
        fondoSala.setTranslateX(70);
        fondoSala.setTranslateY(180);
        cinePane.getChildren().add(fondoSala);

        for (Asiento asiento : asientos) {
            cinePane.getChildren().add(asiento);
        }
    }
}

