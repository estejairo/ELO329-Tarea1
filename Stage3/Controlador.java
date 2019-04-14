///////////////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa el controlador encargado de operar
//  las luces del semaforo de giro. Contiene su constructor y un metodo
//  manageTraffic() encargado de operar concretamente las luces y estados
//  del semaforo.
//
///////////////////////////////////////////////////////////////////////////


public class Controlador extends Thread{
    SemaforoDeGiro semg; //semaforo de giro
    DetectorRequerimiento sensorInductivo; //Detector

    //Constructor, recibe un semaforo y un detector
    public Controlador(DetectorRequerimiento sI, SemaforoDeGiro s){
        sensorInductivo = sI;
        semg = s;
    }

    //Metodo que opera las luces del semaforo segun los requerimientos del sensor
    public void manageTraffic(){
        int counter = 0;    //Variable lleva el conteo del tiempo transcurrido
        while (true) {  //Se opera indefinidamente
            if (sensorInductivo.isOn()){   // Si hay un requerimiento, se opera el semaforo
                sensorInductivo.setOff();   // Al comenzar a atenderlo, se apaga el requerimiento
                semg.turnGreenLightOn();    //Se enciende la luz verde (lo que era requerido)
                System.out.println(counter+"\t"+semg); //Se espera el tiempo correspondiente
                try{
                    Thread.sleep(semg.getGreenLightTime()*1000);    //Se desactiva el proceso durante el tiempo en verde
                    counter = counter + semg.getGreenLightTime();   // Se agrega el tiempo transcurrido al contador
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                //Comienza el parpadeo. Se enciende y apaga arbitrariamente cada 1 segundo.
                //El ciclo dura la mitad del tiempo de parpadeo, ya que en cada ciclo está un segundo
                //encendido y otro segundo apagado.
                for (int i=0; i<(semg.getBlinkingTime()/2); i++){   //Comienza el parpadeo, l
                    semg.turnGreenLightOff();   //Se apaga la luz verde
                    System.out.println(counter+"\t"+semg);  //Imprime el estado del semaforo
                    try{
                        Thread.sleep(1000); //Se espera 1 segundo
                        counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                    semg.turnGreenLightOn();    //Se enciende la luz verde
                    System.out.println(counter+"\t"+semg);   //Imprime el estado del semaforo
                    try{
                        Thread.sleep(1000);//Se espera 1 segundo
                        counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
                    } catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
                /*Se apaga finalmente la luz, dando por finalizada 
                la atencion al requerimiento*/
                semg.turnGreenLightOff(); 
                System.out.println(counter+"\t"+semg);//Se imprime el estado del semaforo
            }
            try{
                Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
                counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
            } catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.println(counter+"\t"+semg); //Se imprime el estado actual del semaforo
        }
        
    }
}