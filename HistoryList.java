import java.util.List;
import java.util.Vector;
import java.io.Serializable;

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

    public void additem(int id, VectorClock timestamp) {
        
        this.history.add(new HistoryItem(id, timestamp));
    }

    public void copyhistory(HistoryList newlist){
        history.addAll(newlist.history);
        // System.out.println(toString());
    }
    public static boolean deliveryTest(int dst, VectorClock clock, HistoryList msg_History ){
        //returns false if massage can not be delivered
        if(msg_History.history.isEmpty()){
            // System.out.println("msg_History is empty\n");
            return true;
        }
        else{
            for (HistoryItem item: msg_History.history){
                if(item.id==dst){
                    // System.out.println("item.id"+item.id+"==dst"+dst);
                    // System.out.println("message from"+ m.src+ " to "+ m.dst+ "with history P"+item.id +" to P" + dst);
                
                    return !VectorClock.isbehind(clock, item.timestamp);
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (HistoryItem i : this.history) {
            str.append("[P"+i.id+" "+i.timestamp.toString()+"] ");
        }
        return str.toString();
    }
}