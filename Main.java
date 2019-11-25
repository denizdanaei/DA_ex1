
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Create two instances of a Process
        Process p1 = new Process(11);
        Process p2 = new Process(22);
        p1.printId();
        p2.printId();


        Event e1 = new Event(p1, Event.Type.RECEIVE);
        e1.trigger();
    }
}