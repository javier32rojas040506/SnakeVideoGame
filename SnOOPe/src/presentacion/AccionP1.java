package presentacion;

import aplicacion.Registro;

public class AccionP1 implements Runnable{
    private final PanelSnakeP1 panelSnakeP1;
    private final SnOOPeGUI gui;
    private int velocidad;

    /**
     * Metodo constructor de la clase Accion
     */
    public AccionP1(PanelSnakeP1 panelSnakeP1, SnOOPeGUI gui){
        this.panelSnakeP1 = panelSnakeP1;
        this.gui = gui;
        velocidad = 0;
    }

    @Override
    public void run() {
        while(panelSnakeP1.getEstado()) {
            try {
                while(panelSnakeP1.getPause()) {
                    Thread.sleep(1);
                }
                panelSnakeP1.mover();
                panelSnakeP1.repaint();
                gui.setPuntuacion(panelSnakeP1.getPuntuacion());
                gui.setPuntuacionP1(panelSnakeP1.getPuntuacion());
                gui.refreshPuntuacion();
                gui.refreshPuntuacionMulti();
                gui.refreshPuntuacionMaquina();
                velocidad = (panelSnakeP1.getPuntuacion()/5)*5;
                Thread.sleep(panelSnakeP1.veolicidadSerpiente() + velocidad);
            } catch (Exception e) {
                Registro.registre(e);
            }
        }
        gui.stopHilo3();
        gui.stopHilo2();
        gui.lose();
    }
}
