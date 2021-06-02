package presentacion;

import aplicacion.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;

public class PanelSnakeP1 extends PanelSnake implements Serializable {

    private Thread hilo;
    private AccionP1 accionP1;

    /**
     * Metodo constructor de la clase PanelSnakeP1
     */
    public PanelSnakeP1(Color cuerpo, Color cabeza, int tamMax, int numCuadros, SnOOPe snoope, SnOOPeGUI gui) {
        super(cuerpo, cabeza, tamMax, numCuadros, snoope, gui);
        direccionProx = 'D';
        igualarDir();
        snoope.setDireccionSnakeP1(direccion);
        this.snoope.setColores(cuerpo, 0);
        cuerpoSnake = snoope.getSnakeP1Body();

        accionP1 = new AccionP1(this, gui);
        hilo = new Thread(accionP1);
        hilo.start();
    }

    public void stopHilo(){
        hilo.interrupt();
    }

    /**
     * Metodo que permite mover la serpiente
     */
    @Override
    public void mover(){
        igualarDir();
        snoope.setDireccionSnakeP1(direccion);
        snoope.getSnakeP1().mover();
        sorpresa = snoope.getSorpresa();
        cuerpoSnake = snoope.getSnakeP1Body();
        alimentos = snoope.getAlimentos();
        bloquesTrampa = snoope.getBloquesTrampa();
        proyectilesDeFuego = snoope.getProyectilesDeFuego();
        gui.actualizarSorpresaSolo(snoope.haySorpresa());
        gui.actualizarSorpresaMulti(snoope.haySorpresa());
        for(ProyectilDeFuego proyectilDeFuego:proyectilesDeFuego)
        {
            proyectilDeFuego.moverProyectil();
        }
        if(snoope.getGameOver()){
            estado = false;
        }
    }

    @Override
    public int veolicidadSerpiente(){
        return snoope.getSnakeP1().getVelocidad();
    }

    /**
     * Metodo paint del JPanel
     */
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        try{
            pintor.drawImage(new ImageIcon(sorpresa.getIconRoute()).getImage(), sorpresa.getCord()[1] * tamTableroW, sorpresa.getCord()[0] * tamTableroH,null);
        } catch (Exception e){}

        for(Alimento comida: alimentos){
            if(comida!=null){
                int[] coordA = comida.getCord();
                if(comida instanceof Fruta){
                    pintor.drawImage(new ImageIcon(comida.getIconRoute()).getImage(), coordA[1] * tamTableroW, coordA[0] * tamTableroH, ((Fruta) comida).getColor(),null);
                }
                else if(comida instanceof Dulce){
                    pintor.drawImage(new ImageIcon(comida.getIconRoute()).getImage(), coordA[1] * tamTableroW, coordA[0] * tamTableroH, ((Dulce)comida).getColor(),null);
                }
                else if(comida instanceof FrutaArcoiris){
                    pintor.drawImage(new ImageIcon(comida.getIconRoute()).getImage(), coordA[1] * tamTableroW, coordA[0] * tamTableroH,null);
                }
                else if(comida instanceof Veneno){
                    pintor.drawImage(new ImageIcon(comida.getIconRoute()).getImage(), coordA[1] * tamTableroW, coordA[0] * tamTableroH,null);
                }

            }
        }

        for (int[] tuple: cuerpoSnake) {
            if(Arrays.equals(tuple, cuerpoSnake.get(cuerpoSnake.size()-1))){
                pintor.setColor(snoope.getSnakeP1().getColorCabeza());
            }
            else{
                pintor.setColor(snoope.getSnakeP1().getColorCuerpo());
            }
            pintor.fillRect((tuple[1]*tamTableroW)+1,(tuple[0]*tamTableroH)+1,tamTableroW - 2,tamTableroH - 2);
        }

        if(proyectilesDeFuego!=null)
        {
            if(proyectilesDeFuego.size()>0)
            {
                for (ProyectilDeFuego proyectilDeFuego : proyectilesDeFuego) {
                    pintor.drawImage(new ImageIcon(proyectilDeFuego.getIconRoute()).getImage(),proyectilDeFuego.getCord()[1] * tamTableroW, proyectilDeFuego.getCord()[0] * tamTableroH,null);
                }
            }
        }

        if(bloquesTrampa!=null)
        {
            if(bloquesTrampa.size()>0)
            {
                for (Pared pared : bloquesTrampa) {
                    pintor.drawImage(new ImageIcon(pared.getIconRoute()).getImage(),pared.getCord()[1] * tamTableroW, pared.getCord()[0] * tamTableroH,null);
                }
            }
        }
    }
}
