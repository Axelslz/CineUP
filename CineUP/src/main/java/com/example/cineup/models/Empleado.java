package com.example.cineup.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Empleado extends Polygon {

    public Empleado(double x, double y) {
        getPoints().addAll(0.0, 0.0, 20.0, 10.0, 0.0, 20.0);
        setTranslateX(x);
        setTranslateY(y);
        this.setFill(javafx.scene.paint.Color.GREEN);
    }


}

