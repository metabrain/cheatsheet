Pair<Integer, int[][]> edmondsKarp(List<List<Edge>> graph, int source,
		int sink) {
	List<List<Edge>> network = buildNetwork(graph);
	int numVert = network.size();
	int[][] flow = new int[numVert][numVert];
	for (List<Edge> list : graph)
		for (Edge e : list)
			flow[e.origin][e.destin] = 0;

	int[] via = new int[numVert];
	int flowValue = 0;
	int increment;
	while ((increment = findPath(network, flow, source, sink, via)) != 0) {
		flowValue += increment;

		int vertex = sink;
		while (vertex != source) {
			int origin = via[vertex];
			flow[origin][vertex] += increment;
			flow[vertex][origin] -= increment;
			vertex = origin;
		}
	}
	return new Pair<Integer, int[][]>(flowValue, flow);
}

List<List<Edge>> buildNetwork(List<List<Edge>> graph) {
	List<List<Edge>> network = graph.clone();

	for (int i = 0; i < graph.size(); i++) 
		for (Edge e : outEdges.get(i)) 
			network.get(e.destin).add(new Edge(e.destin, e.source, 0));
			
	return network;
}

int findPath(List<List<Edge>> network, int[][] flow, int source, int sink,
		int[] via) {
	int numVert = network.size();
	Queue<Integer> waiting = new LinkedList<Integer>();
	boolean[] found = new boolean[numVert];
	for (int i = 0; i < network.size(); i++)
		found[i] = false;
	int[] pathIncr = new int[numVert];
	waiting.offer(source);
	found[source] = true;
	via[source] = source;
	pathIncr[source] = Integer.MAX_VALUE;

	do {
		int origin = waiting.poll();
		for (Edge e : network.get(origin)) {
			int destin = e.destin;
			int residue = e.weight - flow[origin][destin];
			if (!found[destin] && residue > 0) {
				via[destin] = origin;
				pathIncr[destin] = Math.min(pathIncr[origin], residue);
				if (destin == sink)
					return pathIncr[destin];
				else {
					waiting.enqueue(destin);
					found[destin] = true;
				}
			}
		}
	} while (!waiting.isEmpty());
	return 0;
}