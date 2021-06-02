package presentacion;

import aplicacion.Registro;

public class AccionPM implements Runnable{
    private final PanelSnakePM panelSnakePM;
    private final SnOOPeGUI gui;
    private int velocidad;

    /**
     * Metodo constructor de la clase Accion
     */
    public AccionPM(PanelSnakePM panelSnakePM, SnOOPeGUI gui){
        this.panelSnakePM = panelSnakePM;
        this.gui = gui;
        velocidad = 0;
    }

    @Override
    public void run() {
        while(panelSnakePM.getEstado()) {
            try {
                while(panelSnakePM.getPause()) {
                    Thread.sleep(1);
                }
                panelSnakePM.mover();
                panelSnakePM.repaint();
                gui.setPuntuacionP3(panelSnakePM.getPuntuacion());
                gui.refreshPuntuacionMaquina();
                velocidad = (panelSnakePM.getPuntuacion()/5)*5;
                Thread.sleep(panelSnakePM.veolicidadSerpiente() + velocidad);
            } catch (Exception e) {
                Registro.registre(e);
            }
        }
        gui.stopHilo1();
        gui.lose();
    }
}
