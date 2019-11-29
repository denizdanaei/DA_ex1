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
        
        this.vector=vectorClock.vector.clone();
    }
    public int getid(){
        return id;
    }
    public void setid(VectorClock vectorClock){
        this.id=vectorClock.id;
    }

    public void tick() {
        vector[id]++;
    }

    public static boolean isbehind(VectorClock p, VectorClock m)
	{	
		//p.clk is behind m.timestamp
		for (int i = 0; i < p.vector.length; i++)
		{
			if (p.vector[i] < m.vector[i])
			{
                System.out.println("clockVector "+p.vector[i]+" is behind " + m.vector[i]);
                return true;
			}
		}
		return false;
	}
    
    public void updateOnDelivery(VectorClock m){
        tick();
        for (int i = 0; i < vector.length; i++)
		{
			if (vector[i] < m.vector[i])
				vector[i] = m.vector[i];
		}
    }

    
    public String toString() {
        return Arrays.toString(vector);    
    }
}