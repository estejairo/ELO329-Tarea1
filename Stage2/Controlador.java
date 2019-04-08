import javax.swing.*;

public class Controlador extends Thread{
    SemaforoP semp;
    DetectorRequerimiento boton;

    public Controlador(DetectorRequerimiento btn, SemaforoP smp) {
        boton = btn;
        semp = smp;
    }
    public void manageTraffic(){
        int counter = 0;
        while (true) {
            if (boton.isON()){
                boton.setOff();
                semp.turnGreenLightOn();
                System.out.println(counter+"\t"+semp);
                try{
                    Thread.sleep(semp.getGreenLightTime()*1000);
                    counter = counter + semp.getGreenLightTime();
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                for (int i=0; i<(semp.getBlinkingTime()/2); i++){
                    semp.turnGreenLightOff();
                    System.out.println(counter+"\t"+semp);
                    try{
                        Thread.sleep(1000);
                        counter+=1;
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }
                    semp.turnGreenLightOn();
                    System.out.println(counter+"\t"+semp);
                    try{
                        Thread.sleep(1000);
                        counter+=1;
                    }
                    catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
                semp.turnGreenLightOff();
                semp.turnRedLightOn();
                System.out.println(counter+"\t"+semp);
            }
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.println(counter+"\t"+semp);
            counter = counter + 1; 
            semp.turnRedLightOn();
        }   
    }
}