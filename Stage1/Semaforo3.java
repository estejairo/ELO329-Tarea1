////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa un semaforo de 3 luces, 
//  con tiempo de amarillo fijo, métodos para cambio de luces,
//  para solicitar el tiempo de encendido y para imprimir el
//  estado actual del semaforo.
//
////////////////////////////////////////////////////////////////


public class Semaforo3{
    
    // Estados del semaforo representados en luces
    boolean red,green,yellow; 

    // Luces con tiempo de encendido variable
    int redTime, greenTime;
    
    // Luces con tiempo de encendido fijo
    static int yellowTime= 3; 

    // Constructor del semaforo por defecto
    public Semaforo3(){}

    // Constructor que incluye configuracion de luz verde
    public Semaforo3 (int greenTime) {
        this.greenTime=greenTime;
    }

    // Metodo para encender la luz roja, apaga las otras
    public void turnRedLightON() {
        green = false;
        yellow = false;
        red = true;
    }

    // Metodo para encender la luz amarilla, apaga las otras
    public void turnYellowLightON() {
        green = false;
        red = false;
        yellow = true;
    }

    // Metodo para encender la luz verde, apaga las otras
    public void turnGreenLightON() {
        yellow = false;
        red = false;
        green = true;
    }

    // Metodo que entrega el tiempo de duracion de la luz verde
    public int getGreenTime() {
        return greenTime;
    }

    // Metodo que entrega el tiempo de duracion de la luz amarilla
    public static int getYellowTime() {
        return yellowTime;
    }

    // Metodo que habilita la capacidad de imprimir el estado actual del semaforo
    public String toString() {
        if (red){
            return("0");
        }
        else if (yellow){
            return("1");
        }
        else{
            return("2");
        }

    }   
}
