//////////////////////////////////////////////////////////////////////////////////////////
//
//  El siguiente codigo implementa un detector de requerimiento, que puede representar
//  a un boton o un sensor. El detector tiene un estado y puede ser seteado en on
//  en caso de existir un requerimiento. Al momento de atender el requerimiento, el estado
//  debe pasar a off.
//
//////////////////////////////////////////////////////////////////////////////////////////

public class DetectorRequerimiento{
    boolean state;  //Estado del detector
    
    //Entrega el estado del detector
    public boolean isOn(){
        return state;
    }

    //Activa el estado On en el detector, para cuando hay un requerimiento
    public void setOn(){
	state = true;

    }

    //Pone al detector en estado Off, cuando un requerimiento ya fue atendido
    public void setOff(){
	state = false;

    }

    // Permite imprimir el estado del detector con metodos print() del sistema
    public String toString(){
    	if(state){
		return ("1");
        }
        else{
            return("0");
        }
    }

}
