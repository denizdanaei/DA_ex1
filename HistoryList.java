import java.util.List;
import java.util.Vector;
import java.io.Serializable;
// import java.io.Collections;

public class HistoryList implements Serializable{
    private static final long serialVersionUID = 11L;
    
    private class HistoryItem implements Serializable{
        private static final long serialVersionUID = 111L;
    
        public int id;
        public VectorClock timestamp;
        public HistoryItem(int id, VectorClock timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    private List<HistoryItem> history;
    
    public HistoryList() {
        this.history = new Vector<HistoryItem>();
    }

    public void add(int id, VectorClock timestamp) {
        
        this.history.add(new HistoryItem(id, timestamp));
    }
    // public void copy(HistoryList newlist){
    //     history.addAll(newlist.history);
    // }
    public static boolean deliveryTest(int dst, VectorClock clock, Message m ){
        //returns false if massage can not be delivered
        for (HistoryItem item: m.history.history){
            if(item.id==dst){
                
                // System.out.println("message from"+ m.src+ " to "+ m.dst+ "with history P"+item.id +" to P" + dst);
             
                return !VectorClock.isbehind(clock, m.timestamp);
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (HistoryItem i : this.history) {
            str.append("P"+i.id+" "+i.timestamp.toString()+"\n");
        }
        return str.toString();
    }
}