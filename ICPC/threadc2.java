import java.util.*;

public class threadc2 {
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
            for (int i = 0; i < n - 1; i++) {
                long cost = Math.min(a[i], a[i + 1]) * Math.abs(x[i] - x[i + 1]);
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
                    if (edgeCount == n - 1) break; // Early termination
                }
            }

            output.append(totalCost).append("\n");
        }

        System.out.print(output);
    }
}