package targetPairs;

public class targetPairs {

	public static void main(String[] args) {
		// Testing
		System.out.println("Range: 6; Target: 7");
		pairs(6,7);
		System.out.println("\nRange: 0; Target: 0");
		pairs(0,0);
		System.out.println("\nRange: 100; Target: 50");
		pairs(100,50);
	}

	// Return pairs within range that add to target of unique numbers
	public static void pairs (int range, int target) {
		if (range == 0 || range < 0 || target == 0 || target < 0) {
			System.out.println("Not a valid range or target");
			return;
		}
		int[] r = new int[range];
		for (int i = 0; i < range; i++) {			// create array of [1... range]
			r[i] = i + 1;
		}
		int k = 0;
		int j = r.length - 1;
		while (k < j) {
			int sum = r[k] + r[j];
			if (sum == target) {
				System.out.println("(" + r[k] + "," + r[j] + ")");
				j--;
			}
			else if (sum < target) k++;				// sum too small
			else j--;								// sum too large
		}
	}
}