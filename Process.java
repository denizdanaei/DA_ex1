import java.util.List;
import java.util.ArrayList;

public class Process {
    
    public int id;
    public VectorClock clock;
    public HistoryList history;
    public List<Message> msgBuffer;

    public Process(int id, int numProcesses) {
        this.id = id;
        this.clock = new VectorClock(numProcesses);
        this.history = new HistoryList();
        this.msgBuffer = new ArrayList<Message>();
    }

    public void onSendEvent(Message m) {
        System.out.println("P"+id+" SEND to P"+ m.dst);
        clockTick();                    // Increase local clock
        m.addTimestamp(clock);          // Add timestamp and history to message
        m.addHistory(this.history);
        history.add(m.dst, clock);      // Update history list
        //add or update(if there is an item with same id, update)
        printState();
    }

    public void onReceiveEvent(Message m) {
        
        System.out.println("P"+id+" RECEIVE FROM P"+m.src);
        if (!HistoryList.deliveryTest(id, clock, m)) {
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

    void clockTick() {
        this.clock.increase(this.id - 1);
    }

    // Debugging helpers
    public void printState() {
        System.out.println("clock: "+clock.toString());
        System.out.println("history: \n"+history.toString()+"\n");
    }
}
