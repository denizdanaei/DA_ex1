import java.util.List;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Process extends UnicastRemoteObject implements SchiperEggliSandoz_RMI{
    
	private static final long serialVersionUID = 1L;
    public int id;
    public VectorClock clock;
    public HistoryList history;
    public List<Message> msgBuffer;

    public Process(int id, int numProcesses) throws RemoteException {
        super();
        this.id = id;
        this.clock = new VectorClock(id, numProcesses);
        this.history = new HistoryList();
        this.msgBuffer = new ArrayList<Message>();
        
        try{
        	// Binding the remote object (stub) in the local registry
        	Registry registry = LocateRegistry.getRegistry();

        	registry.rebind("SchiperEggliSandoz-" + id, this);
        	System.err.println("Process " + id + " ready");
        } catch (Exception e) {
        	System.err.println("Server exception: " + e.toString());
        	e.printStackTrace();
     }

    }

    public synchronized void onSendEvent(int dst, int delay) throws MalformedURLException, RemoteException, NotBoundException {

        Message m = new Message(id, dst);
        
        
        m.addHistory(this.history);
        clock.tick();                   // Increase local clock
        m.addTimestamp(clock);          // Add timestamp and history to message
        printState();
        SchiperEggliSandoz_RMI dest = (SchiperEggliSandoz_RMI) Naming.lookup(dst + "-" + m.src);

        history.add(m.dst, clock);      // Update history list
        //add or update(if there is an item with same id, update)
        System.out.println("P"+id+" SENT to P"+ m.dst);
        printState();

        System.out.println("Sending - ");

        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            dest.receive(m);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                },
                delay
        );
    }

    public synchronized void receive(Message m) {
        
        if (!HistoryList.deliveryTest(id, clock, m)) {
            
            System.out.println(m.src +" to " +m.dst +"cannot be delivered");
             
            msgBuffer.add(m);               // Push message to msgBuffer is delivery test failed
            return;
        }

        onDeliverEvent(m);        
        System.out.println(m.src +" to " +m.dst +"will now be delivered");
        // System.out.println("P"+id+" Delivered");
        printState();

        if (!msgBuffer.isEmpty()) {
            receive(msgBuffer.remove(0));    // Pop message from msgBuffer
        }
    }

    private synchronized void onDeliverEvent(Message m) {
        clock.tick();
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
        // System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dst+" "+ VectorClock.toString(vectorClk));
        //deleting outdated history items
    
    }

    /*public boolean deliveryTest(Message m) {

        if(HistoryList.historycheck(Process, m))
            {return false;}
        // TODO
        //iterate through history
        //if history item with dest. id
            //check the timestamp
            //if dest. timestamp behind the msg timestamp
                //reject
            //else
                //accept
        return true;
    }
    */
    // Debugging helpers
    private void printState() {
        System.out.println("clock: "+clock.toString());
        System.out.println("history: "+history.toString()+"\n");
    }
}
