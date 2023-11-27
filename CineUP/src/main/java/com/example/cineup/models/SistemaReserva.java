package com.example.cineup.models;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class SistemaReserva extends Rectangle {
    private final Asiento[] asientos;

    public SistemaReserva(double x, double y, int numAsientos) {
        super(30, 30, javafx.scene.paint.Color.RED);
        setTranslateX(x);
        setTranslateY(y);
        this.asientos = new Asiento[numAsientos];

        for (int i = 0; i < asientos.length; i++) {
            double offsetX = 110; // Valor que ajusta el desplazamiento hacia la derecha
            double asientoX = 100 + offsetX + (i % 7) * 60; // Coordenadas de los asientos
            double asientoY = 230 + (i / 7) * 60;
            asientos[i] = new Asiento(asientoX, asientoY);
        }
    }

    public synchronized Asiento reservarAsiento() {
        for (Asiento asiento : asientos) {
            if (!asiento.isReservado()) {
                asiento.reservar();
                return asiento;
            }
        }
        return null;
    }

    public void inicializarAsientos(Pane cinePane) {
        Rectangle fondoSala = new Rectangle(600, 300, Color.DARKSLATEGRAY);
        fondoSala.setTranslateX(100);
        fondoSala.setTranslateY(200);
        cinePane.getChildren().add(fondoSala);

        for (Asiento asiento : asientos) {
            cinePane.getChildren().add(asiento);
        }
    }
}
