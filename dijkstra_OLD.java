static int[] dijkstra (List<List<Edge>> g, int source) {
	int[] dists = new int [g.size()]; 
	int[] previous = new int [g.size()]; 
	int[] via = new int[g.size()];
	boolean[] visited = new boolean [g.size()];
		
	PriorityQueue<Edge> q = new PriorityQueue<Edge>(g.size()) ;
		for(int i = 0 ; i < g.size() ; i++) {
			dists[i] = Integer.MAX_VALUE ;
			via[i] = -1;
		}
	}
	
	dists[source] = 0 ;
	via[source] = source;
	
	for(int i = 0 ; i < g.size() ; i++) {
		Edge e = new Edge(i, i, dists[i]) ;
		q.add(e);
	}
	
	while(!q.isEmpty()) { 
		Edge u = q.poll() ;
		visited[u.destin] = true ;			
		if(u.weight == Integer.MAX_VALUE)
			break ; 
		
		for(Edge e : g.get(u.destin)) {
			if(!visited[e.destin]) { 
				int new_dist = dists[u.destin] + e.weight ;
				if(new_dist < dists[e.destin]) {					
					q.remove(new Edge(e.source, e.destin, dists[e.destin]));
					dists[e.destin] = new_dist ;
					via[e.destin] = u.destin ;
					q.add(new Edge(e.source, e.destin, dists[e.destin]));
					previous[e.destin] = u.destin ;
				}
			}
		}			
	}	
	return dists ;
}

class Edge {
	int source;
	int weight;
	int destin;

	Edge(int source, int destin, int weight) {
		this.source = source;
		this.destin = dest;
		this.weight = weight;
	}
	
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}