
class Solution {
    ArrayList<Integer>[] graph;
    char[] color;

    int farthest(int start, int[] dist) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        dist[start] = 0;
        int far = start;

        while (!q.isEmpty()) {
            int u = q.poll();

            if (dist[u] > dist[far]) {
                far = u;
            }

            for (int v : graph[u]) {
                if (color[v] == color[u] && dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }

        return far;
    }

    public int longestPath(String s, int[][] edges) {
        int n = s.length();
        color = s.toCharArray();

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int[] arm = new int[n];
        int[] distA = new int[n];
        int[] distB = new int[n];

        int ans = 1;

        for (int start = 0; start < n; start++) {
            if (visited[start]) {
                continue;
            }

            ArrayList<Integer> component = new ArrayList<>();
            ArrayDeque<Integer> q = new ArrayDeque<>();

            q.offer(start);
            visited[start] = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                component.add(u);

                for (int v : graph[u]) {
                    if (!visited[v] && color[u] == color[v]) {
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }

            for (int x : component) {
                distA[x] = -1;
            }

            int a = farthest(start, distA);

            for (int x : component) {
                distA[x] = -1;
            }

            int b = farthest(a, distA);

            for (int x : component) {
                distB[x] = -1;
            }

            farthest(b, distB);

            for (int x : component) {
                arm[x] = Math.max(distA[x], distB[x]) + 1;
                ans = Math.max(ans, arm[x]);
            }
        }

        for (int[] e : edges) {
            int u = e[0] - 1;
            int v = e[1] - 1;

            if (color[u] != color[v]) {
                ans = Math.max(ans, arm[u] + arm[v]);
            }
        }

        return ans;
    }
}
