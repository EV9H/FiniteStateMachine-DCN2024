package Tcp;
import Fsm.*;

public class TCPTransition extends Fsm.Transition{
    public TCPTransition(TCPState cs, TCPEvent evt, TCPState ns, TCPAction act){
        super(cs,evt,ns,act);
    }
}
