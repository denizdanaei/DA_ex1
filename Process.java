
public class Process {
    
    private int id;    
    
    public Process(int id) {
        this.id = id;
    }

    public void onReceiveEvent() {
        System.out.println("P"+this.id+" RECEIVE EVENT");
    }

    public void onSendEvent() {
        System.out.println("P"+this.id+" SEND EVENT");
    }
}