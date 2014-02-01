class Node {
	int id, index = -1, lowlink;

	public Node(int id) { this.id = id; }

	public String toString() {
		return "[" + id + "]";
	}
}

class Tarjan {

   private int index = 0;
   private Deque<Node> stack = new ArrayDeque<Node>();
   private ArrayList<ArrayList<Node>> SCC = new ArrayList<ArrayList<Node>>();
   public static boolean[] inStack;

   public ArrayList<ArrayList<Node>> tarjan(Node v, ArrayList<ArrayList<Node>> list){
		v.index = index;
		v.lowlink = index;
		index++;
		stack.push(v);
		inStack[v.id] = true;
		for(Node n : list.get(v.id)){
			if(n.index == -1){
				tarjan(n, list);
				v.lowlink = Math.min(v.lowlink, n.lowlink);
			} else if(inStack[n.id]){
				v.lowlink = Math.min(v.lowlink, n.index);
			}
		}
		if(v.lowlink == v.index){
			Node n;
			ArrayList<Node> component = new ArrayList<Node>();
			do{
				n = stack.pop();
				inStack[n.id] = false;
				component.add(n);
			}while(n != v);
			SCC.add(component);
		}
		return SCC;
	}
}