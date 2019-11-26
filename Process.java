
public class Process {
    
    public int id;   //not public? 
    int numprocess=3;
    public VectorClock vectorClock= new VectorClock(numprocess);
   
    public Process(int id) {
        this.id = id;
        this.vectorClock=vectorClock;
    }

    public void onReceiveEvent(Message m) {
    
        System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.srcId);
    }

    public void onSendEvent(Message m) {
        vectorClock.setOnSendEvent(id);
        System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClock.vectorClock));
    }

    public void onDeliverEvent(Message m) {
        vectorClock.setOnDeliverEvent(id, m.vectorClock);
        System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClock.vectorClock));
    }


}