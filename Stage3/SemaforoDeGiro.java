////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa un semaforo de 1 luz, para representar 
//  la flecha de giro. Incluye métodos para cambio de luz
//  para solicitar el tiempo de encendido y para imprimir el
//  estado actual del semaforo. "1" significa luz verde encendida, y 
//  "0" signifiva luz verde apagada
//
////////////////////////////////////////////////////////////////

public class SemaforoDeGiro{

    //Tiempo de encendido en verde y tiempo de parpadeo de luz
    int greenTimeP,blinkTime;

    //Estado de la luz verde
    boolean green;

    //Constructor que recibe el tiempo en verde (gT) y el tiempo de parpadeo (bT)
    public SemaforoDeGiro(int gT, int bT) {
        greenTimeP = gT;
        blinkTime = bT;
    }

    //Enciende la luz
    public void turnGreenLightOn() {
        green=true;
    }

    //Apaga la luz
    public void turnGreenLightOff() {
        green=false;
    }

    //Entrega el tiempo de encendido para el verde
    public int getGreenLightTime() {
        return greenTimeP; 
    }

    //Entrega el tiempo de parpadeo de la luz
    public int getBlinkingTime() {
        return blinkTime;
    }

    //Habilita la capacidad de imprimir el estado actual del semaforo
    public String toString(){
        if (green){
            return("1");
        }
        else{
            return("0");
        }
    }
}