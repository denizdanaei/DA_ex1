
// Simulating Example 1 from the video (@2.50):
// https://www.youtube.com/watch?v=y5HvzJjYhv8 

public class Main {
    public static void main(String[] args) {

        Process[] processList = {
            new Process(1),
            new Process(2),
            new Process(3)
        };

        Event[] eventList = {                               // Event names from the video:
            new Event(processList[2], Event.Type.SEND),     // e31
            new Event(processList[2], Event.Type.SEND),     // e32
            new Event(processList[1], Event.Type.RECEIVE),  // e21
            new Event(processList[1], Event.Type.SEND),     // e22
            new Event(processList[0], Event.Type.RECEIVE),  // -
            new Event(processList[0], Event.Type.RECEIVE)   // e11
        };

        for (Event e : eventList) {
            e.trigger();
        }

    }
}