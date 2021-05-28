public int maisAresta(Graph g, int total){
	int count = 0;
	for (int i = 0; i < g.V() ; i++) {
		if(g.adj(i).size() > total)
			count++;
	}
}



public int ArestasMaisPeso(Graph g, int max){
	int count = 0;
	for (int i = 0; i < g.V() ; i++) {
		for (Edge x : g.adj(i)) {
			if(x.weight() > max)
				count++;
		}
	}
	return count;
}

public void dfs(Graph g, int v, int parent){
	marked[v] = true;
	boolean ciclos = false;
	for (int x : g.adj(v)) {
		if(!marked[x]){
			dfs(g, x, v);
			edgeTo[x] = v;
		}else if(x != parent){
			ciclos = true;
		}
	}
}

public void dfs(Graph g, int v, int parent){
	marked[v] = true;
	boolean ciclos = false;
	if(parent.color != red)
		v.color = red;
	for (int x : g.adj(v)) {
		if(!marked[x]){
			dfs(g, x, v);
			edgeTo[x] = v;
		}
	}
}

public void bfs(Graph g, int v){
	QUEUE <Integer> q = new Queue<Integer>();
	q.enqueue(v);
	marked[v] = true;
	distTo[v] = 0;
	while(!q.isEmpty()){
		int x = q.dequeue();
		for(int w : g.adj(x)){
			if(!marked[w]){
				q.enqueue(w);
				marked[w] = true;
				distTo[w] = distTo[x] +1;
				edgeTo[w] = x;
			}
		}
	}
}

1 0
2 1
3 3
4 6
5 10
6 15
7 21
8 28
9 36

public void dfs(Graph G, int v, int parent){
	bollean ciclico = false;
	marked[v] = true;
	for(int e: G.adj(v)){
		if(!marked(e)){
			dfs(G, e, v); //v = e, parent = v
			edgeTo[e] = v;
		else if(e != parent)
			ciclico = true;
		}
	}
}

