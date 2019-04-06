public class DetectorRequerimiento{
    boolean state;
    
    public boolean isON(){
        return state;
    }
    public void setOn(){
	state = true;

    }
    public void setOff(){
	state = false;

    }
    public String toString(){
    	if(state){
		return ("1");
	}
	else{
		return("0");
	}
    }

}
