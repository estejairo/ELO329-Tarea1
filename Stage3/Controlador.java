public class Controlador{
    SemaforoDeGiro sensorInductivo;
    DetectorRequerimiento semg;
    public Controlador(DetectorRequerimiento sI, SemaforoDeGiro s){
        sensorInductivo = sI;
        semg = s;
    }
    public manageTraffic extends Thread(){ 
        while (True) {
            if (sensorInductivo.isOn()){
                sensorInductivo.setOff();
                semg.turnGreenLightOn();
                try{
                    Thread.sleep(semg.getGreenLightTime()*1000;);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                for (int i=0; i<(semg.getBlinkingTime()/2); i++){
                    semg.turnGreenLightOff();
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                    semg.turnGreenLightOn();
                    try{
                        Thread.sleep(1000);
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
                semg.turnGreenLightOff();
            }
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                System.out.println(e);
            }
        }
        
    }
}