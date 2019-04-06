import java.io.*;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.Duration;
import java.util.Timer;
public class TestStage3{
    DetectorRequerimiento sI = new DetectorRequerimiento();
    String flnm = "000010000000000000000010100000000000000000100000000000000000000000000000000000000000001000000100000000000000000000000000100000000000000000000000001000000000"
    ActionListener entradas = new SimuladorEntradas(sI, String flnm);
    Timer t = new Timer(1000, entradas);
    t.start();
}