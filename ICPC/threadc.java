import java.util.*;

public class threadc {
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

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
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

            // Create edges
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long cost = Math.min(a[i], a[j]) * Math.abs(x[i] - x[j]);
                    edges.add(new Edge(i, j, cost));
                }
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
