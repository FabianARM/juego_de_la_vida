package Modelo;

import Modelo.Celula;
import java.util.Random;
/**
 * Define las caracteristicas de un tablero de celulas
 * @author Fabi�n
 *
 */
public class Tablero {
	
	//Creamos un tablero de objetos de tipo celula 
	private Celula tablero[][];
	//Filas y columnas que va a tener el tablero 
	private int filas = 0;
	private int columnas = 0; 

	/**
	 * Metodo que calcula la cantidad de filas del tablero
	 * @return retorna un entero con la cantidad de filas de la columna 
	 */
	public int getFilas()
	{
		filas = tablero.length;
		return filas; 
	}
	/**
	 * Metodo que calcula la cantidad de columnas del tablero 
	 * @return retorna un entero que contiene la cantidad de columnas que tiene el tablero
	 */
	public int getColumnas()
	{
		columnas = tablero[0].length;
		return columnas; 
	}
        /**
         * Metodo que nos permite obtener el tablero desde otras celulas
         * @return 
         */
        public Celula[][] getTablero() 
        {
            return tablero;
        }
        /**
         * Metodo que nos permite modificar a tablero
         * @param tablero el nuevo tablero de objetos de tipo celula que quereamos
         */
        public void setTablero(Celula[][] tablero) 
        {
            this.tablero = tablero;
        }
        
        /**
	 * Metodo constructor que crar un tablero de un tama�o filas * columnas
	 * @param filasColumnas entero qque va a representar el tamñño del tablero 
	 */
	public Tablero(int filasColumnas)
	{
            //Le asignamos un tamaño a nuestro tablero
            this.tablero = new Celula[filasColumnas][filasColumnas];
            //Procedemos a llenarlo de objetos de tipo celula
            for(int filas = 0; filas < filasColumnas; filas++)
            {
                for(int columnas = 0; columnas < filasColumnas; columnas++ )
                {
                    //Ponemos un objeto celula en cada posicion de nuestro tablero 
                   this.tablero[filas][columnas] = new Celula();
                }
            }
           
	}
	/**
	 * Metodo que coloca celulas vivas en lugare aleatorios del tablero  utilizando  la clase secure random
	 * @param cantidadDeCelulas entero que representa la cantidad de celulas que se desea en el tablero
	 */
	public void rellenarTablero(int cantidadDeCelulas)
	{
            //Instanciamos a secure Random para utilizar su metodo next int
            Random nuevoRandom = new Random();
            //Creamos una matriz donde vamos a guardar los numero que ya fueron usados
            int numerosRepetidos[][] = new int[cantidadDeCelulas][2];
            //Donde vamos a guardar los numero producidos por secure random
            int filasCelulas = 0;
            int columnasCelulas = 0; 
            //Boolean para saber si el numero esta repetido o no 
            boolean bandera;
            //Lleva la fila por donde la comparacion
            int indiceNumerosRepetidos ; 
            //indice para añadir numeros a la matriz
            int indice = 0; 
            //For que va a controlar las celulas que vamos a crear
            for(int repeticion = cantidadDeCelulas; 0 < repeticion;)
            {
                bandera = false; 
                //Que genere un numero aleatorio para las filas y las columnas, esas van a ser nuestras celulas vivas
                filasCelulas = nuevoRandom.nextInt(getFilas());
                columnasCelulas = nuevoRandom.nextInt(getColumnas());
                //For para comprobar que la casilla 
                indiceNumerosRepetidos = 0; 
                
                
                for(int comparar = repeticion; comparar < cantidadDeCelulas; comparar++)
                {
                    
                    //Con este if preguntamos si los numeros que ya fueron generados son iguales al numero que se genero
                    if (filasCelulas == numerosRepetidos[indiceNumerosRepetidos][0] && columnasCelulas == numerosRepetidos[indiceNumerosRepetidos][1])
                    {
                       bandera = true; 
                    }
                    indiceNumerosRepetidos++; 
                }
                //Si el numero esta repetido generamos uno nuevo 
                if(bandera == true)
                {
                    filasCelulas = nuevoRandom.nextInt(getFilas());
                    columnasCelulas = nuevoRandom.nextInt(getColumnas());
                }
			
                else
                {
                    //Añadimos los numeros que no estan repetidos para posteriormente comparar
                    numerosRepetidos[indice][0] = filasCelulas;
                    numerosRepetidos[indice][1] = columnasCelulas;
                    
                    cambiarEstadoDeCelula(filasCelulas, columnasCelulas, true);
                    //Restamos las respeticiones que se deben hacer 
                    repeticion--;
                    //Aumentamos el indice donde vamos a guardar los numeros
                    indice++;
                }
			
            }
           

	}
	/**
	 * Metodo que permite modificar una casilla de el tablero cambiando su estado de false a true o viceversa
	 * @param fila entero fila donde se encuentra la casilla que queremos cambiar 
	 * @param columna entero columna donde se encuentra la casilla que queremos cambiar
	 * @param estado el nuevo boolean que queremos cambiar modificar en el objeto celula 
	 */
	public void cambiarEstadoDeCelula(int fila, int columna, boolean estado ) 
	{
		//Establecemos el nuevo valor que deseamos para la posicion
		tablero[fila][columna].setVida(estado); 
	}
	
