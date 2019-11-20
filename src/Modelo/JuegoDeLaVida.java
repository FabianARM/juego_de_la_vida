/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *Clase que va a reprsentar el juego de la vida 
 * @author Fabián
 */
public class JuegoDeLaVida {
    /**
     * Metodo que va a realizar la ejecucion del juego de la vida 
     * @param nuevoTablero tablero de celulas que va a recibir el metodo para poder ejecutarlo
     * @return un objeto de tipo tablero que va a contener el nuevo tablero 
     */
    public Tablero ejecucionJuego(Tablero nuevoTablero)
    {   
        //Matriz de booleanos donde vamos a guardar si la celula esta viva o muerta, tiene que ser del tamaño que es el tablero para que hayan errores
        boolean matrizDeLaVida[][] = new boolean[nuevoTablero.getFilas()][nuevoTablero.getColumnas()];
        //Matriz que va a contener las celulas vivas vecinas 
        int matrizCelulasAdyacentes[][]; 
        //Boolean que contiene si la celula va a estar viva o muerta en la siguiente generacion 
        boolean vidaDeLaCelula;
       
        //For que va recorrer las fila del tablero 
        for(int fila = 0; fila < nuevoTablero.getFilas(); fila++)
        {
            //for que se va a encargar de recorrer las columnas 
            for(int columna = 0; columna < nuevoTablero.getColumnas(); columna++)
            {
                nuevoTablero.getCelulasAdyacentes(fila, columna);
                //sacamos las celulas adyacentes vivas
                nuevoTablero.contadorDeCelulasAdyacentes(fila, columna);
                //Asignar si la celula va a sobrevivir en la siguiente generacion 
                matrizDeLaVida[fila][columna] = nuevoTablero.getTablero()[fila][columna].supervivencia(); 
            }
        }
        //Hacemos que los cambios en la matriz de booleanos se vean reflejados en el objeto 
        //For que va recorrer las fila del tablero 
        for(int fila = 0; fila < nuevoTablero.getFilas(); fila++)
        {
            //for que se va a encargar de recorrer las columnas 
            for(int columna = 0; columna < nuevoTablero.getColumnas(); columna++)
            {
                 nuevoTablero.cambiarEstadoDeCelula(fila, columna, matrizDeLaVida[fila][columna]);
            }
        }
        return nuevoTablero;
    }
}
