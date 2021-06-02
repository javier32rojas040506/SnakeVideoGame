package aplicacion;

public class AccionSnOOPe implements Runnable{
    private SnOOPe snoope;

    public AccionSnOOPe(SnOOPe snoope){
        this.snoope = snoope;
    }

    @Override
    public void run() {
        while (!snoope.getGameOver()){
            try {
                snoope.getSnakeP1().mover();
                //snoope.getSnakeP2().mover();
                snoope.getSnakeP3().mover();
                snoope.printMatriz();
                Thread.sleep(1000);
            } catch (Exception e) {
                Registro.registre(e);
            }
        }
        System.out.println("GAME OVER");
    }
}