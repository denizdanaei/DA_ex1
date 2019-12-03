import java.util.Arrays;

public class VectorClock {

    private int[] vector;

    public VectorClock(int size) {              // Default constructor
        this.vector = new int[size];
    }
    public VectorClock(VectorClock clk) {       // Copy constructor
        this.vector = clk.vector.clone();
    }

    public void increase(int ndx) {
        this.vector[ndx]++;
    }

    public String toString() {
        return Arrays.toString(this.vector);    
    }

    public static boolean isbehind(VectorClock p, VectorClock m)
	{	
		//p.clk is behind m.timestamp
		for (int i = 0; i < p.vector.length; i++)
		{
			if (p.vector[i] < m.vector[i])
			{
                return true;
			}
		}
		return false;
    }
    
    public void setToMaximum(VectorClock clock) {
        for (int i = 0; i < this.vector.length; i++) {
            if (this.vector[i] < clock.vector[i]) {
                this.vector[i] = clock.vector[i];
            }
        }
    }
	
}