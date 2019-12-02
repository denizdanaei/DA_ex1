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
            System.out.println("CAN'T DELIVER\n");
            msgBuffer.add(m);
            return;
        }
        onDeliverEvent(m);
        printState();
        if (!msgBuffer.isEmpty()) {
            onReceiveEvent(msgBuffer.remove(0));    // Pop message from msgBuffer
        }
    }

    public void onDeliverEvent(Message m) {
        clockTick();
        clockUpdate(m.timestamp);
        System.out.println("P"+id+" DELIVERY FROM P"+m.src);
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
        // System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dst+" "+ VectorClock.toString(vectorClk));
        //deleting outdated history items
    
    }

    private void updateHistoryBuffer(){}

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


    private boolean deliveryTest(Message msg) {
        for (HistoryItem msgHistItem : msg.history) {
            if (this.id == msgHistItem.id) {    // Check for your ID inside message's history
                if (VectorClock.isbehind(this.clock, msgHistItem.timestamp)) {  // TODO: convert to non-static function
                    return false;
                }
            }
        }
        return true;
    }

    private void updateHistory(int id, VectorClock timestamp) {
        // TODO: check if entry for given id already exists and do magic
        this.history.add(new HistoryItem(id, timestamp));
    }

    private void clockUpdate(VectorClock clk) {
        // Clockwise maximum (per element, take max of the two)
        for (int i : this.clock)
    }

    private void clockTick() {
        this.clock.increase(this.id - 1);
    }

    // Debugging helpers
    public void printState() {
        System.out.println("clock: "+clock.toString());
        for (HistoryItem i : history) {
            System.out.println("P"+i.id+" "+i.timestamp.toString());
        }
        System.out.println();
    }
}
