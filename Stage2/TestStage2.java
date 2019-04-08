import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class TestStage2{
    public static void main(String args[])throws FileNotFoundException{
        String filename = args[0];
        int greenTime = 5;
        int blinkingTime= 10;

        File file = new File(filename);
        try (Scanner entrada = new Scanner(file);){
            SemaforoP semaforop = new SemaforoP(greenTime, blinkingTime);
            DetectorRequerimiento boton = new DetectorRequerimiento();
            ActionListener entradas = new SimuladorEntradas(boton, entrada);
            Controlador ctrl = new Controlador(boton, semaforop);
            //TIMER
            Timer t = new Timer(1000, entradas);
            t.start();
            ctrl.manageTraffic();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }

    }
}