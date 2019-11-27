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