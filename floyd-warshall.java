void go(List<Edge> edges, int nvertices) {
	//(MAXINT/2)-1 to avoid overflow when we sum two non-existing edges
	int MAXINT = (Integer.MAX_VALUE/2)-1 ;

	//initialize matrix
	int[][] adjency_matrix = new int[nvertices+1][nvertices+1] ;	
	for(int i=0 ; i<adjency_matrix.length ; i++) 
		Arrays.fill(adjency_matrix[i], MAXINT) ; 
	
	//fill matrix
	for(Edge e : edges)
		adjency_matrix[e.src][e.dst] = e.weight ;
	
	floydWarshall(adjency_matrix, nvertices) ;
	
	//...do something with the resulting matrix
}


void floydWarshall(int[][] adjency_matrix, int nvertices) {
	//k -> middle path between i and j
	for(int k=1 ; k<=nvertices ; k++)
		for(int i=1 ; i<=nvertices ; i++)
			for(int j=1 ; j<=nvertices ; j++) {
				int dist_through_k = adjency_matrix[i][k]+adjency_matrix[k][j] ;
				//if path through k is lower than previous direct path, update
				if(dist_through_k < adjency_matrix[i][j]) 
					adjency_matrix[i][j] = dist_through_k ;
			}
}