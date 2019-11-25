
public class Process {
    
    private int id;    
    
    public Process(int id) {
        this.id = id;
    }

    public void onReceiveEvent(Message m) {
        System.out.println("P"+this.id+" RECEIVE MESSAGE FROM P" + m.srcId);
    }

    public void onSendEvent(Message m) {
        System.out.println("P"+this.id+" SEND MESSAGE TO P" + m.dstId);
    }
}