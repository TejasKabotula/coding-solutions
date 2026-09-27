# Longest Colored Path

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an undirected acyclic graph (tree) with  **n**  nodes numbered from 1 to n. Each node is colored either Red (R) or Blue (B).

The colors of the nodes are given by a string  **s**  of length n, where:

- s[i] = 'R' means node i + 1 is Red.
- s[i] = 'B' means node i + 1 is Blue.

You are also given a list of n - 1 edges  **edges[][]**, where each edges[i] = [u, v] represents an undirected edge between nodes u and v.

You can start from any node and traverse along the edges to form a path.

A path is called valid if, once you visit a Blue node, you cannot visit any Red node after it on the same path.

In other words, a valid path must have the following form:

- Only Red nodes, or
- Only Blue nodes, or
- Some Red nodes followed by some Blue nodes.
- A path containing a pattern like Blue -> Red is invalid.

Find the maximum number of nodes in a valid path.

 **Examples:** 

```
Input: s = "RBB", edges = [[1, 2], [1, 3]] 
  
Output: 2
Explanation: The longest path is either 1 -> 2 or 1 -> 3. In both cases, the length of the path is 2.
```

```
Input: s = "BB", edges = [[1, 2]]
  
Output: 2
Explanation: The longest path is 1 -> 2. The length of the path is 2.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-27T07:00:33.574Z  

```java

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

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/longest-colored-path--151454/1)