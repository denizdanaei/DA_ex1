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
            
            int[][] destIDs = {{}, {0}, {0,1}};
			
			int[][] delays = {{}, {300}, {1000,10}};

			for (int i = 0; i < numProcesses; i++)
			{
                Process process = new Process(i, numProcesses);
				MyProcess p = new MyProcess(process, destIDs[i], delays[i]); //
				myThreads[i] = new Thread(p);
			}
			
					
			for (int i = 2; i >=0; i--)
			{	
				myThreads[i].start();
			}
			
		} catch (Exception e) {
			System.err.println("Could not create registry exception: " + e.toString()); 
			e.printStackTrace(); 
		} 
	// Thread.sleep(1000);	
	// System.exit(0);
	}
}

class MyProcess implements Runnable
{	
    int[] destIDs;
	Process process;
	int[] delays;
	public MyProcess(Process process, int[] destIDs, int[] delays) { //
		this.destIDs = destIDs;
		this.process = process;
		this.delays = delays;
	}

	public void run() {
		for (int i = 0; i < destIDs.length; i++)
		{
            // System.out.println("P"+process.id+ " try to P" + destIDs[i]); 
				
			try
			{
				if(process.id==1){
				Thread.sleep(50);}
				// if(i== 1){Thread.sleep(500);}
				// if(i== 2){Thread.sleep(500);}

				System.out.println("\n\n");
				process.onSendEvent(destIDs[i], delays[i]);
				
			}
			catch (Exception e) {
				System.err.println("Client exception: " + e.toString()); 
				e.printStackTrace(); 
			} 
			
		}
	}
	
}
