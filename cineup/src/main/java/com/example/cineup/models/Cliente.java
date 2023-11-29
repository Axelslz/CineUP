package com.example.cineup.models;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class Cliente extends Circle {
    private String id;
    private String nombre;
    private SistemaReserva sistemaReserva;
    private Empleado empleado;
    private boolean enSistemaReserva = false;
    private boolean enEmpleado = false;

    public Cliente(String id, String nombre, SistemaReserva sistemaReserva, Empleado empleado) {
        super(15); // Cambié el radio del círculo
        this.id = id;
        this.nombre = nombre;
        this.sistemaReserva = sistemaReserva;
        this.empleado = empleado;
        setVisible(false);
        cargarImagen(); // Llamada al método para cargar la imagen
    }

    private void cargarImagen() {
        // Ruta relativa del recurso
        String rutaImagen = "/com/example/cineup/image/cliente.png";
        Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
        setFill(new ImagePattern(imagen));
    }

    public void aparecerEnSistemaReserva() {
        Platform.runLater(() -> {
            setTranslateX(sistemaReserva.getTranslateX());
            setTranslateY(sistemaReserva.getTranslateY() + 30);
            setVisible(true);
            enSistemaReserva = true;
        });
        esperarYDesaparecer(5, this::moverAEmpleado);
    }

    private void moverAEmpleado() {
        if (enSistemaReserva) {
            enSistemaReserva = false;
            enEmpleado = true;
            aparecerEnEmpleado();
        }
    }

    public void aparecerEnEmpleado() {
        Platform.runLater(() -> {
            setTranslateX(empleado.getTranslateX());
            setTranslateY(empleado.getTranslateY() + 30);
            setVisible(true);
        });
        esperarYDesaparecer(5, () -> sistemaReserva.agregarClienteALaCola(this));
    }

    public void aparecerEnAsiento(Asiento asiento) {
        Platform.runLater(() -> {
            setTranslateX(asiento.getCenterX());
            setTranslateY(asiento.getCenterY());
            setVisible(true);
        });

        PauseTransition wait = new PauseTransition(Duration.seconds(10)); // Tiempo en el asiento
        wait.setOnFinished(event -> Platform.runLater(() -> {
            setVisible(false);
            asiento.liberar();
            sistemaReserva.notificarLiberacionAsiento();
        }));
        wait.play();
    }

    private void esperarYDesaparecer(int segundos, Runnable siguienteAccion) {
        Platform.runLater(() -> {
            PauseTransition wait = new PauseTransition(Duration.seconds(segundos));
            wait.setOnFinished(event -> {
                setVisible(false);
                siguienteAccion.run();
            });
            wait.play();
        });
    }
}