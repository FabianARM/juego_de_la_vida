/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EjecucionContinua;
import Modelo.JuegoDeLaVida;
import Modelo.Tablero;
import Vista.InterfazGrafica;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *Metodo controlador que nos permite unir la interfaz grafica del programa con los metodos necesarios para la ejecucion del juego 
 * @author Fabián
 */
public class Controlador implements ActionListener {
    //Instanciamos nuestros tableros
    private Tablero nuevoTablero;
    private Tablero nuevoTableroTemporal;
   //Nuestra interfaz grafica
    private InterfazGrafica nuevaInterfaz;
    //instanciamos nuestra clase donde esta contenido el juego
    JuegoDeLaVida nuevoJuego = new JuegoDeLaVida(); 
    //Intanciamos nuestro hilo de ejecucion 
    EjecucionContinua nuevaEjecucion;
    private int numeroDeCelulasIniciales  = 0;  
    
    public Controlador()
    {
        
    }
    /**
     * Metodo donde va a estar la ejecucion del programa y vamos a crear nuestros paneles y la ventana 
     */
    public void ejecutar() 
    {
         
        try
        {
            
            //Instanciamos nuestra interfaz
            nuevaInterfaz = new InterfazGrafica(this);
        
            //int donde vamos a almacenar el numero de columnas que va a tener el tablero y nuestra matriz de botones 
            int tamañoDelTablero = 0;
        
            //Le pedimos al usuario que digite el numero de filas y columnas que quiere en el tablero
            tamañoDelTablero = nuevaInterfaz.tamañoTablero();
        
            //Le asginamos un tamaño a nuestro tablero
            nuevoTablero = new Tablero(tamañoDelTablero);
            

        
            //Iniciamos nuestros paneles 
            nuevaInterfaz.construirPanelInferior(); //Panel inferior que contiene botones para manejar el programa 
            nuevaInterfaz.construirPanelSuperior(tamañoDelTablero);//Le damos el valor del que queremos que sea  nuestra matriz de botones
        
            //Añadimos las acciones de los botones 
            nuevaInterfaz.añadirAcciones(tamañoDelTablero);

            //Iniciamos nuestra ventana 
            nuevaInterfaz.construirVentana(); 
            
            
        
            //Hacemos que nuestra interfaz sea visible 
            nuevaInterfaz.setVisible(true);
        }
        //Si no se digita un numero entero 
        catch(NumberFormatException excepcionNumerica)
        {
            JOptionPane.showMessageDialog(null, excepcionNumerica.getMessage());
        }
    
    }
    /**
     * Metodo que actualiza el tablero de botones para que marque las celulas vivas correctamente
     * @param nuevoTablero objeto de tipo 
     */
    public void actualizarTablero(Tablero nuevoTablero)
    {
        
        for(int fila = 0; fila < nuevaInterfaz.getMatrizDeBotones().length; fila++)
        {
            for(int columna = 0; columna < nuevaInterfaz.getMatrizDeBotones().length; columna++)
            {
                //Comprobamos para ver si la celula esta viva y pintarla 
                if(nuevoTablero.getTablero()[fila][columna].isVida() == true)
                {
                   nuevaInterfaz.getMatrizDeBotones()[fila][columna].setBackground(Color.GREEN);
                }
                else
                {
                    //Sino  se pinta de rojo ya que esta muerta
                    nuevaInterfaz.getMatrizDeBotones()[fila][columna].setBackground(Color.red);
                }
            }
        }
    }
    /**
     * Metodo que reinicia el tablero 
     * @param nuevoTablero objeto tablero que debe ser reiniciado 
     */
    public Tablero reiniciarTablero(Tablero nuevoTablero)
    {
        //for que va a recorrer las filas 
        for(int fila = 0; fila < nuevoTablero.getTablero().length; fila++)
        {
            //for que va a recorrer las columnas del tablero
            for(int columna = 0; columna < nuevoTablero.getTablero().length; columna++)
            {
                nuevoTablero.cambiarEstadoDeCelula(fila, columna, false);
            }
        }
        return nuevoTablero; 
    }

    /**
     * Metodo para capturar los eventos que se crean cuando se presionan los botones de la interfaz 
     * @param evento parametro de tipo ActionEvent que se genera cuando se presiona un boton
     */
    @Override
    public void actionPerformed(ActionEvent evento) 
    {
        //Si se presiona siguiente generacion
        if(evento.getSource() == nuevaInterfaz.getSiguienteGeneracion())
        {
            //Actualizamos el tablero con el tablero de la siguiente generacion 
            nuevoTablero = nuevoJuego.ejecucionJuego(nuevoTablero); 
            //for que va a recorrer las filas 
            actualizarTablero(nuevoTablero);
        }
        else
        {
           //Si se presiona pausa
            if(evento.getSource() == nuevaInterfaz.getPausa())
            {
                nuevaEjecucion.cancel(true);
            }
            else
            {
                //Si se presiona el boton de reiniciar
                if(evento.getSource() == nuevaInterfaz.getReiniciar())
                {
                  nuevoTablero = reiniciarTablero(nuevoTablero);
                  actualizarTablero(nuevoTablero);    
                  nuevaInterfaz.celulasIniciales(true);
                }
                else
                {
                    //Cuando se presiona el boton de ejecucion continua 
                    if(evento.getSource() == nuevaInterfaz.getEjecucionContinua())
                    {
                        //habilitamos el boton de pausa solamente
                        nuevaInterfaz.hablitarPausa();
                        nuevaEjecucion = new EjecucionContinua(nuevaInterfaz, nuevoTablero, this);
                        nuevaEjecucion.execute();
                        
                    }
                    else
                    {
                        //Si se presiona el boton de celulas iniciales
                        if(evento.getSource() == nuevaInterfaz.getCelulasIniciales())
                        {
                            try
                            { 
                                //Pedimos el numero inicial de celulas
                                numeroDeCelulasIniciales = nuevaInterfaz.numeroDeCelulasIniciales();
                                nuevoTablero.rellenarTablero(numeroDeCelulasIniciales);
                                actualizarTablero(nuevoTablero);
                                nuevaInterfaz.celulasIniciales(false);
                            }
                            
                            catch(NullPointerException exception)
                            {
                                JOptionPane.showMessageDialog(nuevaInterfaz, exception.getMessage());
                            }
                            catch(NumberFormatException excepcion2)
                            {
                                JOptionPane.showMessageDialog(nuevaInterfaz, excepcion2.getMessage()); 
                            }
                        }
                        else
                        {
                            
                            //Si se presiona algun boton de la matrix de botones
                            
                            for(int fila = 0; fila < nuevaInterfaz.getMatrizDeBotones().length; fila++)
                            {
                                for(int columna = 0; columna < nuevaInterfaz.getMatrizDeBotones().length; columna++)
                                {
                                    if (evento.getSource() == nuevaInterfaz.getMatrizDeBotones()[fila][columna])
                                    {
                                        nuevaInterfaz.getMatrizDeBotones()[fila][columna].setBackground(Color.GREEN);
                                        nuevoTablero.cambiarEstadoDeCelula(fila, columna, true);
                                    }
                                }
                            }
                            
                        }     
                    }
                }
            }
        }
    }
            
    
}