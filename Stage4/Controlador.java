import javax.swing.*;


public class Controlador extends Thread{
    SemaforoDeGiro semg;
    DetectorRequerimiento sensorInductivo;
    public Controlador(DetectorRequerimiento sI, SemaforoDeGiro s){
        sensorInductivo = sI;
        semg = s;
    }
    public void manageTraffic(){
        int counter = 0;
        while (true) {
            if (sensorInductivo.isOn()){
                sensorInductivo.setOff();
                semg.turnGreenLightOn();
                System.out.println(counter+"\t"+semg);
                try{
                    Thread.sleep(semg.getGreenLightTime()*1000);
                    counter = counter + semg.getGreenLightTime();
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                for (int i=0; i<(semg.getBlinkingTime()/2); i++){
                    semg.turnGreenLightOff();
                    System.out.println(counter+"\t"+semg);
                    try{
                        Thread.sleep(1000);
                        counter = counter + 1;
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                    semg.turnGreenLightOn();
                    System.out.println(counter+"\t"+semg);
                    try{
                        Thread.sleep(1000);
                        counter = counter + 1;
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
                semg.turnGreenLightOff();
                System.out.println(counter+"\t"+semg);
            }
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.println(counter+"\t"+semg);
            counter = counter + 1; 
        }
        
    }
}