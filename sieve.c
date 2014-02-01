public static void fill_primes(int n) {
		int i, j;
		primes[1]=0; primes[2]=1;
		for(i=3; i<=n; i++) {
			primes[i]=i%2;
		}
		for(i=3; i*i<=n; i+=2)
			if(primes[i]!=0)
				for(j=i*i; j<=n; j+=i)
					primes[j]=0;
		int np=0;
		for(i=1; i<=n; i++)
			if(primes[i]!=0){
				primes[np++]=i;
			}
		MAX = np-1 ;
	}