import java.util.*;

public class try2 {
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
            int[][] points = new int[n][2];

            for (int i = 0; i < n; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                points[i][0] = x[i];
                points[i][1] = a[i];
            }

            // Sort points by position
            Arrays.sort(points, Comparator.comparingInt(p -> p[0]));

            // Create edges only between consecutive points
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                long cost = Math.min(points[i][1], points[i + 1][1]) * (long) Math.abs(points[i][0] - points[i + 1][0]);
                edges.add(new Edge(i, i + 1, cost));
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
