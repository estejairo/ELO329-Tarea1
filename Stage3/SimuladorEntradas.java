public class SimuladorEntradas implements ActionListener{
    DetectorDeRequerimiento sensorInductivo;
    String filename;
    public SimuladorEntradas(DetectorDeRequerimiento sI, String flnm) {
        sensorInductivo = sI;
        filename = flnm;
    }
    public void actionPerformed(ActionEvent event) {
        sensorInductivo.setOn();
    }
}