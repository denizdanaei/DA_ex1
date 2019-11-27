import java.util.Arrays;

public class VectorClock {

    private int id;
    private int[] vector;

    public VectorClock(int id, int size) {
        this.id = id;
        this.vector = new int[size];
    }

    public void tick() {
        vector[id-1]++;
    }

    public String toString() {
        return Arrays.toString(vector);    
    }

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
	public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2) {
        return vectorClock1;
    }
}