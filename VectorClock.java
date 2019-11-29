import java.util.Arrays;
import java.io.Serializable;

public class VectorClock implements Serializable{
    private static final long serialVersionUID = 2L;
   
    private int id;
    private int[] vector;

    public VectorClock(int id, int size) {
        this.id = id;
        this.vector = new int[size];
    }
    public int[] getVector(){
        return vector;
    }
    public void setVector(VectorClock vectorClock){

        this.vector=vectorClock.vector;

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

    public void updateOnDelivery(VectorClock m){
        tick();
        for (int i = 0; i < vector.length; i++)
		{
			if (vector[i] < m.vector[i])
				vector[i] = m.vector[i];
		}
		// return vector;
    }
    
	// public static int [] update_vectorClk(int[] vectorClock1, int[] vectorClock2) {
    //     return vectorClock1;
    // }
}