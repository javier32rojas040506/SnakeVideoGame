package aplicacion;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;

public class SnakePlayer2  extends Snake implements Serializable {
    /**
     * Metodo cosntructor de la clase Snake
     *
     * @param snOOPeActual
     */
    public SnakePlayer2(SnOOPe snOOPeActual) {
        super(snOOPeActual);
        snakeEnemiga = snOOPeActual.getSnakeP1();
    }
    @Override
    public void mover(){
        int indexOfCabezaSnake = getSnakeBody().size()-1;
        int addx = 0;
        int addy = 0;
        int [] cabeza = getSnakeBody().get(indexOfCabezaSnake);
        switch (direccion) {
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
        int[] nuevo = {cabeza[0] + addx, cabeza[1] + addy};
        int[] cord = getSnakeBody().get(0);
        try {
            verificaMov(nuevo);
            setProximoMovimientoCabeza(nuevo);
            removeBodyToSnake(0);
            addBodyToSnake(nuevo);
            SnOOPeActual.tablero[cord[0]][cord[1]] = 0;
            SnOOPeActual.ponerSnake2();
        }catch (Exception e) {
            Registro.registre(e);
            SnOOPeActual.setGameOver(true);
        }
    }

    @Override
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
                //System.out.println(alimentos[1]);
                alimentos[1] = SnOOPeActual.generarAlimento();
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

        //si la serpiente se golpea a su enemiga
        for(int i=0; i < snakeEnemiga.getSnakeBody().size(); i++)
        {
            if(snakeEnemiga.getPartOfBody(i)[0]==cabeza[0] && snakeEnemiga.getPartOfBody(i)[1]==cabeza[1])
            {
                System.out.println("murio en verifica mov");
                SnOOPeActual.setGameOver(true);
                break;
            }
        }

        //parte inferior tablero
        if(cabeza[0]==SnOOPeActual.getNumCuadrados() || cabeza[1]==SnOOPeActual.getNumCuadrados())
        {
            SnOOPeActual.setGameOver(true);
        }
        //printMatriz();

    }

    @Override
    public void setSerpienteEnemigaDesdeSuInstaciaLocal() {
        snakeEnemiga = SnOOPeActual.getSnakeP1();
    }

}
