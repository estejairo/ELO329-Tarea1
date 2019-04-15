import java.util.Scanner;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class SimuladorEntradas implements ActionListener {
    DetectorRequerimiento sensorInductivo;
    DetectorRequerimiento botonMata;
    DetectorRequerimiento botonPlaceres;
    Scanner filename;
    //Scanner entrada;
    String linea;

    public SimuladorEntradas(DetectorRequerimiento sI, DetectorRequerimiento bm,DetectorRequerimiento bp  ,Scanner flnm) {
        sensorInductivo = sI;
        filename = flnm;
	botonMata = bm;
	botonPlaceres = bp;
    }


    public void actionPerformed(ActionEvent event) {
        if (filename.hasNextLine()) {
            linea = filename.nextLine();
	    //placeres - mata - inductivo
	    String[] seq = linea.split(",");
	    if((seq[0].compareTo("1"))==0){//prender Sem. mata}
	    if((seq[1].compareTo("1"))==0){//prender Sem. mata}
	    if((seq[2].compareTo("1"))==0){sensorInductivo.setOn()}

	    
// 		  if ((linea.compareTo("1"))==0){   
//                sensorInductivo.setOn();
//            }
        }
        else {
            filename.close();
            System.exit(0);
        }
        
    }
}
