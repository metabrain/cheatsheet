static lonb mod(long x, long m) {
	return x % m + (x < 0) ? m : 0;
}

// Acha a menor solucao nao negativa de a * x = b. Retorna -1 se for impossivel
static long solve_mod(long a, long b, long m) {
	if (m < 0)
		return solve_mod(a, b, -m);
	else if(a < 0 || a >= m || b < 0 || b >= m) 
		return solve_mod(mod(a, m), mod(b, m), m);
	else {
		long[] t = extended_gcd(a, m);
		long d = t[0] * a + t[1] * m;
		if(b % d != 0) 
			return -1;
		else
			return mod(t[0] * (b / d), m);
	}
}