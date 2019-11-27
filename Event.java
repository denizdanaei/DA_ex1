public class Event {
    enum Type {RECEIVE, SEND;}

    public Process process;
    public Type type;
    public Message message;
    
    public Event(Process process, Type type, Message message) {
        this.process = process;
        this.type = type;
        this.message = message;
    }

    public void trigger() {
        if (this.type == Type.RECEIVE) {
            this.process.onReceiveEvent(this.message);
        } else if (this.type == Type.SEND) {
            this.process.onSendEvent(this.message);
        }
    }

    public String toString() {
        if (type == Type.RECEIVE) return "RECEIVE";
        else return "SEND";
    }
}