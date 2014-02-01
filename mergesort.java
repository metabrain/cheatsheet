
private static <T extends Comparable<T>> List<T> mergesort(List<T> list) {
	if(list.size()<=1)
		return list ; //base case: sorted 
	
	int middle = list.size()/2 ;
	List<T> left = new ArrayList<T>() ;
	List<T> right = new ArrayList<T>() ;
	for(int i=0 ; i<middle ; i++)
		left.add(list.get(i)) ;
	for(int i=middle ; i<list.size() ; i++)
		right.add(list.get(i)) ;
	
	//recursive mergesort
	left = mergesort(left) ;
	right = mergesort(right) ;
	
	//merge sorted sublists
	return merge(left, right) ;			
}

private static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
	List<T> merged = new ArrayList<T>(left.size()+right.size()) ; 
	int left_idx=0 ;
	int right_idx=0 ;
	while(left_idx<left.size() && right_idx<right.size()) {
		T left_value = left.get(left_idx) ;
		T right_value = right.get(right_idx) ;
		//<= to be stable (we always pick left most element)
		if(left_value.compareTo(right_value)<=0) {
			merged.add(left_value) ;
			left_idx++;
		}
		else {
			merged.add(right_value) ;
			right_idx++ ;
		}				
	}
	//if one list is not yet exausted
	while(left_idx<left.size())	{
		merged.add(left.get(left_idx++)) ;
	}
	while(right_idx<right.size())	{
		merged.add(right.get(right_idx++)) ;
		
	}
	return merged;
}