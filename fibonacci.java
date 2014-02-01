static long fib(int n) {
	long[] fibs = new long[n+1];
	fibs[0] = 1;
	fibs[1] = 1;
	for(long i = 2; i <= n; i++) 
		fibs[i] = fibs[i-1]+fibs[i-2];
	return fibs[n];
}