public class SemaforoDeGiro{

    int greenTimeP,blinkTime;

    boolean green;

    public SemaforoDeGiro(int gT, int bT) {
        greenTimeP = gT;
        blinkTime = bT;
    }

    public void turnGreenLightOn() {
        green=true;
    }
    public void turnGreenLightOff() {
        green=false;
    }
    public int getGreenLightTime() {
        return greenTimeP; 
    }
    public int getBlinkingTime() {
        return blinkTime;
    }
    public String toString(){
        if (green){
            return("1");
        }
        else{
            return("0");
        }
    }
}