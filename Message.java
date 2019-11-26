public class Message {

    public int src;
    public int dst;
    public int timestamp;       // FIXME: change to VectorClock type
    public int history;         // FIXME: change to History type, or list of tuples or sth

    public Message(int src, int dst) {
        this.src = src;
        this.dst = dst;
    }

    public void addTimestamp(int timestamp) {    // FIXME: change type
        this.timestamp = timestamp;
    }

    public void addHistory(int history) {        // FIXME: change type
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