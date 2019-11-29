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

        	registry.rebind(Integer.toString(id), this);
        	System.err.println("Process " + id + " ready");
        } catch (Exception e) {
        	System.err.println("Server exception: " + e.toString());
        	e.printStackTrace();
     }

    }

    public synchronized void onSendEvent(int dst, int delay) throws MalformedURLException, RemoteException, NotBoundException {
        
        System.out.println( "Sending from "+id +" to " +dst+ " with delay "+delay);
        
        clock.tick();// Increase local clock
        VectorClock tempclk= new VectorClock(id, 3);
        tempclk.setVector(clock);
        
        HistoryList tempHistory =new HistoryList();
        tempHistory.copyhistory(history);
        // m.print();

        SchiperEggliSandoz_RMI dest = (SchiperEggliSandoz_RMI) Naming.lookup(Integer.toString(dst));
                
        Message m = new Message(id, dst,tempclk, tempHistory);
        // m.print();
        history.additem(m.dst, tempclk);      // Update history list
        //add or update(if there is an item with same id, update)
        // printState();

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
        
        // System.out.println("\nRecieved Message at P"+m.dst+" from P"+m.src);
        m.print();

        if (!HistoryList.deliveryTest(id, clock, m.history)) {
            System.out.println(m.src +" to " +m.dst +" CANNOT be delivered. Message PUSHED to Buffer.");
            msgBuffer.add(m);         // Push message to msgBuffer is delivery test failed
            return;
        }
        onDeliverEvent(m);        
        if (!msgBuffer.isEmpty()) {
            receive(msgBuffer.remove(0));    // Pop message from msgBuffer
        }
    }

    private synchronized void onDeliverEvent(Message m) {
        System.out.println(m.src +" to " +m.dst +" will now be delivered");
        // m.print();
        clock.updateOnDelivery(m.timestamp);
        history.copyhistory(m.history);
        printState();
        //deleting outdated history items
    }

    // Debugging helpers
    private void printState() {
        System.out.println("P"+id+" clock: "+clock.toString() +"\nhistory: "+history.toString());
    }
}
