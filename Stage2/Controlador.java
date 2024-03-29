///////////////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa el controlador encargado de operar
//  las luces del semaforo peatonal. Contiene su constructor y un metodo
//  manageTraffic() encargado de operar concretamente las luces y estados
//  del semaforo.
//
//  ATENCION: Al cambiar de luz, el programa se va a dormir por el tiempo
//  correspondiente, por lo cual deja de imprimir en pantalla hasta despertar.
//  Cuando retoma la impresion por consola, aparece el tiempo actual transcurrido,
//  contando el tiempo que estubo dormido. No se engañe!
//
//  ATENCION 2: Con el fin de que se distinga la acción de parpadeo respecto a la luz
//  verde, se imprimira un espacio en blanco cuando la luz verde esta apagada: " ".
//
///////////////////////////////////////////////////////////////////////////


public class Controlador extends Thread{
    SemaforoP semp;//semaforo peatonal
    DetectorRequerimiento boton;//Detector

    //Constructor, recibe un semaforo y un detector
    public Controlador(DetectorRequerimiento btn, SemaforoP smp) {
        boton = btn;
        semp = smp;
    }

    //Metodo que opera las luces del semaforo segun los requerimientos del sensor
    public void manageTraffic(){
        int counter = 0;//Variable lleva el conteo del tiempo transcurrido
        while (true) {//Se opera indefinidamente
            if (boton.isOn()){// Si hay un requerimiento, se opera el semaforo
                boton.setOff();// Al comenzar a atenderlo, se apaga el requerimiento
                semp.turnGreenLightOn();//Se enciende la luz verde (lo que era requerido)
                System.out.println(counter+"\t"+semp);//Se imprime el tiempo y el estado
                try{
                    Thread.sleep(semp.getGreenLightTime()*1000); //Se desactiva el proceso durante el tiempo en verde
                    counter = counter + semp.getGreenLightTime();// Se agrega el tiempo transcurrido al contador
                } catch(InterruptedException e){
                    System.out.println(e);
                }
                //Comienza el parpadeo. Se enciende y apaga arbitrariamente cada 1 segundo.
                //El ciclo dura la mitad del tiempo de parpadeo, ya que en cada ciclo está un segundo
                //encendido y otro segundo apagado.
                for (int i=0; i<(semp.getBlinkingTime()/2); i++){//Comienza el parpadeo
                    semp.turnGreenLightOff();//Se apaga la luz verde
                    System.out.println(counter+"\t"+semp);//Imprime el estado del semaforo
                    try{
                        Thread.sleep(1000);//Se espera 1 segundo
                        counter+=1;//Se agrega el tiempo transcurrido al contador
                    }catch(InterruptedException e){
                        System.out.println(e);
                    }
                    semp.turnGreenLightOn(); //Se enciende la luz verde
                    System.out.println(counter+"\t"+semp);//Imprime el estado del semaforo
                    try{
                        Thread.sleep(1000);//Se espera 1 segundo
                        counter+=1;//Se agrega el tiempo transcurrido al contador
                    }
                    catch(InterruptedException e){
                        System.out.println(e);
                    }
                }
                /*Se apaga finalmente la luz verde y se enciende la roja, dando por finalizada 
                la atencion al requerimiento*/
                semp.turnGreenLightOff();
                semp.turnRedLightOn();
                System.out.println(counter+"\t"+semp);//Se imprime el estado del semaforo
            }
            try{
                Thread.sleep(1000);//Si no hay requerimiento, se espera un segundo
            } catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.println(counter+"\t"+semp); //Se imprime el estado actual del semaforoS
            counter = counter + 1; 
            semp.turnRedLightOn();
        }   
    }
}