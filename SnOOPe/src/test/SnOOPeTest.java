package test;

import aplicacion.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import presentacion.SnOOPeGUI;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SnOOPeTest {
    //crear un juego
    private SnOOPe juego1 = new SnOOPe(8,8,8);

    @BeforeEach
    void setUp() {
        juego1 = new SnOOPe(8,8,8);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    /**
     * verifica que ponga Alimentos en el tablero
     */
    void shouldPonerAlimento() throws SnOOPeExcepcion {
        juego1.setFrutas(juego1.getTiposDeAlimentos());
        //obtenemos los alimentos
        Alimento[] alimentos = juego1.getAlimentos();
        int[] cord1 = alimentos[0].getCord();
        int[] cord2 = alimentos[1].getCord();
        assertEquals(juego1.getTablero()[cord1[0]][cord1[1]],alimentos[0]);
        assertEquals(juego1.getTablero()[cord2[0]][cord2[1]],alimentos[1]);
    }

    @Test
    /**
     * verifica que la serpiente cresca
     */
    void shouldCrecer() {
        //crece en la posicion inicial
        juego1.getSnakeP1().crecer();
        assertEquals(juego1.getSnakeP1Body().size(),4);
    }

    @Test
    /**
     * verifica que la serpiente se encoja
     */
    void shouldDecrecer() {
        juego1.getSnakeP1().decrecer();
        assertEquals(juego1.getSnakeP1Body().size(),2);
    }

    @Test
    /**
     * verifica que la serpiente se mueva en la dirreccionEspecificada
     */
    void shouldMover() {
        //se mueve una posicion depues de haber crecido desde la posicion inicial
        juego1.setDireccionSnakeP1('R');
        juego1.getSnakeP1().mover();
        assertEquals(juego1.getTablero()[2][1],1);
        juego1.setDireccionSnakeP1('D');
        juego1.getSnakeP1().mover();
        assertEquals(juego1.getTablero()[3][1],1);
        juego1.setDireccionSnakeP1('L');
        juego1.getSnakeP1().mover();
        assertEquals(juego1.getTablero()[3][0],1);
        juego1.setDireccionSnakeP1('D');
        juego1.getSnakeP1().mover();
        assertEquals(juego1.getTablero()[4][0],1);
        juego1.setDireccionSnakeP1('R');
        juego1.getSnakeP1().mover();
        assertEquals(juego1.getTablero()[4][1],1);
    }

    @Test
    void shouldAumentarVelocidad(){
        juego1.getSnakeP1().setDireccion('R');
        int[] coords = {2,1};
        FlehasAumentanVelocidad felchaMas = new FlehasAumentanVelocidad(juego1);
        int velocidadInicial = juego1.getSnakeP1().getVelocidad();
        juego1.sorpresaForzada(felchaMas, coords);
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.activarSorpresa();
        assertNotEquals(velocidadInicial, juego1.getSnakeP1().getVelocidad());
        assertTrue(velocidadInicial > juego1.getSnakeP1().getVelocidad());
    }

    @Test
    void shouldAumentarVelocidad2(){
        juego1.getSnakeP1().setDireccion('R');
        int[] coords = {2,3};
        FlehasAumentanVelocidad felchaMas = new FlehasAumentanVelocidad(juego1);
        int velocidadInicial = juego1.getSnakeP1().getVelocidad();
        juego1.sorpresaForzada(felchaMas, coords);
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.activarSorpresa();
        assertNotEquals(velocidadInicial, juego1.getSnakeP1().getVelocidad());
    }

    @Test
    void shouldDisminuirVelocidad(){
        juego1.getSnakeP1().setDireccion('R');
        int[] coords = {2,1};
        FlechasDisminucionVelocidad felchaMenos = new FlechasDisminucionVelocidad(juego1);
        int velocidadInicial = juego1.getSnakeP1().getVelocidad();
        juego1.sorpresaForzada(felchaMenos, coords);
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.activarSorpresa();
        assertNotEquals(velocidadInicial, juego1.getSnakeP1().getVelocidad());
        assertTrue(velocidadInicial < juego1.getSnakeP1().getVelocidad());
    }

    @Test
    void shouldDisminuirVelocidad2(){
        juego1.getSnakeP1().setDireccion('R');
        int[] coords = {2,4};
        FlechasDisminucionVelocidad felchaMenos = new FlechasDisminucionVelocidad(juego1);
        int velocidadInicial = juego1.getSnakeP1().getVelocidad();
        juego1.sorpresaForzada(felchaMenos, coords);
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.getSnakeP1().mover();
        juego1.activarSorpresa();
        assertNotEquals(velocidadInicial, juego1.getSnakeP1().getVelocidad());
        assertTrue(velocidadInicial < juego1.getSnakeP1().getVelocidad());
    }

    @Test
    /**
     * verifica que la funcion que verifica que el movimiento de la serpiente sea valido sea correcta
     */
    void shouldVerificaMov() {
        for (int i =0; i<juego1.getNumCuadrados()+1;i++)
        {
            juego1.getSnakeP1().mover();
        }
        assertTrue(juego1.getGameOver());
    }
}