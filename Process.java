public class Process {
    
    public int id;
    public VectorClock clock;
    public int history;         // FIXME: replace with correct type

    public Process(int id, int numProcesses) {
        this.id = id;
        this.clock = new VectorClock(id, numProcesses);
    }

    public void onSendEvent(Message m) {
        clock.tick();
        m.addTimestamp(clock);
        m.addHistory(this.history);
        // System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dst + " W/ TIMESTAMP: " + m.timestamp.toString());
    }

    public void onReceiveEvent(Message m) {
    
        // System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.src + " W/ TIMESTAMP: " + m.timestamp.toString());
    }

    public void onDeliverEvent(Message m) {
        // vectorClk[id-1]++;
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
 
        // System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dst+" "+ VectorClock.toString(vectorClk));
    }




}