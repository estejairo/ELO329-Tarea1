import java.util.Scanner;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class SimuladorEntradas implements ActionListener{
    DetectorRequerimiento boton;
    Scanner filename;
    String linea;

    public SimuladorEntradas(DetectorRequerimiento boton, Scanner flnm) {
        this.boton=boton;
        filename=flnm;
    }
    public void actionPerformed(ActionEvent event) {
        if (filename.hasNextLine()) {
            linea = filename.nextLine();
            if ((linea.compareTo("1"))==0){                
                boton.setOn();
            }
        }
        else {
            filename.close();
            System.exit(0);
        }
        
    }
}