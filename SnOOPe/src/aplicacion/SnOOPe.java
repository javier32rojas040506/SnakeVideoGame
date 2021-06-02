package aplicacion;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SnOOPe implements Serializable{
    //tablero auxiliar
    protected  Object[][] tablero;

    //estado del juego
    private boolean gameOver;

    //size del tablero
    private int width;
    private int height;

    //alimentos generados en tablero
    private Alimento[] alimentos;
    //Sopresas que aparecen en el tablero
    private Sorpresa sorpresa;
    //cord de la snake simulan la sanke player 1
    private Snake snakeP1;
    //cord de la snake simulan la sanke player 2
    private Snake snakeP2;
    //cord de la snake simulan la sanke player 3
    private Snake snakeP3;
    //direccion en el que se mueve la snake
    private int puntos;
    //puntos corresponde a la longitud de la serpiente P2
    private int puntosP2;
    //Nombre del player de  la serpiente
    private String nombre;
    //Nombre del player de  la serpiente  p2
    private String nombreP2;
    //color de la serpiente
    private Color[] colores;
    //numero de sorpresas usadas
    private int numSorpresas;
    //Sorpresa que tiene el jugador
    private Sorpresa sorpresasPendiente;
    //tipos de alimentos de SnOOPe
    private ArrayList<Alimento> tiposDeAlimentos;
    //tipos de Sorpresas de SnOOPe
    private ArrayList<Sorpresa> tiposDeSorpresas;
    //numero de cuadros de ancho y largo
    private  int numCuadrados;
    //Bloques Trampa
    private ArrayList<Pared> bloquesTrampa;
    //Proyectiles
    private ArrayList<ProyectilDeFuego> proyectilesDeFuego;
    private boolean twoPlayer;

    //Timers
    private transient Timer[] timers;
    private transient TimerTask taskActualizar1;
    private transient TimerTask taskActualizar2;

    public  Alimento[] getAlimentos() {
        return alimentos;
    }

    /**
     * contructor clase Snake
     * @param width ancho del tablero
     * @param height alto del tablero
    //* @param name nombre del jugador
    //* @param colores color del Snake
     */
    public SnOOPe(int width, int height, int numCuadrados){
        twoPlayer = false;
        this.width = width;
        this.height = height;
        alimentos = new Alimento[2];
        this.tiposDeAlimentos = new ArrayList<Alimento>();
        tiposDeAlimentos.add(new Fruta(this));
        tiposDeAlimentos.add(new FrutaArcoiris(this));
        tiposDeAlimentos.add(new Dulce(this));
        tiposDeAlimentos.add(new Veneno(this));
        this.tiposDeSorpresas = new  ArrayList<>();
        tiposDeSorpresas.add(new FlehasAumentanVelocidad(this));
        tiposDeSorpresas.add(new FlechasDisminucionVelocidad(this));
        tiposDeSorpresas.add(new Division(this));
        tiposDeSorpresas.add(new BloqueTrampa(this));
        tiposDeSorpresas.add(new EstrellaDeFuego(this));
        tiposDeSorpresas.add(new Lupa(this));
        colores = new Color[2];
        tablero = new Object[numCuadrados][numCuadrados];

        snakeP1 = new SnakePlayer1(this);
        int [] cuerpo1 = {0,0};
        int [] cuerpo2 = {1,0};
        int [] cabeza = {2,0};
        snakeP1.addBodyToSnake(cuerpo1);
        snakeP1.addBodyToSnake(cuerpo2);
        snakeP1.addBodyToSnake(cabeza);
        bloquesTrampa = new ArrayList<>();
        proyectilesDeFuego  = new ArrayList<>();
        snakeP1.setDireccion('D');

        for(Object[] filaEle:tablero)
        {
            for(int i = 0; i < filaEle.length;i++)
            {
                filaEle[i] = 0;
            }
        }
        gameOver = false;
        this.numCuadrados = numCuadrados;
        timers = new Timer[2];
    }


    //getter y  setters

    /**
     * getter de los tipos de alimentos
     * @return el contenedor de los alimentos
     */
    public ArrayList<Alimento> getTiposDeAlimentos() {
        return tiposDeAlimentos;
    }

    /**
     * getter de los tipos de sorpresas
     * @return el contenedor de los sorpresas
     */
    public ArrayList<Sorpresa> getTiposDeSorpresas() {
        return tiposDeSorpresas;
    }

    /**
     * getter de los dos jugadores
     * @return true si existen los dos jugadores
     */
    public boolean isTwoPlayer() {
        return twoPlayer;
    }

    /**
     * getter de los proyectiles de fuego
     * @return proyectiles de fuego en el tablero
     */
    public ArrayList<ProyectilDeFuego> getProyectilesDeFuego() {
        return proyectilesDeFuego;
    }

    /**
     * setter de los proyectiles de fuego
     * @param proyectilesDeFuego en el tablero
     */
    public void setProyectilesDeFuego(ArrayList<ProyectilDeFuego> proyectilesDeFuego) {
        this.proyectilesDeFuego = proyectilesDeFuego;
    }

    /**
     * getter de los bloques trmapa
     * @return
     */
    public ArrayList<Pared> getBloquesTrampa() {
        return bloquesTrampa;
    }

    /**
     * setter de los Bloques trampa
     * @param bloquesTrampa
     */
    public void setBloquesTrampa(ArrayList<Pared> bloquesTrampa) {
        this.bloquesTrampa = bloquesTrampa;
    }

    /**
     * getter del los timer de los alimentos
     * @return
     */
    public Timer[] getTimers() {
        return timers;
    }

    /**
     * metodo que hace set color sanker palyer uno
     * @param colorCuerpo del snakeP1
     * @param colorCabeza del snakeP2
     */
    public void setColorSnake1(Color colorCuerpo, Color colorCabeza) {
        snakeP1.setColores(colorCabeza, colorCuerpo);
    }

    /**
     * Metodo setter del color de la snakeP2
     * @param colorCuerpo
     * @param colorCabeza
     */
    public void setColorSnake2(Color colorCuerpo, Color colorCabeza){
        snakeP2.setColores(colorCabeza, colorCuerpo);
    }

    /**
     * Metodo setter del color de la snakeP
     * @param colorCuerpo
     * @param colorCabeza
     */
    public void setColorSnake3(Color colorCuerpo, Color colorCabeza){
        snakeP3.setColores(colorCabeza, colorCuerpo);
    }

    /**
     * getter de la sopresa Pendiente
     * @return sorpresa pendiente d ela snake
     */
    public Sorpresa getSorpresasPendiente() {
        return sorpresasPendiente;
    }

    /**
     * setter de la sopresa pendiente
     * @param sorpresasPendiente del el snake
     */
    public void setSorpresasPendiente(Sorpresa sorpresasPendiente) {
        this.sorpresasPendiente = sorpresasPendiente;
    }

    /**
     * Metodo getInstance de SnOOPe
     * return SnOOPe actual
     */
    public SnOOPe getInstance(){
        return this;
    }

    /**
     * getter del tablero de Snoop
     * @return
     */
    public  Object[][] getTablero() {
        return tablero;
    }

    /**
     * setter del gameOver
     * @param gameOver
     */
    public  void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * metdod getter del gameOver de sistema
     * @return
     */
    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * getcolor de la snake
     * @return color de snake
     */
    public Color[] getColores() {
        return colores;
    }

    /**
     * Metodo setter del color de la serpiente
     * @param color color de la serpiente en pantalla
     * @param indexOfColor cel indice represental la serpiente (permitidos 0 y  1)
     */
    public void setColores(Color color, int indexOfColor) {
        colores[indexOfColor] = color;
    }

    /**
     * getter del numero de cuadrados
     * @return numero de cuadrados del tablero
     */
    public int getNumCuadrados() {
        return numCuadrados;
    }

    /**
     * metodo que ajusta la direccion de la Snake
     * @param direccion indica la direccion hacia donde se mueve
     */
    public void setDireccionSnakeP1(char direccion){
        Character dir = (Character) snakeP1.getDireccion();
        Character dir2 = (Character) direccion;
        if((dir.equals('R')||dir.equals('L'))&&(dir2.equals('U')||dir2.equals('D'))) {
            snakeP1.setDireccion(direccion);
        }
        if((dir.equals('U')||dir.equals('D'))&&(dir2.equals('R')||dir2.equals('L'))) {
            snakeP1.setDireccion(direccion);
        }
    }

    /**
     * metodo que ajusta la direccion de la Snake
     * @param direccion indica la direccion hacia donde se mueve
     */
    public void setDireccionSnakeP2(char direccion) {
        Character dir = (Character) snakeP2.getDireccion();
        Character dir2 = (Character) direccion;
        if((dir.equals('R')||dir.equals('L'))&&(dir2.equals('U')||dir2.equals('D'))) {
            snakeP2.setDireccion(direccion);
        }
        if((dir.equals('U')||dir.equals('D'))&&(dir2.equals('R')||dir2.equals('L'))) {
            snakeP2.setDireccion(direccion);
        }
    }

    /**
     * metoro setter d ela sorpresa actual
     * @param sorpresa
     */
    public void setSorpresa(Sorpresa sorpresa) {
        this.sorpresa=sorpresa;
    }

    /**
     * metodo getcuerpo retorna las cordenanda de donde esta la snake 1
     * @return cuerpo de la Snake 1
     */
    public ArrayList<int[]> getSnakeP1Body()
    {
        return snakeP1.getSnakeBody();
    }

    /**
     * metodo getcuerpo retorna las cordenanda de donde esta la snake 2
     * @return cuerpo de la Snake 2
     */
    public ArrayList<int[]> getSnakeP2Body()
    {
        return snakeP2.getSnakeBody();
    }


    /**
     * metodo getcuerpo retorna las cordenanda de donde esta la snake 2
     * @return cuerpo de la Snake 2
     */
    public ArrayList<int[]> getSnakeP3Body()
    {
        return snakeP3.getSnakeBody();
    }

    /**
     * metodo getcuerpo retorna snake 1
     * @return Sanke 1
     */
    public Snake getSnakeP1()
    {
        return snakeP1;
    }

    /**
     * metodo getcuerpo retorna la snake 2
     * @return Snake 2
     */
    public Snake getSnakeP2() {
        return snakeP2;
    }

    /**
     * metodo getcuerpo retorna la snake 2
     * @return Snake 2
     */
    public Snake getSnakeP3() {
        return snakeP3;
    }

    /**
     * metodo getPuntos calcula los puntos de una Snake
     * @return puntos de la snake
     */
    public int getPuntos()
    {
        puntos = snakeP1.getSnakeBody().size()-1;
        return puntos;
    }

    /**
     * Metodo getNumSorpresa retorna el numero de sorpresas usadas
     * @return
     */
    public int getNumSorpresas()
    {
        return numSorpresas;
    }



    //Metodos de juego

    public void activarSnakeP2()
    {
        snakeP2 = new SnakePlayer2(this);
        snakeP2.setDireccion('U');
        twoPlayer = true;
        snakeP1.setSerpienteEnemigaDesdeSuInstaciaLocal();
        int [] cuerpo1 = {numCuadrados-1,numCuadrados-1};
        int [] cuerpo2 = {numCuadrados-2,numCuadrados-1};
        int [] cabeza = {numCuadrados-3,numCuadrados-1};
        snakeP2.addBodyToSnake(cuerpo1);
        snakeP2.addBodyToSnake(cuerpo2);
        snakeP2.addBodyToSnake(cabeza);
    }


    public void activarSnakeP3()
    {
        snakeP3 = new SnakeMaquina(this);
        snakeP3.setDireccion('U');
        twoPlayer = true;
        snakeP1.setSerpienteEnemigaDesdeSuInstaciaLocal();
        int [] cuerpo1 = {numCuadrados-1,numCuadrados-1};
        int [] cuerpo2 = {numCuadrados-2,numCuadrados-1};
        int [] cabeza = {numCuadrados-3,numCuadrados-1};
        snakeP3.addBodyToSnake(cuerpo1);
        snakeP3.addBodyToSnake(cuerpo2);
        snakeP3.addBodyToSnake(cabeza);
    }


    /**
     * Metodo ponerSnake pone la snake en el tablero auxiliar
     */
    public  void ponerSnake(){
        //poner elementos
        ArrayList<int[]> cuerpoP1 = getSnakeP1Body();

        for(int[] cuerpoSnake: cuerpoP1)
        {
            int cord_X = cuerpoSnake[0];
            int cord_Y = cuerpoSnake[1];
            try {
                tablero[cord_X][cord_Y] = 1;
            }catch (Exception e){
                gameOver = true;
            }
        }
    }

    public void ponerSnake2(){
        ArrayList<int[]> cuerpoP2 = getSnakeP2Body();
        for (int[] cuerpoSnakeP2 : cuerpoP2) {
            int cord_X = cuerpoSnakeP2[0];
            int cord_Y = cuerpoSnakeP2[1];
            try {
                tablero[cord_X][cord_Y] = 2;
            } catch (Exception e) {
                gameOver = true;
            }
        }
    }


    public void ponerSnake3(){
        ArrayList<int[]> cuerpoP3 = getSnakeP3Body();
        for (int[] cuerpoSnakeP3 : cuerpoP3) {
            int cord_X = cuerpoSnakeP3[0];
            int cord_Y = cuerpoSnakeP3[1];
            try {
                tablero[cord_X][cord_Y] = 2;
            } catch (Exception e) {
                gameOver = true;
            }
        }
    }

    /**
     * Metodo que genera un tippo de alimento en especifico
     * @return generarAlimento
     */
    public Alimento generarAlimento() {
        int random = (int) (Math.random() * tiposDeAlimentos.size());
        int cordx = (int) (Math.random() *numCuadrados); // WIDTH ES EL NUMERO DE PIXELES DE ANCHO
        int cordy = (int) (Math.random() *numCuadrados); // HEIGHT ES EL NUMERO DE PIXELES DE ANCHO
        if(!tablero[cordx][cordy].equals(0))
        {
            generarAlimento();
        }
        int[] cord = {cordx, cordy};

        Alimento res = new Fruta(this);
        if (tiposDeAlimentos.get(random) instanceof Fruta) {
            res = new Fruta(this);
        } else if (tiposDeAlimentos.get(random) instanceof FrutaArcoiris) {
            res = new FrutaArcoiris(this);
        } else if (tiposDeAlimentos.get(random) instanceof Dulce) {
            res = new Dulce(this);
        }else if (tiposDeAlimentos.get(random) instanceof Veneno) {
            res = new Veneno(this);
        }
        res.addColor(snakeP1.getColorCuerpo());
        try {
            res.addColor(snakeP2.getColorCuerpo());
        } catch (Exception e){}
        try {
            res.addColor(snakeP3.getColorCuerpo());
        } catch (Exception e){}
        res.reColor();
        res.setCord(cord);
        boolean cordenadaLibre = false;
        if(tablero[cordx][cordy].equals(0))
        {
            cordenadaLibre = true;
        }
        if(cordenadaLibre) {
            return  res;
        }
        else {
            try {
                if(snakeP1.getSnakeBody().size() == 0)
                {
                    gameOver = true;
                }
                else {
                    generarAlimento();
                }
            } catch (Exception e) {
                Registro.registre(e);
            }
        }
        //System.out.println(res);
        return res;
    }

    /**
     * metodo que dice si existe una sorpresa
     * @return true si exitse una sopresa y false si no
     */
    public boolean haySorpresa(){
        return sorpresasPendiente != null;
    }

    public void activarSorpresa(){
        System.out.println("Se uso la sorpresa"+" "+sorpresasPendiente.toString());
        Sorpresa aux = sorpresasPendiente;
        sorpresasPendiente = null;
        aux.darPoderSopresa(); // OJO
        Timer delayAlimento = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                try {
                    ponerSorpresa();
                } catch (SnOOPeExcepcion snOOPeExcepcion) {
                    snOOPeExcepcion.printStackTrace();
                }
                delayAlimento.cancel();
            }
        };
        delayAlimento.schedule(tarea, (int) (Math.random() * 5)*1000,1000);
    }

    /**
     * metodo que quita un alimento del tablero en especifico
     * @param alimentoAn corresponde al alimento a eliminar
     */
    public  void quitarAlimento(Alimento alimentoAn){
        tablero[alimentoAn.getCord()[0]][alimentoAn.getCord()[1]]=0;
    }

    public void timerOver(){
        timers[0].cancel();
        timers[1].cancel();
    }

    public Sorpresa getSorpresa() {
        return sorpresa;
    }

    public void timerReset(){
        timers[0] = new Timer();
        timers[1] = new Timer();
        taskActualizar1 = new TimerTask() {
            @Override
            public void run() {
                try{
                    quitarAlimento(alimentos[0]);
                    alimentos[0] = generarAlimento();
                    tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]]= alimentos[0];
                }catch (Exception e){}
            }
        };
        taskActualizar2 = new TimerTask() {
            @Override
            public void run() {
                try {
                    quitarAlimento(alimentos[1]);
                    alimentos[1] = generarAlimento();
                    tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                }catch (Exception e){
                }
            }
        };
        timers[0].schedule(taskActualizar1,7500,7500);
        timers[1].schedule(taskActualizar2,7500,7500);
    }

    public void readyUp(){
        timers = new Timer[2];
        timers[0] = new Timer();
        timers[1] = new Timer();
        taskActualizar1 = new TimerTask() {
            @Override
            public void run() {
                try{
                    quitarAlimento(alimentos[0]);
                    alimentos[0] = generarAlimento();
                    tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]]= alimentos[0];
                }catch (Exception e){}


            }
        };
        taskActualizar2 = new TimerTask() {
            @Override
            public void run() {
                try {
                    quitarAlimento(alimentos[1]);
                    alimentos[1] = generarAlimento();
                    tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                }catch (Exception e){}


            }
        };
        timers[0].schedule(taskActualizar1,7500,7500);
        timers[1].schedule(taskActualizar2, 7500,7500);
    }

    public void setFrutas(ArrayList<Alimento> frutas) throws SnOOPeExcepcion {
        tiposDeAlimentos = frutas;
        timers[0] = new Timer();
        timers[1] = new Timer();
        taskActualizar1 = new TimerTask() {
            @Override
            public void run() {
                try{
                    quitarAlimento(alimentos[0]);
                    alimentos[0] = generarAlimento();
                    tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]]= alimentos[0];
                }catch (Exception e){}


            }
        };
        taskActualizar2 = new TimerTask() {
            @Override
            public void run() {
                try {
                    quitarAlimento(alimentos[1]);
                    alimentos[1] = generarAlimento();
                    tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
                }catch (Exception e){}


            }
        };
        timers[0].schedule(taskActualizar1,7500,7500);
        timers[1].schedule(taskActualizar2, 7500,7500);
        try{
            alimentos[0] = generarAlimento();
            tablero[alimentos[0].getCord()[0]][alimentos[0].getCord()[1]] = alimentos[0];
            alimentos[1] = generarAlimento();
            tablero[alimentos[1].getCord()[0]][alimentos[1].getCord()[1]] = alimentos[1];
        } catch (Exception e){
            throw new SnOOPeExcepcion(SnOOPeExcepcion.ALIMENTOS_INSUFICIENTES);
        }
    }

    public void setTiposDeSorpresas(ArrayList<Sorpresa> tiposDeSorpresas) throws SnOOPeExcepcion {
        this.tiposDeSorpresas = tiposDeSorpresas;
        sorpresa = null;
        ponerSorpresa();
    }

    public void ponerSorpresa() throws SnOOPeExcepcion {
        if(sorpresa == null)
        {
            int random = (int) (Math.random() * tiposDeSorpresas.size());
            int cordx = (int) (Math.random() *numCuadrados);
            int cordy = (int) (Math.random() *numCuadrados);
            if(!tablero[cordx][cordy].equals(0))
            {
                ponerSorpresa();
            }
            try {
                sorpresa = tiposDeSorpresas.get(random);
            }catch (Exception e){
                throw new SnOOPeExcepcion(SnOOPeExcepcion.SORPRESAS_INSUFICIENTES);
            }
            int[] cord = {cordx, cordy};
            sorpresa.setCord(cord);
            tablero[sorpresa.getCord()[0]][sorpresa.getCord()[1]] = sorpresa;
        }
    }

    public void sorpresaForzada(Sorpresa sorpresa, int[] coord){
        sorpresa.setCord(coord);
        this.sorpresa = sorpresa;
        tablero[sorpresa.getCord()[0]][sorpresa.getCord()[1]] = sorpresa;
    }

    public void guardar(File file) throws SnOOPeExcepcion {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(this);
            out.close();

        }catch (NotSerializableException e){
            e.printStackTrace();
            throw new SnOOPeExcepcion(SnOOPeExcepcion.ERROR_GUARDADO);
        }
        catch(Exception e){
            //throw new SnOOPeExcepcion(SnOOPeExcepcion.ERROR_GUARDADO);
        }
    }

    /**
     * Metodo abrir Archivo
     * @param file arhivo a abrir
     * @throws SnOOPeExcepcion
     * @return snoope instance
     */
    public SnOOPe cargar(File file) throws IOException, ClassNotFoundException, SnOOPeExcepcion {
        try {
            ObjectInputStream abrirSnoope = new ObjectInputStream(new FileInputStream(file));
            SnOOPe snoopeAbierto = (SnOOPe) abrirSnoope.readObject();
            abrirSnoope.close();
            return snoopeAbierto;
        }catch (ClassNotFoundException e) {
            throw new ClassNotFoundException();
        }catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch(IOException e){
            throw new SnOOPeExcepcion(SnOOPeExcepcion.ERROR_CARGAR);
        }
    }

    /**
     * metodo que imprime el tablero del juego
     */
    public void printMatriz(){
        System.out.println("====================================================");
        for (Object[] matrizJoya : tablero) {
            System.out.print("|");
            for (int y = 0; y < matrizJoya.length; y++) {
                System.out.print(matrizJoya[y]);
                if (y != matrizJoya.length - 1) System.out.print("\t"+"\t");
            }
            System.out.println("|");
        }
    }


    public static void main(String[] args){
        SnOOPe juego1 = new SnOOPe(8,8,8);
        AccionSnOOPe accion = new AccionSnOOPe(juego1);
        juego1.activarSnakeP3();
        Thread hilo = new Thread(accion);
        hilo.start();
    }
}