import java.util.List;
import java.util.ArrayList;

public class Process {
    
    public int id;
    public VectorClock clock;
    public List<HistoryItem> history;
    public List<Message> msgBuffer;

    public Process(int id, int numProcesses) {
        this.id = id;
        this.clock = new VectorClock(numProcesses);
        this.history = new ArrayList<HistoryItem>();
        this.msgBuffer = new ArrayList<Message>();
    }

    public void onSendEvent(Message m) {
        System.out.println("P"+id+" SEND to P"+ m.dst);
        clockTick();                                     // Increase local clock
        m.addMetadata(clock, history);                   // Add timestamp and history to message
        updateHistory(m.dst, clock);
        printState();
    }

    public void onReceiveEvent(Message m) {
        
        System.out.println("P"+id+" RECEIVE FROM P"+m.src);
        if (!deliveryTest(m)) {
            System.out.println("\tCAN'T DELIVER\n");
            msgBuffer.add(m);
            return;
        }
        onDeliverEvent(m);
        // printState();
        if (!msgBuffer.isEmpty()) {
            onReceiveEvent(msgBuffer.remove(0));    // Pop message from msgBuffer
        }
    }

    public void onDeliverEvent(Message m) {
        System.out.println("P"+id+" DELIVERY FROM P"+m.src+"\n");
        clockTick();
        clockUpdate(m.timestamp);
        
        // Copy message history to local history
        for (HistoryItem i : m.history) {
            updateHistory(i.id, i.timestamp);
        }

        //deleting outdated history items
    
    }

    private boolean deliveryTest(Message msg) {

        for (HistoryItem msgHistItem : msg.history) {
            if (this.id == msgHistItem.id) {
                System.out.println("HISTORY ENTRY DETECTED");
                if (VectorClock.isbehind(this.clock, msgHistItem.timestamp)) {  // TODO: convert to non-static function
                    return false;
                }
            }
        }
        return true;
    }

    private void updateHistory(int id, VectorClock timestamp) {
        for (HistoryItem item : history) {      // Check if entry with this PID already exists
            if (this.id == item.id) {
                System.out.println("HISTORY ENTRY COLLISION!");     // TODO implement correct thing here
                return;
            }
        }
        this.history.add(new HistoryItem(id, timestamp));
    }

    private void clockTick() {
        this.clock.increase(this.id - 1);
    }

    private void clockUpdate(VectorClock clock) {
        this.clock.setToMaximum(clock);
    }

    // Debugging helpers
    public void printState() {
        System.out.println("clock: "+clock.toString());
        for (HistoryItem i : history) {
            // System.out.println("P"+i.id+" "+i.timestamp.toString());
        }
        System.out.println();
    }
}
