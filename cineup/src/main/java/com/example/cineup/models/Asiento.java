package com.example.cineup.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Asiento extends Circle {
    private boolean reservado;

    public Asiento(double x, double y) {
        super(10, Color.GRAY);
        setCenterX(x);
        setCenterY(y);
        reservado = false;
    }

    public synchronized void reservar() {
        reservado = true;
        this.setFill(Color.RED);
    }

    public synchronized void liberar() {
        reservado = false;
        this.setFill(Color.GRAY);
    }

    public synchronized boolean isReservado() {
        return reservado;
    }
}
