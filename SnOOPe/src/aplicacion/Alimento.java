package aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public  abstract class Alimento implements Serializable {
    //private Image ICON;
    private String iconRoute;
    private int TIEMPO = 20;
    private int[] cord;
    protected SnOOPe snOOPeActual;
    protected Snake snakeQueComio;
    Color[] colores = {Color.BLACK, Color.CYAN, Color.BLUE, Color.ORANGE, Color.GREEN, Color.MAGENTA, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    ArrayList<Color> coloresAlimentos;


    /**
     * Metodo Constructor de la clase Alimentos
     */
    public  Alimento( SnOOPe snOOPeActual){
        this.snOOPeActual = snOOPeActual;
        coloresAlimentos = new ArrayList<>();
        coloresAlimentos.addAll(Arrays.asList(colores));
    }

    public Snake getSnakeQueComio() {
        return snakeQueComio;
    }

    public void setSnakeQueComio(Snake snakeQueComio) {
        this.snakeQueComio = snakeQueComio;
    }

    /**
     * hace el set a la cordenandas de la fruta
     * @param cord cordenandas para ubicar en el tablero
     */
    public void setCord(int[] cord) {
        this.cord = cord;
    }

    public void addColor(Color c){
        coloresAlimentos.add(c);
    }

    /**
     * hace el get a la cordenada de la fruta
     * @return cordenanda de la fruta
     */
    public int[] getCord() {
        return cord;
    }

    public void reColor(){};

    /**
     * getICON retorna el icono de la fruta especificada
     * @return
     */
    public abstract String getIconRoute();

    /**
     * metodo abstracto digerir (alimentos  cada alimento se digiere diferente)
     */
    public abstract void digerirAlimento();

    /**
     * metodo que retorna como se representa la estructura del alimento
     * @return la estructura de la fruta
     */
    @Override
    public  abstract String toString();
}