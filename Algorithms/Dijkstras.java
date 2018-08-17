import java.util.*;
// topo sortin
public class Dijkstras {
	   // A utility function to print the constructed distance array
	int n=9;
	//List<Integer> adj;
//	Dijkstras(){
//		adj = new LinkedList[n];
//	}
	int mindist(int dist[], boolean[] visited)
	{
		int min_i=-1,min=Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if(visited[i]==false && dist[i]<=min) {
				min=dist[i];
				min_i=i;}}
		return min_i;
	}
	
	void dijkstra(int graph[][], int a) {
		int[] dist = new int[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i]=false;
		}
		dist[a] = 0;
		for (int i = 0; i < n-1; i++) {
		int u = mindist(dist,visited);
		visited[u] = true;
		for (int v = 0; v < n; v++)
			if (!visited[v] && graph[u][v]!=0 &&
                dist[u] != Integer.MAX_VALUE &&
                dist[u]+graph[u][v] < dist[v])
					dist[v] = dist[u]+graph[u][v];
		}
		printSolution(dist, n);
	}
	
    void printSolution(int dist[], int n)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < n; i++)
            System.out.println(i+" tt "+dist[i]);
    }
    public static void main(String args[])
    {
    	 /* Let us create the example graph discussed above */
        int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
                                   {4, 0, 8, 0, 0, 0, 0, 11, 0},
                                   {0, 8, 0, 7, 0, 4, 0, 0, 2},
                                   {0, 0, 7, 0, 9, 14, 0, 0, 0},
                                   {0, 0, 0, 9, 0, 10, 0, 0, 0},
                                   {0, 0, 4, 14, 10, 0, 2, 0, 0},
                                   {0, 0, 0, 0, 0, 2, 0, 1, 6},
                                   {8, 11, 0, 0, 0, 0, 1, 0, 7},
                                   {0, 0, 2, 0, 0, 0, 6, 7, 0}
                                  };
         Dijkstras t = new Dijkstras();
         t.dijkstra(graph, 0);
    }
}
