import java.util.*;
public class topo {
	int n;
	List<Integer> adj[];
	topo(int v){
		n=v;
		adj = new List[v];
		for(int i=0;i<v;i++)
			adj[i] = new LinkedList<Integer>();
	}
	void addEdge(int v, int w) {
		adj[v].add(w);
	}	
	
	void topoutil(int i,Stack<Integer> s, boolean[] v) {
		v[i] = true;
		Integer j;
		Iterator<Integer> it = adj[i].iterator();
		while(it.hasNext()) {
			j=it.next();
			if(v[j]==true)
				continue;
			topoutil(j,s,v);
		}
		s.push(new Integer(i));
	}
	
	void toposort(){
		Stack<Integer> s = new Stack<Integer>();
		boolean[] v = new boolean[n];
		for(int i=0;i<n;i++)
			v[i] = false;
		for(int i=0;i<n;i++)
			if(v[i] == false)
				topoutil(i,s,v);
		while(!s.isEmpty())
			System.out.println(s.pop());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		topo g=new topo(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        g.toposort();
	}
}
