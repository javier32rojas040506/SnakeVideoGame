package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class EstrellaDeFuego extends Sorpresa implements Serializable {
    private String iconRoute = "./images/bolaFuego.png";

    /**
     * Metodo Constructor de la clase  Estrella de fuego
     * @param snoope
     */
    public EstrellaDeFuego(SnOOPe snoope) {
        super(snoope);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    @Override
    public void darPoderSopresa() {
        System.out.println("entro a disparar");
        ProyectilDeFuego proyectil = new ProyectilDeFuego(snoope, getSnakeQueComio());
        proyectil.setPuntoDeDisparo(getSnakeQueComio().getProximoMovimientoCabeza());
        proyectil.setCord(getSnakeQueComio().getProximoMovimientoCabeza());
        snoope.getProyectilesDeFuego().add(proyectil);
        System.out.println("entro a disparar");
        proyectil.disparar();
        proyectil.disparar();
    }

    @Override
    public String toString() {
        return "SE";
    }
}
