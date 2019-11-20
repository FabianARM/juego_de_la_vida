/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *Clase donde definimos los botones poder interactuar con el usuario
 * @author Fabián
 */
public class InterfazGrafica extends JFrame {
 
    //Creamos los botones que vamos a utilizar en el panel inferior 
    private JButton celulasIniciales = new JButton("Celulas iniciales");
    private JButton siguienteGeneracion = new JButton("Siguiente");
    private JButton ejecucionContinua = new JButton("Ejecucion continua");
    private JButton reiniciar = new JButton("Reiniciar");
    private JButton pausa = new JButton("Pausa");
    //Instaciamos una variable de tipo JFrame
    private JFrame marco;
    //Instaciamos objetos de tipo JPanel
    private JPanel panelSuperior ;
    private JPanel panelInferior ;
    //Matriz de tipo JButton que vamos a utlizar para representar las celulas
    private JButton matrizDeBotones[][];
    //Variables donde almacenaremos los resultados de los eventos 
    private int matrizEventosPanelSuperior[] = new int[2];
    private String eventosPanelInferior;
    //Instanciamos controlador
    Controlador nuevoControlador;
    
   /**
    * Metodo constructor donde le enviamos una copia del control para poder pasarle los eventos de los botones 
    * @param control objeto control donde se van a manejar los eventos de los botones
    */
   public InterfazGrafica(Controlador control)
   {
       //Para enviar los eventos al control
       nuevoControlador = control; 
   }
    /**
     * Metodo contructor de la clase 
     * @param filasColumnas
     */
    public InterfazGrafica(int filasColumnas)
    {
      
        construirPanelSuperior(filasColumnas);
        añadirAcciones(filasColumnas);
        construirPanelInferior();
        construirVentana();
        
    }  
    /**
     * Metodo que permite obtener el boton celulas iniciales 
     * @return retorna el boton 
     */
    public JButton getCelulasIniciales() 
    {
        return celulasIniciales;
    }
    /**
     * Metodo get permite obtener el boton siguiente generacion d
     * @return retorna el boton
     */
    public JButton getSiguienteGeneracion() 
    {
        return siguienteGeneracion;
    }
    /**
     * Metodo get que nos permite obtener el boton ejecucion continua
     * @return retorna el boton
     */
    public JButton getEjecucionContinua()
    {
        return ejecucionContinua;
    }
    /**
     * Metodo get que nos permite obtener el boton reiniciar
     * @return retorna el boton
     */ 
    public JButton getReiniciar() 
    {
        return reiniciar;
    }
    /**
     * Metodo get que nos permite obtener el boton pausa
     * @return retorna el boton 
     */
    public JButton getPausa() {
        return pausa;
    }
    
    
    /**
     * Metodo get para obtener la matriz de botones
     * @return 
     */
    public JButton[][] getMatrizDeBotones()
    {
        return this.matrizDeBotones;
    }
    /**
     * Metodo para preguntar al usuario la cantidad de filas y columnas que desea que tenga el tablero
     * @return retorna un int con la cantidad que va representar el tamaño del tablero
     */
     public int tamañoTablero () throws NullPointerException
     {
         String entrada = ""; 
         int filasColumnas; 
         try 
         {
             entrada = JOptionPane.showInputDialog("Digite la cantidad de filas y columnas que desea que tenga el tablero" );
             filasColumnas = Integer.parseInt(entrada);
         }
         catch(NumberFormatException excepcionNumerica)
         {
             throw new NumberFormatException("Digite un número entero"); 
         }
         
         return filasColumnas; 
     }
     /**
      * Metodo que lanza una pregunta al usuario para que este digite cuantas celulas iniciales quiere en el tablero
      * @return un int que va a representar la cantidad de celulas vivas que desea el usuario en el tablero 
      * @throws NumberFormatException 
      */
     public int numeroDeCelulasIniciales () throws NullPointerException
     {
         String entrada = "";
         int numeroDeCelulas = 0; 
         try 
         {
             //Le pedimos al usuario que ingrese la cantidad de celulas iniciales
            entrada = JOptionPane.showInputDialog( marco,"Digite la cantidad de celulas inicales que desea " );
            //Lo pasamaos a entero 
            numeroDeCelulas = Integer.parseInt(entrada); 
            //Si el entero es mayor a el numero de botones generamos una excepcion
            if(numeroDeCelulas > getMatrizDeBotones().length * getMatrizDeBotones().length)
            {
              throw new NullPointerException("El numero que digito es mayor al numero de celulas en el tablero"); 
            }    
         
         }
         //Si se digita una letra capturamos la excepcion 
         catch(NumberFormatException excepcionNumerica)
         {
             //Si se digita una letra
             throw new NumberFormatException("Digite un número entero"); 
         }
         return numeroDeCelulas;
     }
     /**
     * Metodo que nos permite crear una matriz de botones 
     * @param filas int que representa la cantidad de filas que se desea que tenga la matriz 
     */
    public void construirPanelSuperior(int filas)
    {        
        //Le asignamos el tamaño a la matriz de botones 
        
        panelSuperior = new JPanel();
        //
        GridLayout distribucion = new GridLayout(filas, filas); 
        //Distribucion de el panel superior
        panelSuperior.setLayout(distribucion);
        
        //Creamos la matriz de botones
        matrizDeBotones = new JButton[filas][filas];
        //Le añadimos acciones a los botones y los añadimos al panelSuperior 
        for(int fila = 0; fila < matrizDeBotones.length; fila++)
        {
            //For que recorre las columnas de la matriz
            for(int columna = 0; columna < matrizDeBotones[0].length; columna++)
            {
                //Añadimos el boton
                matrizDeBotones[fila][columna] = new JButton();
                //Le cambiamos el color a nuestros botones+
                matrizDeBotones[fila][columna].setBackground(Color.red);
                //Añadimos los botones al panel
                panelSuperior.add(matrizDeBotones[fila][columna]);          
            }
        }  
    }
    /**
     * Metodo que nos permite construir los botones de la parte inferior de la pantalla
     */
    public void construirPanelInferior()
    {
        panelInferior = new JPanel(); 
        
        FlowLayout colocacion = new FlowLayout(5 );
        panelInferior.setLayout(colocacion);
          
        //Los añadimos en el panel
        panelInferior.add(celulasIniciales);
        panelInferior.add(reiniciar);
        panelInferior.add(siguienteGeneracion);
        panelInferior.add(ejecucionContinua); 
        panelInferior.add(pausa);
        //Hacemos que el boton pausa no este disponible al inicio
        pausa.setEnabled(false);
    }
    /**
     * Metodo donde se añade acciones a los botones
     * @param filasColumnas
     */
    public void añadirAcciones(int filasColumnas)
    {
        //Creamos un objeto para añadir opciones a los botones 
        
       
        //For que va a recorrer las filas
        for(int fila = 0; fila < filasColumnas; fila++)
        {
            
            //For que va a recorrer las columnas 
            for(int columnas = 0; columnas < filasColumnas; columnas++)
            {
                //Añadimos las acciones
                matrizDeBotones[fila][columnas].addActionListener(nuevoControlador);   
            }
        }
        //le añadimos acciones a los botones
        celulasIniciales.addActionListener(nuevoControlador);
        reiniciar.addActionListener(nuevoControlador);
        siguienteGeneracion.addActionListener(nuevoControlador);
        ejecucionContinua.addActionListener(nuevoControlador);
        pausa.addActionListener(nuevoControlador);    
        
    }
    /**
     * Metodo para construir una ventana con JFrame, uniendi las dos paneles 
     */
    public void construirVentana()
    {
        marco = new JFrame("Juego de la vida");
        //Establecemos la distribucion de la ventana 
        
        //Distribucion de los paneles en la ventana
        marco.setLayout(new BorderLayout());
        
        //Añadimos los paneles a la ventana
        marco.add(panelSuperior, BorderLayout.CENTER);
        marco.add(panelInferior, BorderLayout.SOUTH);
        
        marco.pack();
        //Se establece que el boton de cerrar ventana termine con la ejecucion del prrograman
        marco.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Metodo que asigna acciones a una matriz de tipo JButton 
     * @param visibilidad
     */
    public void setVisible(boolean visibilidad)
    {
         marco.setVisible(visibilidad);
    }
    /**
     * Metodo que habilita el boton de pausa y desabilita todos lo demas botones, para que estos no sean presionados mientras esta la ejecucion continua
     */
    public void hablitarPausa()
    {
        pausa.setEnabled(true);
        ejecucionContinua.setEnabled(false);
        celulasIniciales.setEnabled(false);
        siguienteGeneracion.setEnabled(false);
        reiniciar.setEnabled(false);
    }
    /**
     * Metodo que desabilita el boton pausa y habilita los demás
     */
    public void habilitarEjecucionContinua()
    {
        pausa.setEnabled(false);
        ejecucionContinua.setEnabled(true);
        celulasIniciales.setEnabled(true);
        siguienteGeneracion.setEnabled(true);
        reiniciar.setEnabled(true);
    }
    /**
     * Metodo que permite cambiar si el boton celulas iniciales esta habilitado o no 
     * @param habilitacion boolean que le dice al metodo si deshabilitar o habilitar el boton
     */
    public void celulasIniciales(boolean habilitacion)
    {
        celulasIniciales.setEnabled(habilitacion);
    }

    

}

    

