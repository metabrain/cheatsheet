private static int[] dijkstra(List<Edge>[] adjencency_list, int source) {
	int n = adjencency_list.length;
	int[] dists = new int[n] ;
	int[] previous = new int[n] ;
	int[] via = new int[n] ;
	boolean[] visited = new boolean[n] ;
	
	PriorityQueue<Edge> q = new PriorityQueue<Main.Edge>(n) ;
	for(int i=0 ; i<n ; i++) { 
		dists[i] = Integer.MAX_VALUE ;
		via[i] = -1 ;
	}
	
	//initial edge
	dists[source] = 0 ;
	via[source] = source ;
	q.add(new Edge(source,source,dists[source])) ; 
	
	while(!q.isEmpty()) {
		Edge u = q.poll() ;
		visited[u.dst] = true ;
			
		for(Edge e : adjencency_list[u.dst]) {
			int new_dist = dists[u.dst] + e.weight ;
			//if dist through 'u' plus 'e' edge weight better than previous best, 
			//	update shortest path
			if(new_dist < dists[e.dst]) {
				q.remove(new Edge(e.src,e.dst,dists[e.dst])) ;
				dists[e.dst] = new_dist ;
				via[e.dst] = u.dst ;
				q.add(new Edge(e.src,e.dst,dists[e.dst]));
				previous[e.dst] = u.dst ;
			}
		}
	}	
	
	return dists;
}

static class Edge implements Comparable<Edge> {
	final int src, dst, weight;

	Edge(int src_, int dst_, int weight_) {
		this.src = src_ ;
		this.dst = dst_ ;
		this.weight = weight_ ;
	}

	@Override
	public int compareTo(Edge o) {
		if(this.weight==o.weight)
			return 0 ;
		else
			return this.weight<o.weight ? -1 : 1 ;
	}
}