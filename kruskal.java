List<Edge> mstKruskal(List<Edge> edges, int numVertices) {
	int mstSize = numVertices - 1;
	DisjointSetForest ds = new DisjointSetForest(numVertices);
	PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
	queue.addAll(edges);
	List<Edge> mst = new ArrayList<Edge>(mstSize);
	while(mst.size() < mstSize) {
		Edge temp = queue.poll();
		int leader1 = ds.find(temp.source);
		int leader2 = ds.find(temp.dest);
		if(leader1 != leader2) {
			mst.add(temp);
			ds.union(leader1, leader2);
		}
	}
	return mst;
}

class Edge implements Comparable<Edge>{
	int origin;
	int destin;
	int weight;

	public Edge(int origin, int destin, int weight) {
		super();
		this.origin = origin;
		this.destin = destin;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (destin != other.destin)
			return false;
		return true;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}