import java.util.Scanner;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class SimuladorEntradas implements ActionListener {
    DetectorRequerimiento sensorInductivo;
    Scanner filename;
    //Scanner entrada;
    String linea;

    public SimuladorEntradas(DetectorRequerimiento sI, Scanner flnm) {
        sensorInductivo = sI;
        filename = flnm;
    }


    public void actionPerformed(ActionEvent event) {
        if (filename.hasNextLine()) {
            linea = filename.nextLine();
            if ((linea.compareTo("1"))==0){                
                sensorInductivo.setOn();
            }
        }
        else {
            filename.close();
            System.exit(0);
        }
        
    }
}