import java.util.Timer;
import java.io.*;
import java.util.Scanner;
//import java.time.LocalTime;
//import java.time.Duration;

public class SemaforoDeGiro{

    int greenTimeP,blinkTime;

    boolean red,green;

    public SemaforoDeGiro(int greenTime, int blinkingTime) {
    }
    //public void turnRedLightOn() {
    //    green=false;
    //    red=true;
    //}
    public void turnGreenLightOn() {
        green=true;
        red=false;
    }
    public void turnGreenLightOff() {

    }
    public int getGreenLightTime() {
        return greenTimeP; 
    }
    public int getBlinkingTime() {
        return blinkTime;
    }
    public String toString(){
        return null;
    }
}