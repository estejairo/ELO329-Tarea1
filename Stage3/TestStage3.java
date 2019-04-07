import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;


public class TestStage3{
    public static void main(String  args[]) throws FileNotFoundException{
        String filename = args[0];
        int greenTime = 30;
        int blinkingTime=10;
        

        File file = new File(filename);
        try(Scanner entrada = new Scanner(file);){
            SemaforoDeGiro semaforo = new SemaforoDeGiro(greenTime, blinkingTime);
            DetectorRequerimiento sensorInductivo = new DetectorRequerimiento();
            ActionListener entradas = new SimuladorEntradas(sensorInductivo, entrada);
            Controlador ctrl = new Controlador(sensorInductivo, semaforo);
       
            Timer t = new Timer(1000, entradas);
            t.start();
            ctrl.manageTraffic();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
             

        
    }
}