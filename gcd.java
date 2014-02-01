static int gcd(int a, int b) {
	while(b != 0) {
		int t = b;
		b = a % b;
		a = t;
	}
	return a;
}

// algoritmo de euclides extendido
static long[] extended_gcd(int a, int b) {
	int x = 0, lastY = x;
	int lastX = 1, y = lastX;
	while(b != 0) {
		int quotient = a / b;
		int tmp = a % b;
		a = b;
		b = tmp;
		
		tmp = lastX - quotient*x;
		lastX = x;
		x = tmp;
		
		tmp = lastY - quotient*y;
		lastY = y;
		y = tmp;
	}
	return new int[]{lastX, lastY};
}

