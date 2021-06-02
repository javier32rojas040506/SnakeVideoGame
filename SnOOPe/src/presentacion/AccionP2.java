package presentacion;

import aplicacion.Registro;

public class AccionP2 implements Runnable{
    private final PanelSnakeP2 panelSnakeP2;
    private final SnOOPeGUI gui;
    private int velocidad;

    /**
     * Metodo constructor de la clase Accion
     */
    public AccionP2(PanelSnakeP2 panelSnakeP2, SnOOPeGUI gui){
        this.panelSnakeP2 = panelSnakeP2;
        this.gui = gui;
        velocidad = 0;
    }

    @Override
    public void run() {
        while(panelSnakeP2.getEstado()) {
            try {
                while(panelSnakeP2.getPause()) {
                    Thread.sleep(1);
                }
                panelSnakeP2.mover();
                panelSnakeP2.repaint();
                gui.setPuntuacionP2(panelSnakeP2.getPuntuacion());
                gui.refreshPuntuacionMulti();
                velocidad = (panelSnakeP2.getPuntuacion()/5)*5;
                Thread.sleep(panelSnakeP2.veolicidadSerpiente() + velocidad);
            } catch (Exception e) {
                Registro.registre(e);
            }
        }
        gui.stopHilo1();
        gui.lose();
    }
}
