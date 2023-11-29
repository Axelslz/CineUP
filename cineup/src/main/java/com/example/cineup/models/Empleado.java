package com.example.cineup.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Empleado extends Pane {

    private ImageView imageView;

    public Empleado(double x, double y) {
        try {
            Image imagenEmpleado = new Image("file:src/main/java/resources/com.example.cineup/image/empleado.png");
            imageView = new ImageView(imagenEmpleado);
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        setTranslateX(x);
        setTranslateY(y);
        getChildren().add(imageView);
    }

    public double getEmpleadoX() {
        return getTranslateX();
    }

    public double getEmpleadoY() {
        return getTranslateY();
    }

    public void setEmpleadoPosition(double x, double y) {
        setTranslateX(x);
        setTranslateY(y);
    }

    public void moverEmpleado(double deltaX, double deltaY) {
        setTranslateX(getTranslateX() + deltaX);
        setTranslateY(getTranslateY() + deltaY);
    }

    public ImageView getImageView() {
        return imageView;
    }
}


