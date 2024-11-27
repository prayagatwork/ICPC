def construct_tree(n, d, l):
    # Check feasibility conditions
    if l > d + 1 or n < d + 1 or n < l + (l - 1):
        return [-1]
    
    edges = []
    
    # Create a path for the diameter
    for i in range(1, d + 2):  # Create d + 1 nodes in a line
        if i < d + 1:
            edges.append((i, i + 1))
    
    # Now we have used d + 1 nodes, remaining nodes
    remaining_nodes = n - (d + 1)
    
    # We need to ensure we have l leaves
    if remaining_nodes > 0:
        # Connect remaining nodes to the first node in the path to maintain leaf count
        for i in range(remaining_nodes):
            edges.append((1, d + 2 + i))
    
    return edges

def main():
    import sys
    input = sys.stdin.read
    data = input().splitlines()
    
    t = int(data[0])
    results = []
    
    for i in range(1, t + 1):
        n, d, l = map(int, data[i].split())
        edges = construct_tree(n, d, l)
        
        if edges == [-1]:
            results.append("-1")
        else:
            result = []
            for u, v in edges:
                result.append(f"{u} {v}")
            results.append("\n".join(result))
    
    print("\n".join(results))

if __name__ == "__main__":
    main()