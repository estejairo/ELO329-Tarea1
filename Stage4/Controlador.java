///////////////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa el controlador encargado de operar
//  las luces del todos los semaforos. Contiene su constructor y un metodo
//  manageTraffic() encargado de operar concretamente las luces y estados
//  de la interseccion. Se basa en el tiempo de operacion de los semaforos
//  de 3 luces presentes en matta y el para presente en placeres.
//  Se incluyen reglas especiales de funcionamiento, pensando en el orden,
//  sincronización y justica en los pasos de autos y peatones, evitando que
//  las solicitudes de cruce cambien bruscamente los semaforos, o que un paso
//  sea bloqueado durante mas de un ciclo.
//  Por ejemplo, no se permite que un peaton cruce si ya paso mas del 80% de
//  tiempo del semaforo vehicular correspondiente a ese cruce. Es decir, si
//  el semaforo vehicular de Matta lleva en rojo 8 de 10 segundos, el peaton
//  que quiera cruzar por Matta será postpuesto hacia el siguiente ciclo, ya
//  que se considera que el tiempo sería insuficiente para cruzar de manera 
//  segura.
//  Por otro lado, si un peaton quiere cruzar por la otra calle, el tiempo
//  del semaforo puede reducirse a maximo la mitad del tiempo para así acelerar
//  la oportunidad de cruzar. 
//  Por ejemplo, si un peaton ahora quisiera cruzar Av. Placeres,
//  y el semaforo vehicular de Placeres lleva en verde menos de 5 de sus 10 segundos totales, 
//  el peaton tendra que esperar. Una vez que hayan pasado los 5 segundos
//  comenzará el cambio de semaforo para que el peaton pueda cruzar placeres,
//  poniendo Placeres en rojo y Matta en verde. Asi, habra esperado la mitad
//  del tiempo, gracias a la existencia del boton peatonal, sin pasar a llevar
//  la oportunidad de cruzar de los autos.
//  Para el caso del semaforo de giro, este sera activado despues de un ciclo normal
//  del semaforo de placeres, poniendo en rojo todos los cruces peatonales, el
//  semaforo vehicular de matta y el semaforo vehicular en direccion desde
//  Valparaiso a Viña del Mar. Los autos desde Viña del Mar hacia Valparaiso podran
//  seguir circulando.
//  Otra particularidad del codigo es que para lograr la sicronizacion se necesita
//  que al menos ambos semaforos de Av. Placeres tengan el mismo tiempo en verde,
//  y que ademas los tiempos de parpadeo de todos los semaforos sean iguales a los tiempos
//  en amarillo de todos los semaforos.
//
///////////////////////////////////////////////////////////////////////////

import javax.swing.*;

public class Controlador extends Thread{
    SemaforoDeGiro semg;
    DetectorRequerimiento sensorInductivo, botonmata,botonplaceres;
    SemaforoP semp_plac,semp_mata;
    Semaforo3 sem_plac_valpo,sem_plac_vina,sem_mata;

    //Variables de tiempo
    int currentGreenTime = 1;
    int currentYellowTime = 1;

    //Estado del requerimiento;
    boolean serving_botonmata = false;
    boolean serving_botonplaceres = false;
    boolean serving_sensorInductivo = false;

    public Controlador(Semaforo3 semaforos3[],DetectorRequerimiento botones[], DetectorRequerimiento sI,SemaforoP semaforosP[], SemaforoDeGiro s){
        //Detectores de requerimiento
        sensorInductivo = sI;
        botonmata = botones[0];
        botonplaceres = botones[1];

        //Semaforos peatonales
        semp_mata = semaforosP[0];
        semp_plac = semaforosP[1];

        //Semaforos vehiculares
        sem_mata = semaforos3[0];
        sem_plac_valpo = semaforos3[1];
        sem_plac_vina = semaforos3[2];
        semg = s;
 
    }

