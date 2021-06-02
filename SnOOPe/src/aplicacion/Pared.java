package aplicacion;

import java.awt.*;
import java.io.Serializable;

public class Pared  implements Serializable {
    //private Image ICON;
    private String iconRoute = "./images/bloqueTrampa.png";
    private int[] cord;
    protected SnOOPe snOOPeActual;


    /**
     * Metodo Constructor de la clase Pared
     */
    public  Pared( SnOOPe snOOPeActual){
        this.snOOPeActual = snOOPeActual;
    }

    /**
     * hace el set a la cordenandas de la pared
     * @param cord cordenandas para ubicar en el tablero
     */
    public void setCord(int[] cord) {
        this.cord = cord;
    }

    /**
     * Metod que hace el set de una coredenada aleatorias a la pared
     */
    public  void setPosicionAleatoriaTableroSnOOpe()
    {
        int cordx = (int) (Math.random() * snOOPeActual.getNumCuadrados());
        int cordy = (int) (Math.random() *  snOOPeActual.getNumCuadrados());
        int[] cord = {cordx, cordy};
        if(!snOOPeActual.tablero[cordx][cordy].equals(0))
        {
            setPosicionAleatoriaTableroSnOOpe();
        }
        setCord(cord);
        snOOPeActual.getTablero()[cordx][cordy] = this;
    }

    /**
     * hace el get a la cordenada de la pared
     * @return cordenanda de la fruta
     */
    public int[] getCord() {
        return cord;
    }

    /**
     * getICON retorna el icono de la pared especificada
     * @return
     */
    public String getIconRoute()
    {
        return iconRoute;
    }

    /**
     * metodo que retorna como se representa la estructura de la pared
     * @return la estructura de la pared
     */
    @Override
    public String toString()
    {
        return "P";
    }

}
