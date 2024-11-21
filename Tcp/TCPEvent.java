package Tcp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Fsm.Event;

public class TCPEvent extends Fsm.Event {
    public static final String PASSIVE = "PASSIVE";
    public static final String ACTIVE = "ACTIVE";
    public static final String SYN = "SYN";
    public static final String SYNACK = "SYNACK";
    public static final String ACK = "ACK";
    public static final String RDATA = "RDATA";
    public static final String SDATA = "SDATA";
    public static final String FIN = "FIN";
    public static final String CLOSE = "CLOSE";
    public static final String TIMEOUT = "TIMEOUT";

    public static final Set<String> VALID_EVENT_SET = new HashSet<>(
        Arrays.asList(
            PASSIVE,
            ACTIVE,
            SYN,
            SYNACK,
            ACK,
            RDATA,
            SDATA,
            FIN,
            CLOSE,
            TIMEOUT
        )
    );    

    public TCPEvent(String name){
        super(name);
    }

    
}
