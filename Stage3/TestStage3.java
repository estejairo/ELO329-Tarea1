////////////////////////////////////////////////////////////////////////////////
// 
//  El siguiente codigo permite probar el semaforo de giro. Al ejecutarlo se
//  debe indicar la ruta del archivo que contiene las señales de prueba para
//  activar el sensor inductivo. Ej: java TestStage3 entradas.txt
//  Las entradas son procesadas mediante Scanner, y procesadas en paralelo al
//  metodo manageTraffic cada un segun con un ActionListener. El metodo
//  manageTraffic es del controlador y se encarga de operar el semaforo segun
//  los tiempos y las señales del sensor inductivo.
//
////////////////////////////////////////////////////////////////////////////////


import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class TestStage3{
    public static void main(String  args[]) throws FileNotFoundException{
        String filename = args[0]; //nombre del archivo a leer (incluyendo formato, idealmente .txt)
        int greenTime = 30; //Tiempo que dura la luz verde
        int blinkingTime = 2;   //Tiempo que se mantiene parpadeando antes de apagarse
        

        File file = new File(filename); //Se genera un archivo File que contenga al .txt
        try(Scanner entrada = new Scanner(file);){ //Se procesa el archivo mediante Scanner
            //Si se encuentra el archivo, se crean los objetos de semaforo, sensor, y controlador.
            //Ademas se genera un SimuladorEntradas para leer el archivo.
            SemaforoDeGiro semaforo = new SemaforoDeGiro(greenTime, blinkingTime);
            DetectorRequerimiento sensorInductivo = new DetectorRequerimiento();
            ActionListener entradas = new SimuladorEntradas(sensorInductivo, entrada);
            Controlador ctrl = new Controlador(sensorInductivo, semaforo);
            
            //El archivo se lee cada 1000 milisegundos segun lo definido en los metodos de SimuladorEntradas
            Timer t = new Timer(1000, entradas);
            t.start(); //Comienza la lectura en paralelo
            ctrl.manageTraffic(); //Se opera el semaforo, atento a las señales del sensor
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }        
    }
}