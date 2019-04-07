import java.util.Scanner;
import java.io.File;

public class SimuladorEntradas implements ActionListener{
    DetectorDeRequerimiento sensorInductivo;
    String filename;


    File file = new File(filename);
    Scanner entrada = new Scanner(file);
    String linea;

    public SimuladorEntradas(DetectorDeRequerimiento sI, String flnm) {
        sensorInductivo = sI;
        filename = flnm;
    }
    public void actionPerformed(ActionEvent event) {
        sensorInductivo.setOn();
        if (entrada.hasNextLine()) {
            linea = entrada.nextLine();
            if (linea=="1"){
                sensorInductivo.setOn();
            }
        }
        else {
            entrada.close();
            System.exit(0);
        }
        
    }
}