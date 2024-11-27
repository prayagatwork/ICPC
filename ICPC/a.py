import sys

def solve_buy_k_get_one_free():
    input = sys.stdin.read
    data = input().split()
    
    t = int(data[0])  # Number of test cases
    idx = 1
    results = []
    
    for _ in range(t):
        # Read n and k
        n, k = map(int, data[idx:idx+2])
        idx += 2
        
        # Read the costs
        costs = list(map(int, data[idx:idx+n]))
        idx += n
        
        # Sort the costs in ascending order
        costs.sort()
        
        # Calculate prefix sums
        prefix_sums = [0] * (n + 1)
        for i in range(1, n + 1):
            prefix_sums[i] = prefix_sums[i - 1] + costs[i - 1]
        
        # Calculate minimum cost for each m
        min_costs = [0] * n
        for m in range(1, n + 1):
            # Compute the number of groups and leftover items
            groups = m // (k + 1)
            leftover = m % (k + 1)
            
            # Total cost is the sum of the most expensive (m - groups) items
            min_costs[m - 1] = prefix_sums[m] - prefix_sums[groups]
        
        results.append(" ".join(map(str, min_costs)))
    
    # Print all results
    sys.stdout.write("\n".join(results) + "\n")

# Main code to take input from the user
def main():
    t = int(input("Enter number of test cases: "))
    all_input = []
    
    for _ in range(t):
        # For each test case, first input n and k
        n, k = map(int, input(f"Enter n and k for test case {_ + 1}: ").split())
        
        # Then input the list of costs
        costs = list(map(int, input(f"Enter {n} costs for test case {_ + 1}: ").split()))
        
        # Store inputs as a single string to simulate reading from stdin
        all_input.append(f"{n} {k}")
        all_input.append(" ".join(map(str, costs)))
    
    # Prepare input in the format expected by solve_buy_k_get_one_free
    input_data = [str(t)] + all_input
    sys.stdin = sys.__stdin__  # Make sure we don't overwrite stdin during manual input
    
    # Simulate reading from sys.stdin by joining inputs into a single string
    sys.stdin = '\n'.join(input_data) + '\n'
    
    # Now call your solve function
    solve_buy_k_get_one_free()

if __name__ == "__main__":
    main()
