package Tcp;
import Fsm.*;

import java.util.Arrays;
import java.util.HashMap;

public class TCPAction extends Fsm.Action {
    
    public TCPAction(){
        super();
    }
    private static int RCOUNT = 0;
    private static int SCOUNT = 0;

    public void execute(Fsm.FSM fsm, Fsm.Event evt){
        
        String currStateName = fsm.currentState().getName();
        String eventName = evt.getName();
        if(currStateName.equals("CLOSED")){
            // RESET COUNTS
            RCOUNT = 0;
            SCOUNT = 0;
        }
        if ( !eventName.equals("RDATA")&& !eventName.equals("SDATA")){
            System.out.println(String.format("Event %s received, current State is %s", eventName, currStateName));
            
        }else if(eventName.equals("RDATA")){
            RCOUNT += 1;
            System.out.println("DATA received " + RCOUNT);
        }else if(eventName.equals("SDATA")){
            SCOUNT += 1;
            System.out.println("DATA sent " + SCOUNT);
        }
    }
}
