static void dfs(int origin, List<List<Integer>> graph) {
	Stack<Integer> stack = new Stack<Integer>();
	stack.add(origin);
	boolean[] visited = new boolean[graph.size()];
	while (!stack.isEmpty()) {
		Integer v = stack.pop();
		visited[v] = true;
		for (Integer out : graph.get(v))
			if (!visited[out]) 
				stack.push(out);
	}
}

