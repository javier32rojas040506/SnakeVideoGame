package aplicacion;

public class SnOOPeExcepcion extends Exception {
    public static String ALIMENTOS_INSUFICIENTES = "Se necesita al menos un alimento para jugar";
    public static String SORPRESAS_INSUFICIENTES = "Se necesita al menos una sorpresa para jugar";
    public static String ERROR_GUARDADO = "Ocurrio un error al guardar";
    public static String ERROR_CARGAR = "Ocurrio un error al cargar";

    public SnOOPeExcepcion(String msg){
        super(msg);
    }
}