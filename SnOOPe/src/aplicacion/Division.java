package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Division extends Sorpresa implements Serializable {
    private String iconRoute = "./images/division.png";

    /**
     * Metodo Constructor de la clase  Sopresa
     * @param snoope
     */
    public Division(SnOOPe snoope) {
        super(snoope);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    @Override
    public void darPoderSopresa() {
        List<int[]> subLista = getSnakeQueComio().getSnakeBody().subList(getSnakeQueComio().getSnakeBody().size()/2, getSnakeQueComio().getSnakeBody().size());
        ArrayList<int[]> nuevoArrayList = new ArrayList<>(subLista);
        getSnakeQueComio().setSnakeBody(nuevoArrayList);
    }

    @Override
    public String toString() {
        return "SD";
    }
}
