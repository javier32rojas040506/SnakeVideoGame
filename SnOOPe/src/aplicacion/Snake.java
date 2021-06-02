package aplicacion;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Snake implements Serializable {
    private ArrayList<int[]> snakeBody;
    private int velocidad;
    protected final SnOOPe SnOOPeActual;
    protected boolean efectoActivo;
    private int[] proximoMovimientoCabeza;
    private Color colorCuerpo;
    private Color colorCabeza;
    protected char direccion;
    protected Snake snakeEnemiga;



    /**
     * Metodo cosntructor de la clase Snake
     */
    public  Snake(SnOOPe snOOPeActual)
    {
        snakeBody = new ArrayList<int[]>();
        velocidad = 100;
        this.SnOOPeActual = snOOPeActual;
        efectoActivo = true;
    }

    /**
     * getter de direccion
     * @return direccion del snake
     */
    public char getDireccion() {
        return direccion;
    }

    /**
     * getter de la snake Enemiga
     * @return snake Enemiga
     */
    public Snake getSnakeEnemiga() {
        return snakeEnemiga;
    }

    /**
     * setter de la snake Enemiga
     * @param snakeEnemiga snake Enemiga
     */
    public void setSnakeEnemiga(Snake snakeEnemiga) {
        this.snakeEnemiga = snakeEnemiga;
    }

    /**
     * setter de la direccion
     * @param direccion direcion del snake
     */
    public void setDireccion(char direccion) {
        this.direccion = direccion;
    }

    public Color getColorCuerpo() {
        return colorCuerpo;
    }

    public Color getColorCabeza() {
        return colorCabeza;
    }

    public void setColores(Color cabeza, Color cuerpo){
        colorCuerpo = cuerpo;
        colorCabeza = cabeza;
    }

    /**
     * getter de las proximas posiciones de la cabeza de la serpiente
     * @return cordenada de la cabeza en el t+1
     */
    public int[] getProximoMovimientoCabeza() {
        return proximoMovimientoCabeza;
    }

    /**
     * setter del proximas posiciones de la cabeza de la serpiente
     * @param proximoMovimientoCabeza cordenada de la cabeza en el t+1
     */
    public void setProximoMovimientoCabeza(int[] proximoMovimientoCabeza) {
        this.proximoMovimientoCabeza = proximoMovimientoCabeza;
    }

    /**
     * setter del efecto de la serpiente
     */
    public boolean isEfectoActivo() {
        return efectoActivo;
    }

    /**
     * getter del efecto de la serpiente
     * @param efectoActivo es el estado del efecto
     */
    public void setEfectoActivo(boolean efectoActivo) {
        this.efectoActivo = efectoActivo;
    }

    /**
     * setter del cuerpo de la serpiente
     * @param snakeBody
     */
    public void setSnakeBody(ArrayList<int[]> snakeBody) {
        this.snakeBody = snakeBody;
    }

    /**
     * Metodo getter de la velocidad de la serpiente
     * @return velocidad de la serpiente
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * metodo que aumenta la velocidad de la serpiente
     * @param velocidad a la que desea que aumente la serpiente
     */
    public void acelerarVelocidad(int velocidad) {
        if (velocidad>0)
        {
            this.velocidad -= velocidad;
        }
    }

    /**
     * metodo que  disminuye la velocidad de la serpiente
     * @param velocidad a la que desea que disminuye la serpiente
     */
    public void desacelerarVelocidad(int velocidad) {
        if (velocidad>0)
        {
            this.velocidad += velocidad;
        }
    }

    /**
     * Metodo setter de la velocidad de la serpiente
     * @param velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * getter del cuerpo de nuestra snake
     * @return cuerpo de la serpiente
     */
    public ArrayList<int[]> getSnakeBody() {
        return snakeBody;
    }

    /**
     * metodo que añade una parte nueva al cuerpo de la snake
     * @param pedazoCuerpo para añadir a la snake
     */
    public void addBodyToSnake(int[] pedazoCuerpo)
    {
        snakeBody.add(pedazoCuerpo);
    }

    /**
     * Metodo que quita una parte del cuerpo de la serpiente
     * @param index de donde se quiere remover el pedazo de cuerpo
     */
    public void removeBodyToSnake(int index) {
        snakeBody.remove(index);
    }

    /**
     * Metodo que devuelve una parte del cuepo especifica
     * @param index de la parte del cuerpo requerida
     */
    public int[]  getPartOfBody(int index)
    {
        return snakeBody.get(index);
    }

    /**
     * Metodo crecer aumenta el tamaño de la serpiente
     */
    public void crecer() {
        int indexOfCabezaSnake = getSnakeBody().size()-1;
        int addx = 0;
        int addy = 0;
        int [] cabeza = getSnakeBody().get(indexOfCabezaSnake);
        //System.out.println("entro a crecer"+true);
        int[] nuevo = {cabeza[0] + addx, cabeza[1] + addy};
        try {
            addBodyToSnake(nuevo);
        }catch (Exception e)
        {
            //System.out.println("GameOver,crecer"+true);
            Registro.registre(e);
            SnOOPeActual.setGameOver(true);
        }
        SnOOPeActual.ponerSnake();
    }

    /**
     * Metodo decrecer aumenta el tamaño de la serpiente
     */
    public  void decrecer() {
        int[] cord =getSnakeBody().get(0);
        removeBodyToSnake(0);
        SnOOPeActual.getTablero()[cord[0]][cord[1]]=0;
        SnOOPeActual.ponerSnake();
    }

    /**
     * Metodo abstracto que verifica el muvimiento de la serpiente
     */
    public abstract void verificaMov(int[] cabeza);

    /**
     * Metodo mover mueve la serpiente por el tablero
     */
    public void mover() {
        //System.out.println("entro a movio");
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
            SnOOPeActual.ponerSnake();
        }catch (Exception e) {
            Registro.registre(e);
            SnOOPeActual.setGameOver(true);
        }
    }

    /**
     * Metodo setter de la sepriente enemiga
     */
    public abstract void setSerpienteEnemigaDesdeSuInstaciaLocal();
}
