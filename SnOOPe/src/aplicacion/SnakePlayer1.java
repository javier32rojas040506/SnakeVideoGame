package aplicacion;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;

public class SnakePlayer1 extends Snake implements Serializable {
    /**
     * Metodo cosntructor de la clase Snake
     * @param snOOPeActual
     */
    public SnakePlayer1(SnOOPe snOOPeActual) {
        super(snOOPeActual);
    }
    @Override
    /**
     * metodo que verifica que el movimiento de la serpiente sea correcto
     * @param cabeza la cabeza de la serpiente
     */
    public void verificaMov(int[] cabeza) {
        Object[][] tablero = SnOOPeActual.getTablero();
        Alimento[] alimentos = SnOOPeActual.getAlimentos();
        Timer[] timers = SnOOPeActual.getTimers();
        if(tablero[cabeza[0]][cabeza[1]] instanceof Alimento)
        {
            if((alimentos[0].getCord()[0] == cabeza[0] && alimentos[0].getCord()[1]==cabeza[1]))
            {
                tablero[cabeza[0]][cabeza[1]]=0;
                if(efectoActivo) {
                    alimentos[0].setSnakeQueComio(this);
                    alimentos[0].digerirAlimento();
                }
                efectoActivo=true;
                alimentos[0]= SnOOPeActual.generarAlimento();
                tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]]= alimentos[0];
                timers[0].cancel();
                timers[0] =  new Timer();
                TimerTask actualizarTareaN1 = new TimerTask() {
                    @Override
                    public void run() {
                        SnOOPeActual.quitarAlimento(alimentos[0]);
                        alimentos[0] = SnOOPeActual.generarAlimento();
                        tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]]= alimentos[0];
                    }
                };
                timers[0].schedule(actualizarTareaN1,7500,7500 );
            }
            else{
                tablero[cabeza[0]][cabeza[1]]=0;
                if(efectoActivo) {
                    alimentos[1].setSnakeQueComio(this);
                    alimentos[1].digerirAlimento();
                }
                efectoActivo=true;
                alimentos[1]=SnOOPeActual.generarAlimento();
                tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                timers[1].cancel();
                timers[1] = new Timer();
                TimerTask actualizarTareaN2 = new TimerTask() {
                    @Override
                    public void run() {
                        SnOOPeActual.quitarAlimento(alimentos[1]);
                        alimentos[1] = SnOOPeActual.generarAlimento();
                        tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                    }
                };
                timers[1].schedule(actualizarTareaN2, 7500,7500);
            }
        }

        if(tablero[cabeza[0]][cabeza[1]] instanceof Sorpresa)
        {
            tablero[cabeza[0]][cabeza[1]] = 0;
            SnOOPeActual.getSorpresa().setSnakeQueComio(this);
            SnOOPeActual.setSorpresasPendiente(SnOOPeActual.getSorpresa());
            SnOOPeActual.setSorpresa(null);
        }

        if(tablero[cabeza[0]][cabeza[1]] instanceof Pared)
        {
            tablero[cabeza[0]][cabeza[1]] = 0;
            SnOOPeActual.setGameOver(true);
        }


        //si la serpiente se golpea a si misma
        for(int i=0; i < this.getSnakeBody().size() - 1; i++)
        {
            if(this.getPartOfBody(i)[0]==cabeza[0] && this.getPartOfBody(i)[1]==cabeza[1])
            {
                SnOOPeActual.setGameOver(true);
                break;
            }
        }

        //si la serpiente golpea a su enemiga
        if(SnOOPeActual.isTwoPlayer()){
            for(int i=0; i < snakeEnemiga.getSnakeBody().size(); i++)
            {
                if(snakeEnemiga.getPartOfBody(i)[0]==cabeza[0] && snakeEnemiga.getPartOfBody(i)[1]==cabeza[1])
                {
                    SnOOPeActual.setGameOver(true);
                    break;
                }
            }
        }

        //parte inferior tablero
        if(cabeza[0] == SnOOPeActual.getNumCuadrados() || cabeza[1] == SnOOPeActual.getNumCuadrados())
        {
            //System.out.println("murio por limete inf 2");
            SnOOPeActual.setGameOver(true);
        }
        //printMatriz();

    }

    public void setSerpienteEnemigaDesdeSuInstaciaLocal() {
        if(SnOOPeActual.isTwoPlayer()) {
            if(SnOOPeActual.getSnakeP2()==null) {
                snakeEnemiga = SnOOPeActual.getSnakeP3();
            }
            else{
                snakeEnemiga = SnOOPeActual.getSnakeP2();
            }
        }
        else{
            snakeEnemiga = this;
        }
        System.out.println(snakeEnemiga);
        System.out.println(SnOOPeActual.isTwoPlayer());
    }
}




