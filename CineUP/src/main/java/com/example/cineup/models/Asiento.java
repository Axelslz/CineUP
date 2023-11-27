package com.example.cineup.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Asiento extends Circle {
    private boolean reservado;

    public Asiento(double x, double y) {
        super(15, Color.LIGHTGRAY);
        setCenterX(x);
        setCenterY(y);
        this.reservado = false;
    }

    public synchronized boolean isReservado() {
        return reservado;
    }

    public synchronized void reservar() {
        this.reservado = true;
        this.setFill(Color.RED);
    }

    public synchronized void liberar() {
        this.reservado = false;
        this.setFill(Color.LIGHTGRAY);
    }
}
