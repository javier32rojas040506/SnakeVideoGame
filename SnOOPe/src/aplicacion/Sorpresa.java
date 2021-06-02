package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public abstract class Sorpresa implements Serializable {
    private Image ICON;
    private boolean alcanzada;
    private int[] cord;
    protected SnOOPe snoope;
    private Snake snakeQueComio;

    /**
     * Metodo Constructor de la clase  Sopresa
     */
    public  Sorpresa(SnOOPe snoope){
        alcanzada = false;
        this.snoope = snoope;
    }

    /**
     * getter del sanke que comio la sorpresa
     * @return snake que comio
     */
    public Snake getSnakeQueComio() {
        return snakeQueComio;
    }

    /**
     * getter del sanke que comio la sorpresa
     * @param  snakeQueComio la sorpresa
     */
    public void setSnakeQueComio(Snake snakeQueComio) {
        this.snakeQueComio = snakeQueComio;
    }

    /**
     * hace el set a la cordenandas de la sorpresa
     * @param cord cordenandas para ubicar en el tablero
     */
    public void setCord(int[] cord) {
        this.cord = cord;
    }

    /**
     * hace el get a la cordenada de la sopresa
     * @return cordenanda de la sorpresa
     */
    public int[] getCord() {
        return cord;
    }

    /**
     * getICON retorna el icono de la sorpresa especificada
     * @return
     */
    /*
    public abstract Image getICON();*/

    public abstract String getIconRoute();

    /**
     * metodo abstracto darPoder (sopresa  cada sorpresa tiene un poder diferente)
     */
    public abstract void darPoderSopresa();

    /**
     * metodo que retorna como se representa la estructura de la sorpresa
     * @return la estructura de la sorpresa
     */
    @Override
    public  abstract String toString();
}
