////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa un semaforo de 2 luces, para representar 
//  un semaforo peatonal. Incluye métodos para cambio de luz,
//  para solicitar el tiempo de encendido y para imprimir el
//  estado actual del semaforo. "1" significa luz verde encendida, y 
//  "0" signifiva luz verde apagada
//
////////////////////////////////////////////////////////////////


public class SemaforoP{

    //Tiempo de encendido en verde y tiempo de parpadeo de luz
    int greenTimeP,blinkTime;

    //Estado de la luz verde y roja. Por defecto parte en roja
    boolean red = true;
    boolean green;

    //Constructor que recibe el tiempo en verde (greenTime) y el tiempo de parpadeo (blinkingTime)
    public SemaforoP(int greenTime, int blinkingTime) {
        this.greenTimeP=greenTime;
        this.blinkTime=blinkingTime;
    }

    //Enciende la luz roja
    public void turnRedLightOn() {
        red=true;
        green=false;
    }

    //Enciende la luz verde
    public void turnGreenLightOn() {
        green=true;
        red=false;
    }

    //Apaga la luz verde, sin encender la roja
    public void turnGreenLightOff() {
        green=false;

    }

    //Entrega el tiempo de encendido para el verde
    public int getGreenLightTime() {
        return greenTimeP; 
    }

    //Entrega el tiempo de parpadeo de la luz verde
    public int getBlinkingTime() {
        return blinkTime;
    }

    //Habilita la capacidad de imprimir el estado actual del semaforo
    public String toString(){
        if (green){
            return("1");
        }
        else if(red){
                return("0");
            }
        else{
            return(" ");
        }
    }
}