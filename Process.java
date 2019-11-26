
public class Process {
    
    public int id; 
    int numprocess=3;
    public Process(int id) {
        this.id = id;
        this.vectorClock=vectorClock;
    }
    
    public VectorClock vectorClock= new VectorClock(numprocess);
   
    
    public void onReceiveEvent(Message m) {
        System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.srcId);
        if(DeliveryCondition()){Deliver(m);}
        else{pushtoBuffer();}
        if(!isBufferempty()){popBuffer();}
    }

    public void onSendEvent(Message m) {
        vectorClock.setOnSendEvent(id);
        m.vectorClock=vectorClock;
        updateHistoryBuffer();
        System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClock.vectorClock));
    }

    public void Deliver(Message m) {
        vectorClock.setOnDeliverEvent(id, m.vectorClock);
        updateHistoryBuffer();
        System.out.println("P"+this.id+" Delivered MESSAGE TO P" + m.dstId+" "+ VectorClock.toString(vectorClock.vectorClock));
    }


    // pseudocode
    private boolean DeliveryCondition(){
        boolean Condition=false;
        return Condition;
    }
    private void pushtoBuffer(){}
    private void popBuffer(){}
    private boolean isBufferempty(){return false;}
    private void updateHistoryBuffer(){}


}