    public void manageTraffic(){
        int counter = 0; //contador para guardar el tiempo transcurrido
        System.out.println("time\tsemp_p\tsemp_m\tsemg\ts_pvaln\ts_pvin\ts_mat");
        while(true){
            //Verde y amarillo de placeres (respecto al semaforo en direccion desde valparaiso hacia viña)
            if((currentGreenTime < sem_plac_vina.greenTime)&&(sem_plac_vina.green)){ //Si esta en verde y aun queda tiempo
                if ((botonmata.isOn())&&(currentGreenTime<(sem_plac_vina.greenTime*0.8))){ //logica para negar el paso si queda poco tiempo en verde
                    //se enciende el semaforo peatonal en caso de ser permitido
                    botonmata.setOff();
                    serving_botonmata = true; //Se marca como atendido
                    semp_mata.turnGreenLightOn();
                    currentGreenTime += 1;
                }
                else if ((botonplaceres.isOn())&&(currentGreenTime>(sem_plac_vina.greenTime*0.5))){ 
                    //Si alguien quiere cruazar por la otra calle y ya transcurrio el tiempo por default para permitir el cambio de luces
                    currentGreenTime = sem_plac_vina.greenTime;//Se pasa a la siguiente etapa
                }
                else{
                    currentGreenTime += 1;
                }
            }
            else if ((currentGreenTime == sem_plac_vina.greenTime)&&(sem_plac_vina.green)){ //Cambio a amarillo
                    sem_plac_vina.turnYellowLightON();
                    semp_mata.turnGreenLightOff();
                    if (sensorInductivo.isOn()){ //Solo en esta etapa se puede atender una solicitud de sensor inductivo!
                        serving_sensorInductivo = true; //Se atiende el requerimiento
                        currentGreenTime = 1;
                    }
                    else{
                        sem_plac_valpo.turnYellowLightON();
                        currentGreenTime = 1;  
                    }   
            }
            else if ((currentYellowTime < sem_plac_vina.yellowTime)&&(sem_plac_vina.yellow)){ //Estado en amarillo para semaforo direccion a viña
                if (serving_botonmata){ //Si se esta atendiendo el semaforo peatonal de mata, comienza el blink
                    if (semp_mata.green){
                        semp_mata.turnGreenLightOff();
                    }
                    else{
                        semp_mata.turnGreenLightOn();  
                    }
                }
                currentYellowTime += 1;
            }
            else if ((currentYellowTime == sem_plac_vina.yellowTime)&&(sem_plac_vina.yellow)){ //Cambio de amarillo a rojo (a verde del semaforo complementario)
                if (botonplaceres.isOn()&&(!serving_sensorInductivo)){ //Si se esta atendiendo el sensor, se postpone al peaton. Sino, se atiende
                    botonplaceres.setOff();
                    serving_botonplaceres = true;
                    semp_plac.turnGreenLightOn();
                }
                semp_mata.turnRedLightOn(); //se verifica que este apagado el semaforo peatonal de matta
                serving_botonmata = false; //se termina de atender
                sem_plac_vina.turnRedLightON();

                if (serving_sensorInductivo){ //Si se esta atendiendo al sensor inductivo, aqui comienza a darse su luz verde y se ajustan los demas semaforos para permitirlo
                    semg.turnGreenLightOn();
                    currentYellowTime = 1;
                }
                else{
                    sem_mata.turnGreenLightON();
                    sem_plac_valpo.turnRedLightON();
                    currentYellowTime = 1;
                }
            }
            /////////////////////////////////////////////////////////////

            //Verde y amarillo de luz de giro (su amarillo se sicroniza con el semaforo desde viña a valparaiso)
            //Estos estados son excepcionales para atender la luz de giro
            else if((currentGreenTime < semg.greenTimeP)&&(semg.green)){
                currentGreenTime += 1;
            }
            else if ((currentGreenTime == semg.greenTimeP)&&(semg.green)){
                semg.turnGreenLightOff();
                sem_plac_valpo.turnYellowLightON();
                currentGreenTime = semg.greenTimeP+1;
            }
            else if ((currentYellowTime < sem_plac_valpo.yellowTime)&&(sem_plac_valpo.yellow)&&(serving_sensorInductivo)){
                if (semg.green){ //parpadeo
                    semg.turnGreenLightOff();
                }
                else{
                    semg.turnGreenLightOn();
                }
                currentYellowTime += 1;
            }
            else if ((currentYellowTime == sem_plac_valpo.yellowTime)&&(sem_plac_valpo.yellow)&&(serving_sensorInductivo)){
                if (botonplaceres.isOn()&&(!serving_sensorInductivo)){
                    botonplaceres.setOff();
                    serving_botonplaceres = true;
                    semp_plac.turnGreenLightOn();
                }//Regresa al ciclo normal Matta-Placeres
                sem_mata.turnGreenLightON();
                sem_plac_valpo.turnRedLightON();
                semg.turnGreenLightOff();
                serving_sensorInductivo = false;
                currentGreenTime = 1;
                currentYellowTime = 1;                
            }
            /////////////////////////////////////////////////////////////

            //Verde y amarillo de matta
            //Es similar al comportamiento de verde y amarillo para el semaforo vehicular direccion de valparaiso a viña.
            //Son casi complementarios. El rojo de uno es el tiempo de verde+amarillo del otro
            else if((currentGreenTime < sem_mata.greenTime)&&(sem_mata.green)){
                if (botonplaceres.isOn()){
                    botonplaceres.setOff();
                    serving_botonplaceres = true;
                    semp_plac.turnGreenLightOn();
                    currentGreenTime += 1;
                }
                else if ((botonmata.isOn())&&(currentGreenTime>(sem_mata.greenTime*0.5))){
                    currentGreenTime = sem_mata.greenTime;
                }
                else{
                    currentGreenTime += 1;
                }
            }
            else if ((currentGreenTime == sem_mata.greenTime)&&(sem_mata.green)){
                sem_mata.turnYellowLightON();
                semp_plac.turnGreenLightOff();
                currentGreenTime = 1;
            }
            else if ((currentYellowTime < sem_mata.yellowTime)&&(sem_mata.yellow)){
                if (serving_botonplaceres){
                    if (semp_plac.green){
                        semp_plac.turnGreenLightOff();
                    }
                    else{
                        semp_plac.turnGreenLightOn();
                    }
                }
                currentYellowTime += 1;
            }
            else if ((currentYellowTime == sem_mata.yellowTime)&&(sem_mata.yellow)){
                if (botonmata.isOn()){
                    botonmata.setOff();
                    serving_botonmata = true;
                    semp_mata.turnGreenLightOn();
                }
                semp_plac.turnRedLightOn();
                serving_botonplaceres= false;
                sem_mata.turnRedLightON();
                sem_plac_valpo.turnGreenLightON();
                sem_plac_vina.turnGreenLightON();
                currentYellowTime = 1;
            }
            else{
                System.out.println("Caimos en un estado fantasma :C"); //debug
            }
            //Se imprime la salida
            System.out.println(counter+"\t"+semp_plac+"\t"+semp_mata+"\t"+semg+"\t"+sem_plac_valpo+"\t"+sem_plac_vina+"\t"+sem_mata+"\t");
            //Se repite cada un segundo y se lleva cuenta del tiempo transcurrido
            try{
                Thread.sleep(1000); //Si no hay requerimiento, se espera un segundo
                counter = counter + 1;  //Se agrega el tiempo transcurrido al contador
            } catch(InterruptedException e){
                System.out.println(e);
            }            
        }
    }
}