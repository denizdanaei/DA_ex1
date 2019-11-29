import java.io.Serializable;

public class Message implements Serializable{

    public int src;
    public int dst;
    public VectorClock timestamp;
    public HistoryList history;

    public Message(int src, int dst) {
        this.src = src;
        this.dst = dst;
        // print();
        // this.timestamp= timestamp;
        // this.history=history;
    }

    public void addTimestamp(VectorClock timestamp) {
        this.timestamp = timestamp;
    }

    public void addHistory(HistoryList newhistory) {

        this.history = new HistoryList();
        // history.copy(newhistory);
        
    }
    // public void clone()
	// {
	    
	// }
	
    // Helper function
    public void print() {
        System.out.println("Message from P" + this.src+ " to P" + this.dst+" TIMESTAMP: " + this.timestamp+ " HISTORY: " + this.history);
        System.out.println("\n");
    }
}   