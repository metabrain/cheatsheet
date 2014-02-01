	public static List<Integer> factorize(int n) throws InterruptedException {
		ArrayList<Integer> list = new ArrayList<Integer>(100) ;
		int sqrt = (int) Math.sqrt(n) ;
		while(n!=1) {
			for(int i = 0 ; i<MAX; i++) { 
			//MAX until where the primes are calculated in the primes[] array
				if(n%primes[i]==0) {
					n = n/primes[i] ;
					sqrt = (int) Math.sqrt(n) ;
					list.add(primes[i]) ;
					break ;
				}
				if(primes[i]>sqrt) {
					list.add(n) ;
					n = 1 ;
					break ;
				}
			}
		}
		return list ;
	}