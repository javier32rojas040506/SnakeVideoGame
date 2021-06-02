package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Fruta extends Alimento implements Serializable {
    //private Image ICONO = new ImageIcon("./images/fruta.png").getImage();
    private Color color;
    private String iconRoute = "./images/fruta.png";

    /**
     * Constructor Clase Fruta
     */
    public Fruta(SnOOPe snoope)
    {
        super(snoope);
        int indexOfColor = (int) (Math.random() * coloresAlimentos.size());
        color =  coloresAlimentos.get(indexOfColor);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    /**
     * metodo return color de la fruta en especifico
     * @return color
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void reColor(){
        int indexOfColor = (int) (Math.random() * coloresAlimentos.size());
        color =  coloresAlimentos.get(indexOfColor);
    }

    /*
    @Override
    public Image getICON() {
        return ICONO;
    }*/


    /**
     * Metodo que que hace la funcion Del Veneno
     */
    @Override
    public void digerirAlimento() {
        modificaTamano();
    }

    /**
     * aumenta el tamaño de la snake en una unidad
     */
    public void modificaTamano()
    {
        snakeQueComio.crecer();
        if (snakeQueComio.getColorCuerpo().equals(color)) {
            snakeQueComio.crecer();
        }
    }

    /**
     * Metodo que retorna la estructura de la fruta especificada
     * @return
     */
    @Override
    public String toString() {
        return "AF";
    }

}