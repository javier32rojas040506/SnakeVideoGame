package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class BloqueTrampa extends Sorpresa implements Serializable {
    private String iconRoute = "./images/sorpresaBloqueTrampa.png";

    /**
     * Metodo Constructor de la clase  Sopresa
     * @param snoope
     */
    public BloqueTrampa(SnOOPe snoope) {
        super(snoope);
    }

    /*
    @Override
    public Image getICON() {
        return  new ImageIcon("./images/flechaVelocidadMenos.png").getImage();
    }*/

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    @Override
    public void darPoderSopresa() {
        Pared paredGenreda = new Pared(snoope);
        snoope.getBloquesTrampa().add(paredGenreda);
        paredGenreda.setPosicionAleatoriaTableroSnOOpe();
    }

    @Override
    public String toString() {
        return "SB";
    }
}
