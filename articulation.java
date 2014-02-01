public class Articulation {
    Set<Integer> art = new HashSet<Integer>();
    int n;
    Node[] graph;
    int num;
    int[] dfn;
    int[] low;
    public Articulation(int vertex) {
        n = vertex;
        graph = new Node[n];
        dfn = new int[n];
        low = new int[n];
    }
    public void init() {
        for (int i = 0; i < n; i++) {
            dfn[i] = low[i] = -1;
        }
        num = 0;
    }
    public void articulation() {
        init();
        articulation(0, -1);
    }

    private void articulation(int check, int parent) {
        int childCount = 0;
        dfn[check] = low[check] = num++;
        for (Node adj = graph[check]; adj != null; adj = adj.link) {
            int w = adj.vertex;
            if (dfn[w] < 0) {
		childCount++;
                articulation(w, check);
                low[check] = (low[check] < low[w]) ? low[check] : low[w];
                if (parent >=0 && low[w] >= dfn[check]) { 
                    art.add(check);
                }
            } else if (w != parent) {
		    low[check] = (low[check] < dfn[w]) ? low[check] : dfn[w];
            }
        }
        if (parent < 0 && childCount > 1) {
		art.add(check);
        }
    }

    public void add(int x, int y) {
        Node tt = new Node();
        tt.vertex = y;
        tt.link = graph[x];
        graph[x] = tt;
        tt = new Node();
        tt.vertex = x;
        tt.link = graph[y];
        graph[y] = tt;
    }
}
