package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class FlechasDisminucionVelocidad  extends  Sorpresa implements Serializable {
    private String iconRoute = "./images/flechaVelocidadMenos.png";
    private boolean sinUsar;

    public FlechasDisminucionVelocidad(SnOOPe snoope) {
        super(snoope);
        sinUsar = true;
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    /**
     * Disminuye la velocidad de la serpiente rival
     */
    @Override
    public void darPoderSopresa() {
        Timer timer = new Timer();
        int velocidadSnake = getSnakeQueComio().getVelocidad();
        TimerTask renovarVelocidad = new TimerTask() {
            @Override
            public void run() {
                getSnakeQueComio().setVelocidad(velocidadSnake);
                timer.cancel();
            }
        };
        timer.schedule(renovarVelocidad, 3500,3500);
        getSnakeQueComio().desacelerarVelocidad(100);
    }

    @Override
    public String toString() {
        return "SFD";
    }
}