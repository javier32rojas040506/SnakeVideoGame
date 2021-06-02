package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Dulce extends Alimento implements Serializable {
    //private Image ICONO = new ImageIcon("./images/dulce.png").getImage();
    private Color color;
    private String iconRoute = "./images/dulce.png";

    /**
     * Contructor clase Dulce
     */
    public Dulce(SnOOPe snoope)
    {
        super(snoope);
        int indexOfColor = (int) (Math.random() * coloresAlimentos.size());
        color =  coloresAlimentos.get(indexOfColor);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void reColor(){
        int indexOfColor = (int) (Math.random() * coloresAlimentos.size());
        color =  coloresAlimentos.get(indexOfColor);
    }

    /**
     * Metodo que que hace la funcion Del Veneno
     */
    @Override
    public void digerirAlimento()
    {
       modificaTamano();
    }

    /**
     * Disminuye en una unidad el tamaño de la sanke que lo consume
     * y si el contricante tiene el color del dulce disminuye en 2
     */
    public void modificaTamano()
    {
        snakeQueComio.decrecer();
        //para el multiplayer
        if(color.equals(snOOPeActual.getColores()[1]))
        {
            snakeQueComio.getSnakeEnemiga().decrecer();
        }
    }

    @Override
    public String toString() {
        return "AD";
    }
}