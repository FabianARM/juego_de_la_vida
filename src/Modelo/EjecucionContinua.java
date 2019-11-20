/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Controlador;
import Vista.InterfazGrafica;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *Clase que crea un hilo de ejecucion 
 * @author Fabián
 */
public class EjecucionContinua extends SwingWorker<Tablero, Object>  {
    //Instaciamos nuestra interfaz
    InterfazGrafica nuevaInterfaz;
    //Instanciamos nuestro controlador
    Controlador nuevoControlador;
    //Instanciamos nuestro tablero
    Tablero nuevoTablero;
    /**
     * Metodo constructor de la clase
     * @param nuevaInterfaz objeto de tipo InterfazGrafica, que va contener los botones en estado que se estan trabajando en el controlador 
     * @param nuevoTablero objeto de tipo Tablero que va contener los botones en estado que se estan trabajando en el controlador 
     * @param nuevoControlador objeto de tipo Controlador, que es el controlador que se esta  trabajando actualmente
     */
    public EjecucionContinua(InterfazGrafica nuevaInterfaz ,Tablero nuevoTablero, Controlador nuevoControlador)
    {
        this.nuevaInterfaz = nuevaInterfaz; 
        this.nuevoTablero = nuevoTablero; 
        this.nuevoControlador = nuevoControlador;
    }
    /**
     * Metodo que permite crear un hilo de ejecucion 
     * @return un objeto de tipo tablero 
     * @throws Exception 
     */
    @Override
    protected Tablero doInBackground() throws Exception {
        //Matriz de booleanos donde vamos a guardar si la celula esta viva o muerta, tiene que ser del tamaño que es el tablero para que hayan errores
        boolean matrizDeLaVida[][] = new boolean[nuevoTablero.getFilas()][nuevoTablero.getColumnas()];
        //Matriz que va a contener las celulas vivas vecinas 
        int matrizCelulasAdyacentes[][]; 
        //Boolean que contiene si la celula va a estar viva o muerta en la siguiente generacion 
        boolean vidaDeLaCelula;
        
        //While que nos permite decirle que se ejecute hasta que no hayan celulas vivas
        while(nuevoTablero.contadorDeCelulasVivas() != 0)
        {   
            //comparacion para que el proceso no continue si es cancelado
            if(!isCancelled())
            {
                
                try
                {
                    //Delay de 1 segundo entre cada ejecucion del ciclo while
                    Thread.sleep(1000);
                }
                //Si el proceso es interrumpido el metodo anterior genera una excepcion la capturamos aqui 
                catch(InterruptedException excepecion)
                {
                    //Con un JOptionPane le decimos que el proceso ha sido pausado
                    JOptionPane.showMessageDialog(null, "La ejecucion fue pausada");
                }
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
               
                nuevoControlador.actualizarTablero(nuevoTablero);
            }
            //Si el proceso se cancelo que retorne el tablero 
            else
            {
                return nuevoTablero;
            }
        }
      
        
        //Si se detiene que retorne los resultado que lleva 
       return nuevoTablero; 
    }
    /**
     * Metodo que se ejecuta cuando el hilo ha dejado de ejecutarse 
     */
    @Override
    public void done()
    {
        //Le decimos que habilite el resto de botones 
        nuevaInterfaz.habilitarEjecucionContinua();
        nuevaInterfaz.celulasIniciales(true);
    }
    
    
    
}
