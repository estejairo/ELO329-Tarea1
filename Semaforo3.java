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
        LocalTime ti = LocalTime.now();
        LocalTime tf = LocalTime.now(); 
        //verde
        while(tf.getSecond()-ti.getSecond()<greenTime){
            int dif0=tf.getSecond()-ti.getSecond();
            turnGreenLightON();
            //System.out.println( dif0 + "[s] 0");
            tf=LocalTime.now();
        }
        //amarillo
        ti = LocalTime.now();
        tf = LocalTime.now(); 

        while(tf.getSecond()-ti.getSecond()<yellowTime){
            int dif1=tf.getSecond()-ti.getSecond();
            turnYellowLightON();
            //System.out.println(dif1 + " [s]  1");
            tf=LocalTime.now();
        }
        //Rojo
        tf = LocalTime.now(); 
        ti = LocalTime.now();

        System.out.println(redTime);
        while(tf.getSecond()-ti.getSecond()<redTime){
            System.out.println(" PORAQUIPASA LA WEAAAA");
            int dif2=tf.getSecond()-ti.getSecond();
            turnRedLightON();
            System.out.println(dif2 + " [s]   2");
            tf=LocalTime.now();
        }
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

    public void setredTime(int redT) {
        redTime=redT;
    }

    public static int getYellowLightTime() {
        return yellowTime;
    }

    //public String toString() {}
}