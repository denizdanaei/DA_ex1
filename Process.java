import java.util.List;
import java.util.ArrayList;

public class Process {
    
    public int id;
    public VectorClock clock;
    public HistoryList history;
    public List<Message> msgBuffer;

    public Process(int id, int numProcesses) {
        this.id = id;
        this.clock = new VectorClock(id, numProcesses);
        this.history = new HistoryList();
        this.msgBuffer = new ArrayList<Message>();
    }

    public void onSendEvent(Message m) {
        clock.tick();                   // Increase local clock
        m.addTimestamp(clock);          // Add timestamp and history to message
        m.addHistory(this.history);
        history.add(m.dst, clock);      // Update history list

        System.out.println("P"+id+" SEND");
        printState();
    }

    public void onReceiveEvent(Message m) {
        
        if (!deliveryTest()) {
            msgBuffer.add(m);               // Push message to msgBuffer
            return;
        }
        System.out.println("P"+id+" RECEIVE");
        printState();
        onDeliverEvent(m);
        if (!msgBuffer.isEmpty()) {
            onReceiveEvent(msgBuffer.remove(0));    // Pop message from msgBuffer
        }
    }

    public void onDeliverEvent(Message m) {
        clock.tick();
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
        // System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dst+" "+ VectorClock.toString(vectorClk));
    }

    private void updateHistoryBuffer(){}

    public boolean deliveryTest() {
        // TODO
        return true;
    }
 
    // Debugging helpers
    public void printState() {
        System.out.println(clock.toString());
        System.out.println(history.toString()+"\n");
    }
}
