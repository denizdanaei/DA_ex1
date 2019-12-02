public class HistoryItem {
    public int id;
    public VectorClock timestamp;
    public HistoryItem(int id, VectorClock timestamp) {
        this.id = id;
        this.timestamp = new VectorClock(timestamp);        // Copy timestamp
    }
}
