private static <T extends Comparable<T>> List<T> quicksort(List<T> list) {
	if(list.size()<=1)
		return list ; //base case: sorted
	//could optimize with insertion sort for lists with less 16 elements	
	Collections.shuffle(list) ; //to "mitigate" n^2 worst case
	int pivot_idx = list.size()/2 ; //could be optimized for median
	T pivot = list.get(pivot_idx) ;
	
	List<T> less = new ArrayList<T>(list.size()) ;
	List<T> greater = new ArrayList<T>() ;
	for(int i=0 ; i<list.size() ; i++) 
		if(i!=pivot_idx) {
			T e = list.get(i) ;
			if(e.compareTo(pivot)<=0)
				less.add(e) ;
			else
				greater.add(e) ;
		}
	
	//recursive calls and concatenation of the results 		
	return concatenate(quicksort(less), pivot, quicksort(greater)) ; 
}

private static <T extends Comparable<T>> List<T> concatenate(List<T> left, T middle, List<T> right) {
	List<T> result = left ;
	result.add(middle) ;
	result.addAll(right) ;	
	return result ;
}