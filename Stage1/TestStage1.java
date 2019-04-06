import java.io.*;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;

public class TestStage1{
    public static void main(String  args[]) throws IOException{
        int N = Integer.parseInt(args[0]);
        int redT = Integer.parseInt(args[1]);
        LocalTime ti = LocalTime.now();
        LocalTime tf = LocalTime.now();
        Semaforo3 sem1 = new Semaforo3(5);
        long telap = Duration.between(ti,tf).toMillis()/1000;
        long tselap;
        sem1.redTime=redT; 
        while(telap<N){
            System.out.println(telap);
            LocalTime tis = LocalTime.now();
            LocalTime tfs = LocalTime.now(); 
            //verde
            tselap = Duration.between(tis,tfs).toMillis()/1000;
            if(telap>N)break;
            while(tselap<sem1.getGreenTime()){
                tfs=LocalTime.now();
                tselap = Duration.between(tis,tfs).toMillis()/1000;
                tf=LocalTime.now();
                telap = Duration.between(ti,tf).toMillis()/1000;
                if(telap>=N)break;
                sem1.turnGreenLightON();
                //System.out.println(tselap+" "+ sem1.getGreenTime()+" " + telap);
                System.out.println( tselap + "[s] 0 Verde");

            }
            //amarillo
            if(telap>=N)break;
            tis = LocalTime.now();
            tfs = LocalTime.now(); 
            tselap = Duration.between(tis,tfs).toMillis()/1000;
            while(tselap<sem1.getYellowTime()){
                tfs=LocalTime.now();
                tf=LocalTime.now();
                tselap = Duration.between(tis,tfs).toMillis()/1000;
                telap = Duration.between(ti,tf).toMillis()/1000;
                //System.out.println(tselap+" "+ sem1.getYellowTime()+" "+ telap);
                if(telap>=N)break;
                sem1.turnYellowLightON();
                System.out.println(tselap + "[s]  1 Amarillo");
            }
            //Rojo
            if(telap>=N)break;
            tfs = LocalTime.now(); 
            tis = LocalTime.now();
            tselap = Duration.between(tis,tfs).toMillis()/1000;
            while(tselap<redT){
                tfs=LocalTime.now();
                tf=LocalTime.now();
                tselap = Duration.between(tis,tfs).toMillis()/1000;
                telap = Duration.between(ti,tf).toMillis()/1000;
                if(telap>=N)break;
                sem1.turnRedLightON();
                System.out.println(tselap + "[s]   2 Rojo");
                //System.out.println(tselap+" "+ redT+" "+ telap);
            }
            if(telap>=N)break;

        }
        System.out.println("Fin Programa tiempo total LOOP = "+telap);
    }
    // public long getTimeElapsed(LocalTime t_inicial, LocalTime t_final){
    //     tf=LocalTime.now();
    //     long telapx = Duration.between(ti,tf).toMillis()/1000;
    //     return telapx;
    // }
}