//////////////////////////////////////////////////////////////////////////////////
//                                                                              //
//  El siguiente codigo permite probar el semaforo de 3 luces.                  //
//  Para utilizarlo, se debe ejecutar entregando en primer lugar el tiempo      //
//  en segundos que se operara el semaforo, y luego se debe indicar el          //
//  el tiempo de encendido para la luz roja.                                    //
//  Ejemplo: java TestStage1 30 5                                               //
//  Donde 30 indica una operacion de 30 segundos, y el 5 indica una luz roja    //
//  de 5 segundos de operacion.                                                 //
//                                                                              //
//////////////////////////////////////////////////////////////////////////////////

// Clase principal
public class TestStage1{

    // Main
    public static void main(String args[]){
        int greenTime = 5; //Tiempo en verde, arbitrario
        int N = Integer.parseInt(args[0]); //tiempo en segundos ingresado, trasnformado a entero
        Semaforo3 sem = new Semaforo3(greenTime); //Construccion del semaforo
        sem.redTime = Integer.parseInt(args[1]); //Seteo del tiempo de encendido para el rojo
        
        //Variables auxiliares para contar el tiempo transcurrido en cada luz
        int currentRedTime = 1;
        int currentYellowTime = 1;
        int currentGreenTime = 1;

        //Configuracion del estado inicial del semaforo
        sem.turnRedLightON();

        //Ciclo de N segundos de duracion (aprox)
        for (int i=0; i<=N; i++){
            // Logica para encender las luces
            if ((currentRedTime < sem.redTime)&&(sem.red)){ //verifica si esta en rojo y aun queda tiempo
                currentRedTime+=1;
                
            }
            else if ((currentRedTime == sem.redTime)&&(sem.red)){ //Verifica si esta en rojo y debe cambiar de luz
                sem.turnYellowLightON();
                currentRedTime = 1;
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            else if ((currentYellowTime < sem.yellowTime)&&(sem.yellow)){ //verifica si esta en amarillo y aun queda tiempo
                currentYellowTime+=1;
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            else if ((currentYellowTime == sem.yellowTime)&&(sem.yellow)){ //Verifica si esta en rojo y debe cambiar de luz
                sem.turnGreenLightON();
                currentYellowTime = 1;
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            else if ((currentGreenTime < sem.greenTime)&&(sem.green)){ //verifica si esta en verde y aun queda tiempo
                currentGreenTime+=1;
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            else if ((currentGreenTime == sem.greenTime)&&(sem.green)){ //Verifica si esta en rojo y debe cambiar de luz
                sem.turnRedLightON();
                currentGreenTime = 1;
            }

            System.out.println(i + "\t" + sem); //Estado del semaforo cada segundo

            //Esperar 1 segundo
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e){
                System.out.println(e);
            }
        }
    }
}