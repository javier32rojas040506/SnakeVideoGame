package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.TimerTask;
import java.util.Timer;

public class Lupa extends Sorpresa implements Serializable {
    private String iconRoute = "./images/lupa.png";
    private Alimento alimento;

    /**
     * Metodo Constructor de la clase  Sopresa
     * @param snoope actual que maneja las sorpresas
     */
    public Lupa(SnOOPe snoope) {

        super(snoope);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    @Override
    public void darPoderSopresa() {
        getSnakeQueComio().setEfectoActivo(false);
        System.out.println("hizo el set");
    }

    @Override
    public String toString() {
        return "SL";
    }
}
