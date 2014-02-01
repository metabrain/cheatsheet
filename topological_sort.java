public static void topologicalSort(List<List<Integer>> graph,
			List<List<Integer>> graphIncident) {
		Queue<Integer> ready = new ArrayBlockingQueue<Integer>(graph.size());
		int[] inCounter = new int[graph.size()];
		for (int i = 0; i < graph.size(); i++) {
			inCounter[i] = graphIncident.get(i).size();
			if (inCounter[i] == 0) {
				ready.add(i);
			}
		}
		while (!ready.isEmpty()) {
			Integer vertex = ready.poll();
			//TREAT(vertex);
			for (Integer w : graph.get(vertex)) {
				inCounter[w]--;
				if (inCounter[w] == 0)
					ready.add(w);
			}
		}
	}