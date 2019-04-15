import java.util.Timer;
import java.io.*;
import java.util.Scanner;


public class SemaforoP{

    int greenTimeP,blinkTime;

    boolean red = true;
    boolean green;

    public SemaforoP(int greenTime, int blinkingTime) {
        this.greenTimeP=greenTime;
        this.blinkTime=blinkingTime;
    }
    public void turnRedLightOn() {
        red=true;
        green=false;
    }
    public void turnGreenLightOn() {
        green=true;
        red=false;
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
        else if(red){
                return("0");
            }
        else{
            return(" ");
        }
    }
}