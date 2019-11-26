public class Process {
    
    public int id;
    public int vectorClk;   // FIXME: replace with correct type
    public int history;     // FIXME: replace with correct type

    public Process(int id) {
        this.id = id;
        this.vectorClk = 0;
    }

    public void onSendEvent(Message m) {
        
        vectorClk++;                            // FIXME
        m.addTimestamp(this.vectorClk);
        m.addHistory(this.history);

        System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dst + " W/ TIMESTAMP: " + m.timestamp);
    }

    public void onReceiveEvent(Message m) {
    
        System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.src + " W/ TIMESTAMP: " + m.timestamp);
    }

    public void onDeliverEvent(Message m) {
        // vectorClk[id-1]++;
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
 
        // System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dst+" "+ VectorClock.toString(vectorClk));
    }


}