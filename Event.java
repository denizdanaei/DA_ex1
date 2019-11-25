public class Event {
    enum Type {RECEIVE, SEND;}

    public Process process;
    public Type type;
    
    public Event(Process process, Type type) {
        this.process = process;
        this.type = type;
    }

    public void trigger() {
        if (this.type == Type.RECEIVE) {
            this.process.onReceiveEvent();
        } else if (this.type == Type.SEND) {
            this.process.onSendEvent();
        }
    }
}