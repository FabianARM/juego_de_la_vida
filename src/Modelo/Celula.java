package Modelo;
/**
 * Clase celula que contiene los metodos y los atributos caracteistcos de una celula
 * @author Fabi�n
 *
 */
public class Celula {
	// Boolean que va definir si la celula existe o no en el tablero 
	private boolean vida = false; 
	//Variable donde se va a amacenar la cantidad de celulas vivas alrededor de una celula en especifico 
	private int celulasVecinas = 0; 
	//Variable que nos va a permitir llevar el conteo de cuantas celulas vivas hay
        
	private static int cantidadDeCelulas = 0;
        //Posiciones adyacentes de la celula
        private int[][] matrizDePosiciones ;
    /**
     * 
     * @return 
     */
    public static int getCantidadDeCelulas() 
    {
        return cantidadDeCelulas;
    }
    /**
     * 
     * @param cantidadDeCelulas 
     */
    public static void setCantidadDeCelulas(int cantidadDeCelulas) 
    {
        Celula.cantidadDeCelulas = cantidadDeCelulas;
    }
        
        
        /**
         * Metodo que nos permite obtener la matrizDeposiciones adyacentes
         * @return retorna una matriz de enteros con las posiciones adyacentes 
         */
        public int[][] getMatrizDePosiciones() 
        {
            return matrizDePosiciones;
        }
        /**
         * Metodo que permite establecer el valor de matriz 
         * @param matrizDePosiciones 
         */
        public void setMatrizDePosiciones(int[][] matrizDePosiciones) 
        {
            this.matrizDePosiciones = matrizDePosiciones;
        }
        
        
	/**
	 * Metodo is que permite saber el estado de vid
	 * @return Retorna un boolean que hace referecia al estado de vida si es falso � verdadero
	 */
	public boolean isVida()
	{
		return vida;
	}
	/**
	 * Metodo set de la variable vida que nos permite cambiar el estado de vida
	 * @param vida boolean que tiene el nuevo valor que se va a asignar a vida
	 */
	public void setVida(boolean vida)
	{
		this.vida = vida;
	}
        /**
         * Metodo set que nos permite establecer el valor de celulas vecinas
         * @param celulasVecinas 
         */
        public void setCelulasVecinas(int celulasVecinas)
        {
            this.celulasVecinas = celulasVecinas; 
        }
	/**
	 * metodo get de la variable celulas vecinas que permite obtener su valor desde diferentes clases
	 * @return Retorna el valor de celulasVecinas 
	 */
	public int getCelulasVecinas()
	{
		return celulasVecinas;
	}
        /**
         * Reglas se supervivencia que establecen si una celula va a vivir o va a morir en su siguiente generacion
     * @return 
         */
        public boolean supervivencia()
        {
            //Si la celula esta muerta se deben realizar esta acciones
            if(isVida() == false)
            {
                //Si una celula esta muera y esta rodeada por 3 celulas nacera
                if (getCelulasVecinas() == 3)
                {
                    return (true);
                }
                else
                {
                    //Seguira muerta
                     return (false);
                }
            }
            //Si las celulas estan vivas se deben realizar esta acciones
            else
            {
                //Si la celula no tiene vecino o tiene solo uno va a morir por soledad
                if(getCelulasVecinas() <= 1)
                {
                     return (false); 
                }
                else
                {
                    //Si la celula esta rodeada por 2 o 3 celulas sobrevivira
                    if(getCelulasVecinas() == 2 || getCelulasVecinas() == 3 )
                    {
                         return (true); 
                    }
                    else
                    {
                        //si la celula esta rodeada por más de 3 vecinos esta morira por sobrepoblacion
                         return false;                     
                    }
                }
            }
       }
	

	
	
}
