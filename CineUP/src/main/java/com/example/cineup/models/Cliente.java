package com.example.cineup.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Cliente extends Circle {
    private String id;
    private String nombre;

    private Pane cinePane;

    private Circle visualRepresentation;
    private SistemaReserva sistemaReserva;
    private Empleado empleado;

    public Cliente(String id, String nombre, SistemaReserva sistemaReserva, Empleado empleado, Pane cinePane) {
        super(5, Color.BLUE);
        this.id = id;
        this.nombre = nombre;
        this.sistemaReserva = sistemaReserva;
        this.empleado = empleado;
        this.cinePane = cinePane; // Store the reference to cinePane

        setVisible(false);
    }

    public Circle getVisualRepresentation() {
        return visualRepresentation;
    }

    public void aparecerEnSistemaReserva() {
        double minX = 0;
        double minY = 0;
        double maxX = cinePane.getWidth() - 10;
        double maxY = cinePane.getHeight() - 10;

        setTranslateX(minX + Math.random() * (maxX - minX));
        setTranslateY(minY + Math.random() * (maxY - minY));

        setVisible(true);

        esperarYDesaparecer(3, this::aparecerEnEmpleado);

    }

    private void aparecerEnEmpleado() {
        setTranslateX(empleado.getTranslateX());
        setTranslateY(empleado.getTranslateY() + 30);  // Ajustar la posición y
        setVisible(true);

        esperarYDesaparecer(3, () -> {
            Asiento asiento = sistemaReserva.reservarAsiento();
            if (asiento != null) {
                aparecerEnAsiento(asiento);
            }
        });
    }


    private void aparecerEnAsiento(Asiento asiento) {
        setTranslateX(asiento.getCenterX());
        setTranslateY(asiento.getCenterY());
        setVisible(true);

        PauseTransition wait = new PauseTransition(Duration.seconds(30));
        wait.setOnFinished(event -> {
            setVisible(false);
            asiento.liberar();
        });
        wait.play();
    }

    private void esperarYDesaparecer(int segundos, Runnable siguienteAccion) {
        PauseTransition wait = new PauseTransition(Duration.seconds(segundos));
        wait.setOnFinished(event -> {
            setVisible(false);
            siguienteAccion.run();
        });
        wait.play();
    }
    private void updateVisualRepresentation() {
        visualRepresentation.setTranslateX(getTranslateX());
        visualRepresentation.setTranslateY(getTranslateY());
        visualRepresentation.setVisible(isVisible());
    }
}
