import java.lang.Object;
public class Controlador{
    SemaforoP semp;
    DetectorRequerimiento boton;
    public Controlador(DetectorRequerimiento boton, SemaforoP semp){
        this.boton=boton;
        this.semp=semp;
    }
    public void run(){
        try {
            Thread.sleep(1000);   
        }
        catch (Exception e) {

            //TODO: handle exception
        }
    }
    public manageTraffic()throws Exception{
        semp.turnRedLightOn();
    }
}