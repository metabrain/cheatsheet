public static List<Edge> prim(List<List<Edge>> graph, int numVertices) {
	boolean[] visited = new boolean[numVertices];
	int[] cost = new int[numVertices];

	PriorityQueue<Edge> connected = new PriorityQueue<Edge>();
	List<Edge> mst = new ArrayList<Edge>();
	
	for(int i = 0; i < numVertices; i++) {
		visited[i] = false;
		cost[i] = Integer.MAX_VALUE;
	}
		
	int origin = 0;
	cost[origin] = 0;
	connected.add(new Edge(origin, origin, cost[origin]));
	while(!connected.isEmpty()) {
		Edge e = connected.poll();
		visited[e.destin] = true;
		if(origin != e.destin)
			mst.add(e);
		for(Edge out : graph.get(e.destin)) {
			int destin = out.destin;
			if(!visited[destin] && out.weight < cost[destin]) {
				boolean vertexIsInQueue = cost[destin] < Integer.MAX_VALUE;
				cost[destin] = out.weight;
				if(vertexIsInQueue)
					connected.remove(new Edge(e.origin, destin, cost[destin]));
				connected.add(out);
			}
		}
	}
	return mst;	
}