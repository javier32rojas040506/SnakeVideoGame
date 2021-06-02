package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class FrutaArcoiris extends Alimento implements Serializable {
    //private Image ICONO = new ImageIcon("./images/frutaArcoiris.png").getImage();
    private String iconRoute = "./images/frutaArcoiris.png";

    /**
     * Constructor Clase FrutaArcoiris
     */
    public FrutaArcoiris(SnOOPe snoope)
    {
        super(snoope);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    /**
     * Metodo que que hace la funcion Del Veneno
     */
    @Override
    public void digerirAlimento() {
        modificaTamano();
    }

    /**
     * aumenta el tamaño de la snake en 3 unidades
     */
    public void modificaTamano()
    {
        snakeQueComio.crecer();
        snakeQueComio.crecer();
        snakeQueComio.crecer();
    }

    /**
     * metodo que representa el tipo de fruta
     * @return
     */
    @Override
    public String toString() {
        return "AFA";
    }
}
