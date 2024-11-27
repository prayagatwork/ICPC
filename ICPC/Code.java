import java.util.*;

public class Code {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // Number of test cases
        
        while (T-- > 0) {
            int N = sc.nextInt(); // Length of array
            int K = sc.nextInt(); // Number of constraints
            
            // Triplets for constraints
            int[][] triplets = new int[K][3];
            for (int i = 0; i < K; i++) {
                triplets[i][0] = sc.nextInt(); // Li
                triplets[i][1] = sc.nextInt(); // Ri
                triplets[i][2] = sc.nextInt(); // mi
            }
            
            // Array to store the result
            int[] A = new int[N];
            Arrays.fill(A, 1); // Start by filling the array with 1
            
            // Process each constraint
            boolean valid = true;
            for (int[] triplet : triplets) {
                int L = triplet[0] - 1; // Convert to 0-based index
                int R = triplet[1] - 1; // Convert to 0-based index
                int mi = triplet[2];
                
                // Ensure the subarray [L, R] does not have minimum equal to mi
                for (int i = L; i <= R; i++) {
                    if (A[i] == mi) {
                        // Change A[i] to a valid number
                        A[i] = mi + 1; // Try a value greater than mi
                        if (A[i] > N) A[i] = 1; // Wrap around if exceeding N
                    }
                }
            }
            
            // Verify the constraints
            for (int[] triplet : triplets) {
                int L = triplet[0] - 1;
                int R = triplet[1] - 1;
                int mi = triplet[2];
                
                // Check if the minimum in [L, R] is still mi
                int minValue = Integer.MAX_VALUE;
                for (int i = L; i <= R; i++) {
                    minValue = Math.min(minValue, A[i]);
                }
                if (minValue == mi) {
                    valid = false;
                    break;
                }
            }
            
            // Output the result
            if (!valid) {
                System.out.println(-1);
            } else {
                for (int i = 0; i < N; i++) {
                    System.out.print(A[i] + " ");
                }
                System.out.println();
            }
        }
        
        sc.close();
    }
}
