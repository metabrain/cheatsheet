boolean finished = false ;

backtrack(int a[], int depth) {
	if(is_solution(a,depth)) {
		process_solution(a,depth) ;
		finished = true ;
	}
	else {
		List children = construct_children(a,depth) ;
		for(C c : children) {
			make_move(a,depth) ;
			backtrack(a,depth+1) ;
			unmake_move(a,depth) ;

			if(finished) 
				return ; /* terminate early */
		}
	}
}