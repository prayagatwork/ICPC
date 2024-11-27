import java.util.*;

public class mstc {
    static class Edge implements Comparable<Edge> {
        int u, v;
        long cost;

        Edge(int u, int v, long cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    static class UnionFind {
    int[] parent, rank;

    // Constructor to initialize the UnionFind structure
    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each element is its own parent initially
            rank[i] = 0;    // Rank (tree depth) is initially 0
        }
    }

    // Find with path compression to flatten the tree
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }

    // Union by rank to attach the smaller tree to the larger tree
    boolean union(int x, int y) {
        int rootX = find(x);  // Find the root of x
        int rootY = find(y);  // Find the root of y

        if (rootX == rootY) return false;  // Already in the same set

        // Union by rank to keep the tree shallow
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;  // Increase the rank if both trees are of equal size
        }

        return true;
    }
}


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // Number of test cases

        StringBuilder output = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt(); // Number of points
            int[] x = new int[n];
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            // Sort points by x-coordinate
            Integer[] indices = new Integer[n];
            for (int i = 0; i < n; i++) {
                indices[i] = i;
            }
            Arrays.sort(indices, Comparator.comparingInt(i -> x[i]));

            // Create edges only between consecutive points
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                int u = indices[i];
                int v = indices[i + 1];
                long cost = Math.min(a[u], a[v]) * Math.abs(x[u] - x[v]);
                edges.add(new Edge(u, v, cost));
            }

            // Sort edges by cost
            Collections.sort(edges);

            // Kruskal's MST
            UnionFind uf = new UnionFind(n);
            long totalCost = 0;
            int edgeCount = 0;

            for (Edge edge : edges) {
                if (uf.union(edge.u, edge.v)) {
                    totalCost += edge.cost;
                    edgeCount++;
                    if (edgeCount == n - 1) break;
                }
            }

            output.append(totalCost).append("\n");
        }

        System.out.print(output);
    }
}