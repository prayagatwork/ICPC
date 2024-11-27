def minimum_cost_to_buy_items(test_cases):
    results = []
    
    for n, k, prices in test_cases:
        # Sort prices to facilitate finding minimums
        prices.sort()
        
        # Initialize DP array
        dp = [float('inf')] * (n + 1)
        dp[0] = 0  # Cost of buying 0 items
        
        # Iterate over each item
        for i in range(n):
            for m in range(n, 0, -1):  # Update dp in reverse order
                if m > i + 1:  # Ensure we don't exceed available items
                    continue
                # If we buy this item
                dp[m] = min(dp[m], dp[m-1] + prices[i])
                
                # If we can use this as a free item (only if we have enough previous purchases)
                if m >= k + 1:
                    dp[m] = min(dp[m], dp[m-k-1] + prices[i])
        
        results.append(dp[1:n+1])
    
    return results

# Example usage:
t = int(input())
test_cases = []
for _ in range(t):
    n, k = map(int, input().split())
    prices = list(map(int, input().split()))
    test_cases.append((n, k, prices))

results = minimum_cost_to_buy_items(test_cases)
for result in results:
    print(" ".join(map(str, result)))