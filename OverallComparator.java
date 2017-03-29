import java.util.*;

public class OverallComparator implements Comparator<Pair<Double, String>> {

	/**
	*
	* Returns the pair with the highest overall quality
	* 	@param Pair_a and Pair_b which hold the overall quality value in their key
	*	@return Returns 0 if b == a, < 0 if b < a, > 0 if b > a
	*/
	public int compare(Pair<Double, String> a, Pair<Double, String> b) {
	    return Double.compare(b.getKey(), a.getKey());
	}

}