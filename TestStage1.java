import java.io.*;
import java.util.Scanner;
import java.time.LocalTime;
public class TestStage1{
      public static void main(String  args[]) throws IOException{
          int N = Integer.parseInt(args[0]);
          int redT = Integer.parseInt(args[1]);
          LocalTime ti = LocalTime.now();
          LocalTime tf = LocalTime.now(); 
          while(tf.getSecond()-ti.getSecond()<N){
              Semaforo3 sem1 = new Semaforo3(5);
              sem1.setredTime(redT);
              tf=LocalTime.now();
          }
          System.out.println("Fin Programa");
    }
}