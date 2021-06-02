package aplicacion;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class FlehasAumentanVelocidad extends Sorpresa implements Serializable {
    private boolean sinUsar;
    private String iconRoute = "./images/flechaVelocidadMas.png";

    public FlehasAumentanVelocidad(SnOOPe snoope) {
        super(snoope);
    }

    @Override
    public String getIconRoute() {
        return iconRoute;
    }

    /**
     * Aumenta la velocidad de la serpiente que lo consume
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
        getSnakeQueComio().acelerarVelocidad(50);
    }

    @Override
    public String toString() {
        return "SFA";
    }
}