import java.util.Arrays;

public class VectorClock{
    int[] vectorClock;
    public VectorClock (int numprocess){
        vectorClock=new int[numprocess];        
    }

    public void setOnSendEvent(int id){
        vectorClock[id-1]++;
    }
    public void setOnDeliverEvent(int id, VectorClock m){
        vectorClock[id]++;
        for (int i = 0; i < vectorClock.length; i++)
		{
			if (vectorClock[i] < m.vectorClock[i])
                vectorClock[i] = m.vectorClock[i];
        }
    }

	public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2){
        //TODO    
        return vectorClock1;
    }
    public static String toString(int[] vectorClock)
    {
        return Arrays.toString(vectorClock);  
    }
}