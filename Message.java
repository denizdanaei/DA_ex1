import java.util.List;
import java.util.ArrayList;

public class Message {

    public int src;
    public int dst;
    public VectorClock timestamp;
    public List<HistoryItem> history;

    public Message(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    public void addMetadata(VectorClock timestamp, List<HistoryItem> history) {
        this.timestamp = new VectorClock(timestamp);            // Copy timestamp
        this.history = new ArrayList<HistoryItem>(history);     // Copy history
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
