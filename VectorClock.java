import java.util.Arrays;

// import sun.security.util.Length;

public class VectorClock{
    int[] vectorClock;

    public VectorClock (int numprocess){
        vectorClock=new int[numprocess];
    }

    public void setOnSendEvent(int id){
        vectorClock[id-1]++;
    }
    public void setOnDeliverEvent(int id, VectorClock m){
        vectorClock[id-1]++;
        for (int i = 0; i < vectorClock.length; i++)
		{
			if (vectorClock[i] < m.vectorClock[i])
                vectorClock[i] = m.vectorClock[i];
        }
    }
	// public int[] max(VectorClock vectorClock, VectorClock vc2)
	// {
	// 	VectorClock max = vectorClock;
	// 	for (int i = 0; i < numprocess; i++)
	// 	{
	// 		if (vectorClock.vectorClock[i] < vc2.vectorClock[i])
    //             max.vectorClock[i] = vc2.vectorClock[i];
    //     }
    //     return max.vectorClock;
	// }
	public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2){
        //TODO    
        return vectorClock1;
    }
    public static String toString(int[] vectorClock)
    {
        return Arrays.toString(vectorClock);  
    }
}