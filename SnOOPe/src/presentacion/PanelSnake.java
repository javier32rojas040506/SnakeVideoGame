package presentacion;

import aplicacion.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class PanelSnake extends JPanel implements Serializable{
    protected final int tamTableroW;
    protected final int tamTableroH;
    protected final int resW;
    protected final int resH;
    protected final Color colorSnake;
    protected ArrayList<int[]> cuerpoSnake;
    protected Alimento[] alimentos;
    protected ArrayList<Pared> bloquesTrampa;
    protected ArrayList<ProyectilDeFuego> proyectilesDeFuego;
    protected SnOOPe snoope;
    protected char direccion;
    protected char direccionProx;
    protected Color colorCabeza;
    protected SnOOPeGUI gui;
    protected Boolean estado;
    protected Boolean pause;
    protected Sorpresa sorpresa;

    public PanelSnake(Color cuerpo, Color cabeza, int tamMax, int numCuadros, SnOOPe snoope, SnOOPeGUI gui) {
        this.gui = gui;
        this.snoope = snoope;
        setLayout(null);
        setOpaque(false);
        colorSnake = cuerpo;
        colorCabeza = cabeza;

        alimentos = snoope.getAlimentos();
        int tamMaxW = tamMax - 36;
        int tamMaxH = tamMax - 99;
        resW = tamMaxW%numCuadros;
        resH = tamMaxH%numCuadros;
        setBounds(10+(resW/2),30+(resH/2),tamMaxW-(resW),tamMaxH-(resH));
        tamTableroW =  this.getWidth() /numCuadros;
        tamTableroH = this.getHeight() / numCuadros;

        estado = true;
        pause = false;
        repaint();
    }

    /**
     * Metodo getter del estado del juego
     * @return si esta vivo o no
     */
    public Boolean getEstado() {
        return !snoope.getGameOver();
    }

    /**
     * Metodo getter del snoope del juego
     * @return si esta vivo o no
     */
    public SnOOPe getSnoope() {
        return snoope;
    }

    /**
     * Metodo setter del estado de la pausa
     */
    public void setPause(Boolean pause) {
        this.pause = pause;
    }

    /**
     * Metodo getter del estado del juego
     * @return si esta pausado o no
     */
    public Boolean getPause() {
        return pause;
    }

    /**
     * Metodo getter de los puntos del jugador
     * @return la cantidad de putnos
     */
    public int getPuntuacion(){return cuerpoSnake.size();}

    /**
     * Metodo iguala la direcion siguiente y la actual del movimiento
     */
    public void igualarDir(){
        direccion = direccionProx;
    }

    /**
     * Metodo que permite cambiar la direccion del movimiento
     */
    public void cambiarDireccion(char dir){
        this.direccionProx = dir;
    }

    /**
     * Setter del snoope
     */
    public void setSnoope(SnOOPe snoope) {
        this.snoope = snoope;
    }

    public abstract void mover();

    public abstract int veolicidadSerpiente();
}
