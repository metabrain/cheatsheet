static boolean[] primes(int upTo) {
	boolean[] sieve = new boolean[upTo];
	Arrays.fill(sieve, true);
	
	sieve[0] = sieve[1] = false;
	for(int i = 2; i * i < upTo; i++)
		if(sieve[i])
			for(int j = i * i; j < upTo; j += i)
				sieve[j] = false;
				
	return sieve;
}