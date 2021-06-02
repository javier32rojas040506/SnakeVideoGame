package aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProyectilDeFuego implements Serializable {
    //private Image ICON;
    private String iconRoute = "./images/proyectilFuego.png";
    private int[] cord;
    private int[] puntoDeDisparo;
    protected SnOOPe snOOPeActual;
    private Snake snakeQueComio;
    protected char direccionProyectil;
    private boolean exploto;

    /**
     * Metodo Constructor de la clase ProyectilDeFuego
     */
    public  ProyectilDeFuego(SnOOPe snOOPeActual, Snake snakeQueComio){
        this.snOOPeActual = snOOPeActual;
        this.snakeQueComio = snakeQueComio;
        exploto = false;
    }

    /**
     * getter del punto inicial de disparo
     * @return punto inicial de disparo
     */
    public int[] getPuntoDeDisparo() {
        return puntoDeDisparo;
    }

    /**
     * setter del punto inicial de disparo
     * @param puntoDeDisparo inicial
     */
    public void setPuntoDeDisparo(int[] puntoDeDisparo) {
        this.puntoDeDisparo = puntoDeDisparo;
    }

    /**
     * hace el set a la cordenandas del proyectil de fuego
     * @param cord cordenandas para ubicar en el tablero
     */
    public void setCord(int[] cord) {
        this.cord = cord;
    }

    /**
     * Metodo que hace el set de una coredenada aleatorias al Proyectil
     */
    public  void setPosicionAleatoriaTableroSnOOpe()
    {
        int cordx = (int) (Math.random() * snOOPeActual.getNumCuadrados());
        int cordy = (int) (Math.random() *  snOOPeActual.getNumCuadrados());
        int[] cord = {cordx, cordy};
        setCord(cord);
        snOOPeActual.getTablero()[cordx][cordy] = this;
    }

    public void disparar()
    {

        direccionProyectil =  snakeQueComio.getDireccion();
        int addx = 0;
        int addy = 0;
        switch (direccionProyectil) {
            case 'D'://ES DOWN
                addx++;
                break;
            case 'U'://ES UP
                addx --;
                break;
            case 'R'://ES RIGHT
                addy ++;
                break;
            case 'L'://ES LEFT
                addy --;
        }
        int[] posicionProyectil = {getCord()[0] + addx, getCord()[1] + addy};
        try {
            verificaMovPoyectil(posicionProyectil);
            snOOPeActual.getTablero()[getCord()[0]][getCord()[1]] = 0;
            snOOPeActual.getTablero()[posicionProyectil[0]][posicionProyectil[1]] = this;
            setCord(posicionProyectil);
        }catch (Exception e) {
            Registro.registre(e);
        }
    }


    public void moverProyectil()
    {
        int addx = 0;
        int addy = 0;
        exploto = false;
        switch (direccionProyectil) {
            case 'D'://ES DOWN
                addx++;
                break;
            case 'U'://ES UP
                addx --;
                break;
            case 'R'://ES RIGHT
                addy ++;
                break;
            case 'L'://ES LEFT
                addy --;
        }
        int[] posicionProyectil = {getCord()[0] + addx, getCord()[1] + addy};
        try {
            verificaMovPoyectil(posicionProyectil);
            snOOPeActual.getTablero()[getCord()[0]][getCord()[1]] = 0;
            snOOPeActual.getTablero()[posicionProyectil[0]][posicionProyectil[1]] = this;
            setCord(posicionProyectil);
            if(exploto)
            {
                snOOPeActual.getTablero()[getCord()[0]][getCord()[1]] = 0;
            }
        }catch (Exception e) {
            Registro.registre(e);
        }
    }




    /**
     * hace el get a la cordenada del proyectil
     * @return cordenanda de la fruta
     */
    public int[] getCord() {
        return cord;
    }

    /**
     * getICON retorna el icono del proyectil especificado
     * @return
     */
    public String getIconRoute()
    {
        return iconRoute;
    }

    public  void verificaMovPoyectil(int[] posicionProyectil) {
        Object[][] tablero = snOOPeActual.getTablero();
        Alimento[] alimentos = snOOPeActual.getAlimentos();
        Timer[] timers = snOOPeActual.getTimers();
        //si choca con un alimento
        if (tablero[posicionProyectil[0]][posicionProyectil[1]] instanceof Alimento) {
            if ((alimentos[0].getCord()[0] == posicionProyectil[0] && alimentos[0].getCord()[1] == posicionProyectil[1])) {
                exploto = true;
                tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                snOOPeActual.getProyectilesDeFuego().remove(this);
                alimentos[0] = snOOPeActual.generarAlimento();
                tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]] = alimentos[0];
                timers[0].cancel();
                timers[0] = new Timer();
                TimerTask actualizarTareaN1 = new TimerTask() {
                    @Override
                    public void run() {
                        snOOPeActual.quitarAlimento(alimentos[0]);
                        alimentos[0] = snOOPeActual.generarAlimento();
                        tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]] = alimentos[0];
                    }
                };
                timers[0].schedule(actualizarTareaN1, 7500, 7500);
            } else {
                exploto = true;
                tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                snOOPeActual.getProyectilesDeFuego().remove(this);
                //System.out.println(alimentos[1]);
                alimentos[1] = snOOPeActual.generarAlimento();
                tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                timers[1].cancel();
                timers[1] = new Timer();
                TimerTask actualizarTareaN2 = new TimerTask() {
                    @Override
                    public void run() {
                        snOOPeActual.quitarAlimento(alimentos[1]);
                        alimentos[1] = snOOPeActual.generarAlimento();
                        tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                    }
                };
                timers[1].schedule(actualizarTareaN2, 7500, 7500);

            }
        }

        if (tablero[posicionProyectil[0]][posicionProyectil[1]] instanceof Sorpresa) {
            tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
            exploto = true;
            snOOPeActual.getProyectilesDeFuego().remove(this);
            snOOPeActual.setSorpresasPendiente(null);
            snOOPeActual.setSorpresa(null);
        }

        if (tablero[posicionProyectil[0]][posicionProyectil[1]] instanceof Pared) {
            snOOPeActual.getBloquesTrampa().remove(tablero[posicionProyectil[0]][posicionProyectil[1]]);
            tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
            exploto = true;
            snOOPeActual.getProyectilesDeFuego().remove(this);
            snakeQueComio.crecer();
            snakeQueComio.crecer();
            snakeQueComio.crecer();
            snakeQueComio.crecer();
            snakeQueComio.crecer();
        }


        for (int i = 0; i < snakeQueComio.getSnakeBody().size(); i++) {
            //System.out.println(snakeP1.get(i)[0]+"=="+cabeza[0]+"&&"+snakeP1.get(i)[1]+"=="+cabeza[1]);
            if (snakeQueComio.getPartOfBody(i)[0] == posicionProyectil[0] && snakeQueComio.getPartOfBody(i)[1] == posicionProyectil[1]) {
                tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                exploto = true;
                snOOPeActual.getProyectilesDeFuego().remove(this);
                if (i >= 1) {
                    List<int[]> subLista = snakeQueComio.getSnakeBody().subList(i, snakeQueComio.getSnakeBody().size());
                    ArrayList<int[]> nuevoArrayList = new ArrayList<>(subLista);
                    snakeQueComio.setSnakeBody(nuevoArrayList);
                } else {
                    snOOPeActual.setGameOver(true);
                }
            }
        }


        if(snOOPeActual.isTwoPlayer()){
            //si la serpiente se golpea a la otra
            for(int i=0; i < snakeQueComio.getSnakeEnemiga().getSnakeBody().size(); i++)
            {
                //System.out.println(snakeP1.get(i)[0]+"=="+cabeza[0]+"&&"+snakeP1.get(i)[1]+"=="+cabeza[1]);
                if(snakeQueComio.getSnakeEnemiga().getPartOfBody(i)[0]==posicionProyectil[0] && snakeQueComio.getSnakeEnemiga().getPartOfBody(i)[1]==posicionProyectil[1])
                {
                    tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                    exploto = true;
                    snOOPeActual.getProyectilesDeFuego().remove(this);
                    if(i > 1){
                        List<int[]> subLista = snakeQueComio.getSnakeEnemiga().getSnakeBody().subList(i, snakeQueComio.getSnakeEnemiga().getSnakeBody().size());
                        ArrayList<int[]> nuevoArrayList = new ArrayList<>(subLista);
                        snakeQueComio.getSnakeEnemiga().setSnakeBody(nuevoArrayList);
                    }
                    else {
                        snOOPeActual.setGameOver(true);
                    }
                }
            }
        }


        //parte inferior tablero
        if (posicionProyectil[0] == snOOPeActual.getNumCuadrados()-1 || posicionProyectil[1] == snOOPeActual.getNumCuadrados()-1
                || posicionProyectil[0] == 0 || posicionProyectil[1] == 0) {
            if (puntoDeDisparo[0]==snOOPeActual.getNumCuadrados()-1||puntoDeDisparo[1]==snOOPeActual.getNumCuadrados()-1||
            puntoDeDisparo[0]==0||puntoDeDisparo[1]==0)
            {
                //esquinas
                if (posicionProyectil[0] == snOOPeActual.getNumCuadrados()-1 && posicionProyectil[1] == snOOPeActual.getNumCuadrados()-1
                        || posicionProyectil[0] == 0 && posicionProyectil[1] == 0)
                {
                    snOOPeActual.getProyectilesDeFuego().remove(this);
                    tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                    exploto = true;
                }
                if (posicionProyectil[0] == 0 && posicionProyectil[1] == snOOPeActual.getNumCuadrados()-1
                        || posicionProyectil[0] == snOOPeActual.getNumCuadrados()-1&& posicionProyectil[1] == 0)
                {
                    snOOPeActual.getProyectilesDeFuego().remove(this);
                    tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                    exploto = true;
                }
            }
            else {
                snOOPeActual.getProyectilesDeFuego().remove(this);
                tablero[posicionProyectil[0]][posicionProyectil[1]] = 0;
                exploto = true;
            }
        }
        //printMatriz();
    }

    /**
     * metodo que retorna como se representa la estructura del proyectil
     * @return la estructura del proyectil
     */
    @Override
    public String toString()
    {
        return "PF";
    }
}
