package presentacion;

import javax.swing.*;
import java.awt.*;

public class PanelTablero extends JPanel {
    private final int numCuadros;
    private final int tamTableroW;
    private final int tamTableroH;
    private final int resW;
    private final int resH;

    /**
     * Metodo constructor de la clase PanelTablero
     */
    public PanelTablero(int tamMax, int numCuadros, Color colorFondo){
        setLayout(null);
        setBackground(colorFondo);
        int tamMaxW = tamMax - 36;
        int tamMaxH = tamMax - 99;
        this.numCuadros = numCuadros;
        resW = tamMaxW%numCuadros;
        resH = tamMaxH%numCuadros;
        setBounds(10+(resW/2),30+(resH/2),tamMaxW-(resW),tamMaxH-(resH));
        tamTableroW =  this.getWidth() /numCuadros;
        tamTableroH = this.getHeight() / numCuadros;
    }

    /**
     * Metodo paint del JPanel
     */
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(Color.BLACK);
        for (int i = 0; i < numCuadros; i++) {
            for (int j = 0; j < numCuadros; j++) {
                if(numCuadros < 100){
                    pintor.drawRect(i*tamTableroW,j*tamTableroH,tamTableroW - 1,tamTableroH - 1);
                }
                else{
                    pintor.drawRect(i*tamTableroW,j*tamTableroH,tamTableroW,tamTableroH);
                }
            }
        }
    }
}
