static int knapsack_0_1(int capacity, int n, int[] weights, int[] values) {
	int[][] m = new int[n + 1][capacity + 1];
	for (int i = 0; i < capacity; i++)
		m[0][i] = 0;
	for (int i = 0; i < n; i++)
		m[i][0] = 0;
	for (int i = 0; i < n; i++)
		for (int j = 0; j <= capacity; j++) {
			if (weights[i] > j)
				m[i + 1][j] = m[i][j];
			else
				m[i + 1][j] += Math.max(m[i][j], m[i][j - weights[i]]
						+ values[i]);
		}
	return m[n][capacity];
}

static boolean[] knapTrace(int[][] knapsack, int[] weights, int n) {
	boolean[] solution = new boolean[n];
	int i = knapsack.length - 1;
	int j = knapsack[0].length - 1;
	for (int p = 0; p < n; p++)
		solution[p] = false;
	while (i > 0 && knapsack[i][j] != 0) {
		int val = knapsack[i][j];
		while (val == knapsack[i - 1][j])
			i--;
		solution[i - 1] = true;
		j -= weights[i - 1];
		i--;
	}
	return solution;
}