package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Veneno extends Alimento implements Serializable {
    //private Image ICONO = new ImageIcon("./images/veneno.png").getImage();
    private String iconRoute = "./images/veneno.png";

    /**
     * Constructor de la clase Veneno
     */
    public Veneno( SnOOPe snoope){
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
        suicidarse();
    }

    /**
     * mata a la serpiente
     */
    public void suicidarse()
    {
        snOOPeActual.setGameOver(true);
    }

    @Override
    public String toString() {
        return "AV";
    }



}
