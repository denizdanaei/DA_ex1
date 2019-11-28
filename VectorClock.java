import java.util.Arrays;

public class VectorClock {

    private int id;
    private int[] vector;

    public VectorClock(int id, int size) {
        this.id = id;
        this.vector = new int[size];
    }

    public void tick() {
        vector[id]++;
    }

    public String toString() {
        return Arrays.toString(vector);    
    }

    public static boolean isbehind(VectorClock p, VectorClock m)
	{	
		//p.clk is behind m.timestamp
		for (int i = 0; i < p.vector.length; i++)
		{
			if (p.vector[i] < m.vector[i])
			{
                System.out.println(p.vector[i]+"is behind " + m.vector[i]);
                return true;
			}
		}
		return false;
	}
	
    // this is now in tick()
    // public void setOnSendEvent(int id){
    //     vectorClock[id-1]++;
    // }

    // public void setOnDeliverEvent(int id, VectorClock m){
    //     vectorClock[id]++;
    //     for (int i = 0; i < vectorClock.length; i++)
	// 	{
	// 		if (vectorClock1[i] < vectorClock2[i])
	// 			max[i] = vectorClock2[i];
	// 	}
	// 	return max;
    // }
    
	// public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2) {
    //     return vectorClock1;
    // }
}