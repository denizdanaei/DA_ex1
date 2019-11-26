public class Message {
    public int srcId;
    public int dstId;
    public VectorClock vectorClock;
    public Message(Process src, Process dst, VectorClock vectorClk) { 
        this.srcId = src.id;
        this.dstId = dst.id;
        this.vectorClock=src.vectorClock;
    }
}   