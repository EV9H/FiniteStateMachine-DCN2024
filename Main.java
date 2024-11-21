import Tcp.*;

import java.util.Scanner;

// Mealy Machine (FSM generate an ouput for each transition)
public class Main {
    public static void main(String[] args) {

        // Initialize States

        TCPState CLOSED = new TCPState("CLOSED"); 
        TCPState LISTEN = new TCPState("LISTEN");
        TCPState SYN_SENT = new TCPState("SYN_SENT");
        TCPState ESTABLISHED = new TCPState("ESTABLISHED");
        TCPState SYN_RCVD = new TCPState("SYN_RCVD");
        TCPState FIN_WAIT_1 = new TCPState("FIN_WAIT_1");
        TCPState FIN_WAIT_2 = new TCPState("FIN_WAIT_2");
        TCPState CLOSING = new TCPState("CLOSING");
        TCPState TIME_WAIT = new TCPState("TIME_WAIT");
        TCPState LAST_ACK = new TCPState("LAST_ACK");
        TCPState CLOSE_WAIT = new TCPState("CLOSE_WAIT");

        // Initialize Events 
        TCPEvent PASSIVE = new TCPEvent("PASSIVE");
        TCPEvent ACTIVE = new TCPEvent("ACTIVE");
        TCPEvent SYN = new TCPEvent("SYN");
        TCPEvent SYNACK = new TCPEvent("SYNACK");
        TCPEvent ACK = new TCPEvent("ACK");
        TCPEvent RDATA = new TCPEvent("RDATA");
        TCPEvent SDATA = new TCPEvent("SDATA");
        TCPEvent FIN = new TCPEvent("FIN");
        TCPEvent CLOSE = new TCPEvent("CLOSE");
        TCPEvent TIMEOUT = new TCPEvent("TIMEOUT");


        // Initialize actions 
        TCPAction ACTION = new TCPAction();


        // Initialize FSM
        Fsm.FSM TCPFSM = new Fsm.FSM("TCP-Machine", CLOSED);
        // TCPFSM.traceOn();


        // add Transitions 
        try {
            TCPFSM.addTransition( new TCPTransition(CLOSED, ACTIVE, SYN_SENT, ACTION));
            TCPFSM.addTransition( new TCPTransition(CLOSED, PASSIVE, LISTEN, ACTION));
            TCPFSM.addTransition( new TCPTransition(LISTEN, CLOSE, CLOSED, ACTION));
            TCPFSM.addTransition( new TCPTransition(LISTEN, SYN, SYN_RCVD, ACTION));
            TCPFSM.addTransition( new TCPTransition(SYN_SENT, CLOSE, CLOSED, ACTION));
            TCPFSM.addTransition( new TCPTransition(SYN_SENT, SYN, SYN_RCVD, ACTION));
            TCPFSM.addTransition( new TCPTransition(SYN_SENT, SYNACK, ESTABLISHED, ACTION));
            TCPFSM.addTransition( new TCPTransition(SYN_RCVD, ACK, ESTABLISHED, ACTION));
            TCPFSM.addTransition( new TCPTransition(SYN_RCVD, CLOSE, FIN_WAIT_1, ACTION));
            TCPFSM.addTransition( new TCPTransition(FIN_WAIT_1, ACK, FIN_WAIT_2, ACTION));
            TCPFSM.addTransition( new TCPTransition(FIN_WAIT_1, FIN, CLOSING, ACTION));
            TCPFSM.addTransition( new TCPTransition(CLOSING, ACK, TIME_WAIT, ACTION));
            TCPFSM.addTransition( new TCPTransition(FIN_WAIT_2, FIN, TIME_WAIT, ACTION));
            TCPFSM.addTransition( new TCPTransition(TIME_WAIT, TIMEOUT, CLOSED, ACTION));
            TCPFSM.addTransition( new TCPTransition(ESTABLISHED, CLOSE, FIN_WAIT_1, ACTION));
            TCPFSM.addTransition( new TCPTransition(ESTABLISHED, FIN, CLOSE_WAIT, ACTION));
            TCPFSM.addTransition( new TCPTransition(ESTABLISHED, RDATA, ESTABLISHED, ACTION));
            TCPFSM.addTransition( new TCPTransition(ESTABLISHED, SDATA, ESTABLISHED, ACTION));
            TCPFSM.addTransition( new TCPTransition(CLOSE_WAIT, CLOSE, LAST_ACK, ACTION));
            TCPFSM.addTransition( new TCPTransition(LAST_ACK, ACK, CLOSED, ACTION));
        }
        catch (Fsm.FsmException e){
            System.err.println("Error: " + e.getMessage());
        }

        
        Scanner sc = new Scanner(System.in);
        

        int count = 0;

        while(true){
            String line = sc.nextLine();
            Scanner tokenScanner = new Scanner(line);
            while (tokenScanner.hasNext()){
                String token = tokenScanner.next();

                if (Tcp.TCPEvent.VALID_EVENT_SET.contains(token)){
                    TCPEvent newEvent = new TCPEvent(token);
                    try{
                        TCPFSM.doEvent(newEvent);

                    }catch (Fsm.FsmException e){
                        System.err.println("Error: " + e.getMessage());
                    }
                }else{
                    System.err.println("Error: unexpected Event: " + token);
                }
            }
            tokenScanner.close();
        }
    }
}


// States


// Events


// Transitions


// Start State

