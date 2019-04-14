/////////////////////////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa el comportamiento de un ActionListener,
//  leyendo una nueva linea del archivo que simula las entradas cada vez que
//  se gatilla un evento.
//  El objeto recibe un detector de requerimiento y el texto procesado por Scanner.
//
/////////////////////////////////////////////////////////////////////////////////////


import java.util.Scanner;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class SimuladorEntradas implements ActionListener {
    DetectorRequerimiento sensorInductivo;
    Scanner filename; //Nombre del archivo procesado por Scanner
    String linea;   //Linea del archivo

    //Constructor. Recibe un detector y el objeto tipo Scanner con el texto
    public SimuladorEntradas(DetectorRequerimiento sI, Scanner flnm) {
        sensorInductivo = sI;
        filename = flnm;
    }

    //Metodo que implementa la accion a realizar cuando se gatilla un evento
    public void actionPerformed(ActionEvent event) {
        if (filename.hasNextLine()) {   //Su hay una linea disponible para leer,
            linea = filename.nextLine();//Se le asigna esa nueva linea a la variable.
            if ((linea.compareTo("1"))==0){   //Si es un 1, se enciende el detector.             
                sensorInductivo.setOn();        
            }   //Si no hay "1" en la linea, no se hace nada
        }
        else {  //Si llega al final del archivo
            filename.close();   //Se cierrra el Scanner
            System.exit(0);     //Se sale del programa
        }
        
    }
}