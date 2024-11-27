import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  
        while (T-- > 0) {
            int N = sc.nextInt();  
            int K = sc.nextInt();  
            int[] A = new int[N];
            
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            List<Integer> candidates = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if ((A[i] & K) == K) {
                    candidates.add(i + 1); 
                }
            }
            
            if (candidates.isEmpty()) {
                System.out.println("NO");
                continue;
            }
            System.out.println(candidates);
            
            int currentAnd = -1;
            List<Integer> subset = new ArrayList<>();
            for (int i=0;i<candidates.size();i++) {
                int index=candidates.get(i);
                System.out.println(index);
                if (currentAnd == -1) {
                    currentAnd = A[index - 1];
                } 
                currentAnd &= A[index - 1];
                subset.add(index);
            }
            if (currentAnd == K) {
                System.out.println("YES");
                System.out.println(subset.size());
                for (int idx : subset) {
                    System.out.print(idx + " ");
                }
                System.out.println();
            } else {
                System.out.println("NO");
            }
        }
	}
}
