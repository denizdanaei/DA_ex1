import java.util.Arrays;

public class VectorClock{

	public static int[] max(int[] vectorClock1, int[] vectorClock2)
	{
		int[] max = vectorClock1;
		for (int i = 0; i < vectorClock1.length; i++)
		{
			if (vectorClock1[i] < vectorClock2[i])
				max[i] = vectorClock2[i];
		}
		return max;
	}
	public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2){

    
    return vectorClock1;
    }
    public static String toString(int[] vectorClock)
    {
        return Arrays.toString(vectorClock);
        
    }
}