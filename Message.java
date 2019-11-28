import java.io.Serializable;

public class Message implements Serializable{

    public int src;
    public int dst;
    public VectorClock timestamp;
    public HistoryList history;

    public Message(int src, int dst) {
        this.src = src;
        this.dst = dst;
        print();
        // this.timestamp= timestamp;
        // this.history=history;
    }

    public void addTimestamp(VectorClock timestamp) {
        this.timestamp = timestamp;
    }

    public void addHistory(HistoryList history) {
        this.history = history;
    }

    // Helper function
    public void print() {
        System.out.println("SRC: " + this.src);
        System.out.println("DST: " + this.dst);
        System.out.println("TIMESTAMP: " + this.timestamp);
        System.out.println("HISTORY: " + this.history);
        System.out.println("\n");
    }
}   