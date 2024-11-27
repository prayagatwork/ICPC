def minimum_cost_to_buy_items(test_cases):
    results = []
    
    for n, k, prices in test_cases:
        # Sort prices in ascending order
        prices.sort()
        
        # Create an array to store minimum costs for buying m items
        min_costs = [0] * (n + 1)
        
        # Calculate minimum costs for each m (1 to n)
        for m in range(1, n + 1):
            if m <= k:
                # If m is less than or equal to k, we just buy them all
                min_costs[m] = sum(prices[:m])
            else:
                # Calculate cost considering groups of k + 1
                full_groups = m // (k + 1)
                remaining_items = m % (k + 1)
                
                # Cost from full groups
                cost_from_full_groups = full_groups * sum(prices[:k])
                
                # Cost from remaining items
                cost_from_remaining = sum(prices[:remaining_items])
                
                # Total cost is the sum of both parts
                min_costs[m] = cost_from_full_groups + cost_from_remaining
        
        results.append(min_costs[1:])  # Return results for 1 to n items
    
    return results


def main():
    # Read number of test cases
    t = int(input("Enter number of test cases: "))
    
    # Collect test cases
    test_cases = []
    for _ in range(t):
        # Read n and k
        n, k = map(int, input("Enter n and k (e.g., '5 2'): ").split())
        
        # Read the list of item prices
        prices = list(map(int, input(f"Enter {n} prices: ").split()))
        
        test_cases.append((n, k, prices))
    
    # Calculate the results using the main function
    results = minimum_cost_to_buy_items(test_cases)
    
    # Print the results
    for result in results:
        print(" ".join(map(str, result)))


if __name__ == "__main__":
    main()
