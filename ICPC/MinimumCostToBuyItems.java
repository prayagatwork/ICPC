import java.util.*;

public class MinimumCostToBuyItems {

    public static List<List<Integer>> minimumCostToBuyItems(List<TestCase> testCases) {
        List<List<Integer>> results = new ArrayList<>();

        for (TestCase testCase : testCases) {
            int n = testCase.n;
            int k = testCase.k;
            int[] prices = testCase.prices;
            
            // Sort prices to facilitate finding minimums
            Arrays.sort(prices);
            
            // Initialize DP array
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;  // Cost of buying 0 items
            
            // Iterate over each item
            for (int i = 0; i < n; i++) {
                for (int m = n; m >= 1; m--) {  // Update dp in reverse order
                    if (m > i + 1) {  // Ensure we don't exceed available items
                        continue;
                    }
                    // If we buy this item
                    dp[m] = Math.min(dp[m], dp[m - 1] + prices[i]);
                    
                    // If we can use this as a free item (only if we have enough previous purchases)
                    if (m >= k + 1) {
                        dp[m] = Math.min(dp[m], dp[m - k - 1] + prices[i]);
                    }
                }
            }
            
            // Prepare result for this test case
            List<Integer> result = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                result.add(dp[i]);
            }
            results.add(result);
        }
        
        return results;
    }

    // Helper class to store test case data
    static class TestCase {
        int n, k;
        int[] prices;
        
        TestCase(int n, int k, int[] prices) {
            this.n = n;
            this.k = k;
            this.prices = prices;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int t = scanner.nextInt();
        List<TestCase> testCases = new ArrayList<>();
        
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] prices = new int[n];
            for (int j = 0; j < n; j++) {
                prices[j] = scanner.nextInt();
            }
            testCases.add(new TestCase(n, k, prices));
        }
        
        List<List<Integer>> results = minimumCostToBuyItems(testCases);
        
        for (List<Integer> result : results) {
            System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
        
        scanner.close();
    }
}
