static Pair<int[], int[]> bellmanFord(Edge[] edges, int origin,
		int n) throws NegativeWeightCycleException{
	int[] via = new int[n];
	int[] length = new int[n];
	for(int i = 0; i < n; i++) {
		via[i] = -1;
		length[i] = Integer.MAX_VALUE;
	}
	length[origin] = 0;
	via[origin] = origin;
	boolean changes = false;
	for (int i = 1; i < n; i++) {
		changes = updateLengths(edges, length);
		if (!changes)
			break;
	}
	// Negative-weight cycles detection.
	if (changes && updateLengths(edges, length))
		throw new NegativeWeightCycleException();
	else
		return new Pair<int[], int[]>(length, via);
}

static boolean updateLengths(Edge[] edges, int[] length, int[] via) {
	boolean changes = false;
	for (Edge e : edges) {
		int startPoint = e.origin;
		int endPoint = e.destin;
		if (length[startPoint] < Integer.MAX_VALUE) {
			int newLength = length[startPoint] + e.weigth;
			if (newLength < length[endPoint]) {
				length[endPoint] = newLength;
				via[endPoint] = endPoints[startPoint];
				changes = true;
			}
		}
	}
	return changes;
}