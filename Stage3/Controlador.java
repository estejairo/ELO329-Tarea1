public class Controlador{
    SemaforoDeGiro semg;
    DetectorRequerimiento sensorInductivo;
    public Controlador(DetectorRequerimiento sI, SemaforoDeGiro s){
        semg = sI;
        sensorInductivo = s;
    }
    public manageTraffic extends Thread(){ 
        while (True) {
            if (sensorInductivo.isOn()){
                semg.turnGreenLightOn();
                 try{Thread.sleep(semg.getGreenLightTime(););}catch(InterruptedException e){System.out.println(e);}
                for (int i=0; i<(semg.getBlinkingTime()/2); i++){
                    semg.turnGreenLightOff();
                    try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
                    semg.getBlinkingTime();
            
                }
                semg.turnGreenLightOff();
            }
            try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
        }
        
    }
}

/* 




semg.turnGreenLightOff()
semg.getBlinkingTime()

*/