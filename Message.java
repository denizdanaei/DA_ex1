public class Message {
    public int srcId;
    public int dstId;
    public int[] vectorClk;
    public Message(int src, int dst) { // ,int[] VectorClk
        this.srcId = src;
        this.dstId = dst;
        // this.vectorClk=vectorClk;
    }
}