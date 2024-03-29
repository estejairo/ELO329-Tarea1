import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class TestStage4{
    public static void main(String  args[]) throws FileNotFoundException{
        String filename = args[0];
        int greenTime = 12;
        int blinkingTime = 6;
        

        File file = new File(filename);
        try(Scanner entrada = new Scanner(file);){
            SemaforoP semp_mata = new SemaforoP(greenTime, blinkingTime);
            SemaforoP semp_plac = new SemaforoP(greenTime, blinkingTime);
            SemaforoP semaforosP[] = {semp_mata,semp_plac};
            Semaforo3 sem_mata= new Semaforo3(greenTime);
            Semaforo3 sem_valpo_vina= new Semaforo3(greenTime);
            Semaforo3 sem_vina_valpo= new Semaforo3(greenTime);
            Semaforo3 semaforos3[] = {sem_mata,sem_valpo_vina,sem_vina_valpo};
            SemaforoDeGiro sem_giro = new SemaforoDeGiro(greenTime, blinkingTime);
            DetectorRequerimiento sensorInductivo = new DetectorRequerimiento();
            DetectorRequerimiento boton_mata = new DetectorRequerimiento();
            DetectorRequerimiento boton_placeres = new DetectorRequerimiento();
            DetectorRequerimiento botones []={boton_mata,boton_placeres};
            ActionListener entradas = new SimuladorEntradas(boton_mata, boton_placeres, sensorInductivo, entrada);
            Controlador ctrl = new Controlador(semaforos3,botones,sensorInductivo,semaforosP,sem_giro);
       
            //Estado inicial
            semp_mata.turnRedLightOn();
            semp_plac.turnRedLightOn();
            sem_mata.turnRedLightON();
            sem_valpo_vina.turnGreenLightON();
            sem_vina_valpo.turnGreenLightON();
            sem_giro.turnGreenLightOff();
            sensorInductivo.setOff();
            boton_mata.setOff();
            boton_placeres.setOff();

            Timer t = new Timer(1000, entradas);
            t.start();
            ctrl.manageTraffic();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
             

        
    }
}