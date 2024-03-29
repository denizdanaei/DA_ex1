
// Simulating Example 1 from the video (@2.50):
// https://www.youtube.com/watch?v=y5HvzJjYhv8 

public class Main {
    public static void main(String[] args) {

        // Process[] processList = {
        //     new Process(1, 3),
        //     new Process(2, 3),
        //     new Process(3, 3)
        // };
        
        // Message[] messageList = {
        //     new Message(3, 1),
        //     new Message(3, 2),
        //     new Message(2, 1)
        // };

        // Event[] eventList = {                               // Event names from the video:
        //     new Event(processList[2], Event.Type.SEND, messageList[0]),     // e31
        //     new Event(processList[2], Event.Type.SEND, messageList[1]),     // e32
        //     new Event(processList[1], Event.Type.RECEIVE, messageList[1]),  // e21
        //     new Event(processList[1], Event.Type.SEND, messageList[2]),     // e22
        //     new Event(processList[0], Event.Type.RECEIVE, messageList[2]),  // - (becomes e12 later)
        //     new Event(processList[0], Event.Type.RECEIVE, messageList[0])   // e11
        // };


        // Example 2
        Process[] processList = {
            new Process(1, 3),
            new Process(2, 3),
            new Process(3, 3)
        };
        
        Message[] messageList = {
            new Message(1, 2),
            new Message(1, 3),
            new Message(3, 2),
        };

        Event[] eventList = {                               // Event names from the video:
            new Event(processList[0], Event.Type.SEND, messageList[0]),     // e11
            new Event(processList[0], Event.Type.SEND, messageList[1]),     // e12
            new Event(processList[2], Event.Type.RECEIVE, messageList[1]),  // e31
            new Event(processList[2], Event.Type.SEND, messageList[2]),     // e32
            new Event(processList[1], Event.Type.RECEIVE, messageList[2]),  // - (becomes e22 later)
            new Event(processList[1], Event.Type.RECEIVE, messageList[0]),  // - (becomes e22 later)
        };


        for (Event e : eventList) {
            e.trigger();
        }
    }
}