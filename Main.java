import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
// Simulating Example 1 from the video (@2.50):
// https://www.youtube.com/watch?v=y5HvzJjYhv8 

public class Main {
    public static void main(String[] args) {
        int numProcesses = 3;
		Thread[] myThreads = new Thread[numProcesses];
		try{
			// Create Registry
			Registry registry = LocateRegistry.createRegistry(1099);
            
            int[][] destIDs = {{1,2}, {}, {1}};
			String[][] messages = {{"1", "2"}, {}, {"3"}};
			int[][] delays = {{5000, 0}, {}, {500}};

			for (int i = 0; i < numProcesses; i++)
			{
                Process process = new Process(i, numProcesses);
				MyProcess p = new MyProcess(process, destIDs[i], messages[i], delays[i]);
				myThreads[i] = new Thread(p);
			}
			for (int i = 0; i < numProcesses; i++)
			{
				myThreads[i].start();
			}
			
		} catch (Exception e) {
			System.err.println("Could not create registry exception: " + e.toString()); 
			e.printStackTrace(); 
		} 
		
	}


     /*   
    Process[] processList = {
        new Process(1, 3),
        new Process(2, 3),
        new Process(3, 3)
    };
            
    Message[] messageList = {
        new Message(3, 1),
        new Message(3, 2),
        new Message(2, 1)
    };




        Event[] eventList = {                               // Event names from the video:
            new Event(processList[2], Event.Type.SEND, messageList[0]),     // e31
            new Event(processList[2], Event.Type.SEND, messageList[1]),     // e32
            new Event(processList[1], Event.Type.RECEIVE, messageList[1]),  // e21
            new Event(processList[1], Event.Type.SEND, messageList[2]),     // e22
            new Event(processList[0], Event.Type.RECEIVE, messageList[2]),  // - (becomes e12 later)
            new Event(processList[0], Event.Type.RECEIVE, messageList[0])   // e11
        };

        for (Event e : eventList) {
            e.trigger();
        }
    }
    */
}

class MyProcess implements Runnable
{	
    Message[] messageList = {
        new Message(3, 1),
        new Message(3, 2),
        new Message(2, 1)
    };
	int[] destIDs;
    String[] messages;
	Process process;
	int[] delays;
	public MyProcess(Process process, int[] destIDs, String[] messages, int[] delays) {
		this.messages = messages;
		this.destIDs = destIDs;
		this.process = process;
		this.delays = delays;
	}

	public void run() {
		for (int i = 0; i < destIDs.length; i++)
		{
			try
			{
			    if(process.id == 2)
			        Thread.sleep(1000);
				process.onSendEvent(messageList[i]);
			}
			catch (Exception e) {
				System.err.println("Client exception: " + e.toString()); 
				e.printStackTrace(); 
			} 
			
		}
	}
	
}
