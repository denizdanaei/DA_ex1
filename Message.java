import java.io.Serializable;

public class Message implements Serializable{

    public int src;
    public int dst;
    public VectorClock timestamp;
    public HistoryList history;

    public Message(int src, int dst, VectorClock timestamp, HistoryList history) {
        this.src = src;
        this.dst = dst;
        this.timestamp= timestamp;
        this.history=history;
    }

    public void addTimestamp(VectorClock timestamp) {
        this.timestamp = timestamp;
    }

    public void addHistory(HistoryList srcHistory) {
        history.copyhistory(srcHistory);       
    }
    // Helper function
    public void print() {
        System.out.println("Message from P" + this.src+ " to P" + this.dst+" TIMESTAMP: " + this.timestamp+ " HISTORY: " + this.history.toString());
    }
}   