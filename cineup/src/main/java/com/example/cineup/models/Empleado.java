package com.example.cineup.models;

import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;

public class Empleado extends Polygon {

    public Empleado(double x, double y) {
        getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        setTranslateX(x);
        setTranslateY(y);
        this.setFill(Color.GREEN);
    }
}

