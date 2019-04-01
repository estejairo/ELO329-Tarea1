import java.util.Timer;
import java.io.*;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;
public class Semaforo3{
    
    boolean red,green,yellow;

    int redTime, greenTime;
    
    static int yellowTime= 3; 

    public Semaforo3(){}

    public Semaforo3 (int greenTime) {
        this.greenTime=greenTime;
    }

    public void turnRedLightON() {
        green = false;
        yellow = false;
        red = true;
    }

    public void turnYellowLightON() {
        green = false;
        red = false;
        yellow = true;
    }

    public void turnGreenLightON() {
        yellow = false;
        red = false;
        green = true;
    }

    public int getGreenTime() {
        return greenTime;
    }
    public static int getYellowTime() {
        return yellowTime;
    }

    //public String toString() {}
}