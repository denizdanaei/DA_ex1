
public class Process {
    
    private int id;   //not public? 
    private int[] vectorClk;
    public Process(int id, int numProcess) {
        this.id = id;
        this.vectorClk=new int[numProcess];
    }

    public void onReceiveEvent(Message m) {
    
        System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.srcId);
    }

    public void onSendEvent(Message m) {
        vectorClk[id-1]++;
        System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClk));
    }

    public void onDeliverEvent(Message m) {
        vectorClk[id-1]++;
        // VectorClock.max(vectorClk, m.vectorClk); //uncomment when problem is fixed
 
        System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClk));
    }


}