	/**
         * Metodo que permite determinas las posiciones adyacentes de una celula en el tablero, siempre hay 8 vecinos por que el tablero es circular
         * @param posicionFila entero que representa la fila donde esta la celula de la que se desea determinar sus vecinos
         * @param posicionColumna entero que representa la columna donde esta la celula de la que se desea determinar sus vecinos 
         */
	public void getCelulasAdyacentes(int posicionFila, int posicionColumna)
	{
             int contadorDeFila = 0;     
            //Creamos el  donde vamos a guardar las coordenadas 
            int[][] matriz = new int[8][2];
                   contadorDeFila = 0;
                   //For que recorra las filas segun el vecino (-1, -1) de esta forma formando una matriz 3*3 con los vecinos a recorrer 
                   for (int fila = posicionFila - 1 ; fila <=  posicionFila + 1; fila++)
                    {
			//For que va a recorrer las columnas iniciando desde (-1, -1)
			for(int columna = posicionColumna -1; columna <= posicionColumna + 1; columna++)
                        {
                            
				//Si el numero de las coordenadas es el que ingreso el usuario para que no se tome en cuenta en las coordenadas
				if(fila == posicionFila && columna == posicionColumna)
				{
                                    //Hacemos que este ciclo no lo cuente
                                    contadorDeFila = contadorDeFila - 1;
				}
				else 
				{
					// comparamos que el vecino no vaya a exceder el rango de la matriz y no de coordenadas que no se encuentran en la matriz
					if (fila + 1 == 0 || columna + 1 == 0 || columna >= getTablero().length || fila >= getTablero().length)
					{
						 //Si esta en el borde izquierdo 
						if (fila == -1)
						 {
							matriz[contadorDeFila][0] = tablero.length - 1; 
						 }
						else 
						{
							//Si esta en el borde de arriba derecho 
							if(fila == tablero.length)
							{
								matriz[contadorDeFila][0] = 0;
							}
                                                        else
                                                        {
                                                            //Si la fila nno esta fuera del rango 
                                                            matriz[contadorDeFila][0] = fila;
                                                        }
						}
						//Si esta en el borde izquierdo 
                                                if (columna == -1)
						 {
							matriz[contadorDeFila][1] = tablero.length - 1; 
						 }
						else 
						{
							//Si esta en el borde de abajo derecho 
							if(columna == tablero.length)
							{
								matriz[contadorDeFila][1] = 0;
							}
                                                        else
                                                        {
                                                            //Si la columna no esta fuera del rango
                                                            matriz[contadorDeFila][1] = columna;
                                                        }
						}
					}
					else 
					{						
						//Recordamos sumarle una para que las coordenadas esten correctas por nuestra manera de que las la primera fila es 1 y no 0
						matriz[contadorDeFila][0] = fila; 
						matriz[contadorDeFila][1] = columna;
                                        }
                                }
                                //Avanzamos en el contador de fila
                                contadorDeFila++;
			}
                       
                    }
                    //Establecemos la matriz de posiciones de la celula
                    tablero[posicionFila][posicionColumna].setMatrizDePosiciones(matriz);
           
		
  }
        /**
         * Metodo que cuenta los vecinos vivos de la celula 
         * @param fila entero que representa la fila en la que se encuentra la celula de la que queremos saber su celulas adyacentes
         * @param columna entero que representa la columna donde se encuentra la celula de la que queremos saber sus celulas adyacentes
         */
        public void contadorDeCelulasAdyacentes(int fila, int columna)
        {
            int contadorDeCelulasVivas = 0; 
            int matrizDePosiciones[][] = tablero[fila][columna].getMatrizDePosiciones(); 
            for(int indice = 0; indice < matrizDePosiciones.length;indice++)
            {
                if(tablero[matrizDePosiciones[indice][0]][matrizDePosiciones[indice][1]].isVida() == true)
                {
                    contadorDeCelulasVivas++;
                }
            }
            tablero[fila][columna].setCelulasVecinas(contadorDeCelulasVivas);
        }
        /**
         * Metodo que realiza un conteo de la celulas vivas cada vez que es invocado
         * @return un entero que contiene el numero de celulas 
         */
        public int contadorDeCelulasVivas()
        {
            int contador = 0;
            //For que va a recorrer las filas
            for(int fila = 0; fila < tablero.length; fila++)
            {
                //for que recorre las columnas 
                for(int columna = 0; columna < tablero.length; columna++)
                {
                    //Preguntamos si la celula esta viva
                    if(tablero[fila][columna].isVida() == true)
                    {
                        //le sumamos uno al contador de celulas
                        contador++;
                    }
                }
                
            }
            return contador;
        }
}
