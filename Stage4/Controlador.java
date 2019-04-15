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

        while(true){

            //Verde y amarillo de placeres
            if((currentGreenTime < sem_plac_vina.greenTime)&&(sem_plac_vina.green)){
                if ((botonmata.isOn())&&(currentGreenTime<(sem_plac_vina.greenTime*0.8))){
                    botonmata.setOff();
                    serving_botonmata = true;
                    semp_mata.turnGreenLightOn();
                }
                else if ((botonplaceres.isOn())&&(currentGreenTime>(sem_plac_vina.greenTime*0.5))){
                    currentGreenTime = sem_plac_vina.greenTime;
                }
                else{
                    currentGreenTime += 1;
                }
            }
            else if ((currentGreenTime == sem_plac_vina.greenTime)&&(sem_plac_vina.green)){
                    serving_sensorInductivo = true;
                    sem_plac_valpo.turnYellowLightON();
                    semp_mata.turnGreenLightOff();
                    if (sensorInductivo.isOn()){
                        currentGreenTime = 1;
                    }
                    else{
                        sem_plac_vina.turnYellowLightON();
                        currentGreenTime = 1;  
                    }   
            }
            else if ((currentYellowTime < sem_plac_vina.yellowTime)&&(sem_plac_vina.yellow)){
                if (serving_botonmata){
                    if (semp_mata.green){
                        semp_mata.turnGreenLightOff();
                    }
                    else{
                        semp_mata.turnGreenLightOn();  
                    }
                }
                currentYellowTime += 1;
            }
            else if ((currentYellowTime == sem_plac_vina.yellowTime)&&(sem_plac_vina.yellow)){
                if (botonplaceres.isOn()){
                    botonplaceres.setOff();
                    serving_botonplaceres = true;
                    semp_plac.turnGreenLightOn();
                }
                semp_mata.turnRedLightOn();
                serving_botonmata = false;
                sem_plac_valpo.turnRedLightON();

                if (serving_sensorInductivo){
                    semg.turnGreenLightOn();
                    currentYellowTime = 1;
                }
                else{
                    sem_mata.turnGreenLightON();
                    sem_plac_vina.turnRedLightON();
                    currentYellowTime = 1;
                }
            }
            /////////////////////////////////////////////////////////////

            //Verde y amarillo de luz de giro
            else if((currentGreenTime < semg.greenTimeP)&&(semg.green)){
                currentGreenTime += 1;
            }
            else if ((currentGreenTime == semg.greenTimeP)&&(semg.green)){
                semg.turnGreenLightOff();
                sem_plac_vina.turnYellowLightON();
                currentGreenTime = 1;
            }
            else if ((currentYellowTime < sem_plac_valpo.yellowTime)&&(sem_plac_valpo.yellow)&&(serving_sensorInductivo)){
                if (semg.green){
                    semg.turnGreenLightOff();
                }
                else{
                    semg.turnGreenLightOn();
                }
                currentYellowTime += 1;
            }
            else if ((currentYellowTime == sem_plac_valpo.yellowTime)&&(sem_plac_valpo.yellow)&&(serving_sensorInductivo)){
                sem_mata.turnGreenLightON();
                sem_plac_vina.turnRedLightON();
                semg.turnGreenLightOff();
                currentYellowTime = 1;                
            }
            /////////////////////////////////////////////////////////////

            //Verde y amarillo de matta
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

            
            /*
            //Estado matta
            //al principio de este se puede atender peatonal matta
            sem_mata.turnGreenLightOn();
            sem_plac_valpo.turnRedLightON();
            sem_plac_vina.turnRedLightON();

            //Transicion matta placeres
            sem_mata.turnYellowLightON();
            
            //Estado placeres
            //al principio de este se puede atender peatonal matta
            sem_mata.turnRedLightON();
            sem_plac_valpo.turnGreenLightOn();
            sem_plac_vina.turnGreenLightOn();
            /*al final de este se puede atender el giro

            //Transicion placeres matta
            sem_plac_valpo.turnYellowLightON();
            sem_plac_vina.turnYellowLightON();

            //Transicion placeres giro
            sem_plac_valpo.turnYellowLightON();

            //Estado giro
            semg.turnGreenLightOn();

            //Transicion giro matta
            sem_plac_vina.turnYellowLightON();
            //semg blinking y termina off

            */
        }
    }
}