private static State a_star(int[][] puzzle) throws InterruptedException {
	PriorityQueue<State> q = new PriorityQueue<State>() ;		
	q.add(new State(puzzle)) ;
	
	if(q.peek().isGoal()) 
		return q.peek() ;
	
	while(!q.isEmpty()) {
		State state = q.poll() ;	
		for(State possible_move : state.generateMoves()) {
			if(possible_move.isGoal())
				return possible_move ;
			else
				if(state.moves.size()+state.heuristic()>50)
					continue ;
				q.add(possible_move);
		}
	}
	
	return null;
}

static class State implements Comparable<State> {
	public List<State> generateMoves()  ...
	private int heuristic() ...

	@Override
	public int compareTo(State arg0) {
		int this_score = this.moves.size()+this.heuristic() ; 
		int arg0_score = arg0.moves.size()+arg0.heuristic() ; 
		if(this_score==arg0_score) 	return 0 ;
		else return this_score<arg0_score ? -1 : 1 ;
	}
}