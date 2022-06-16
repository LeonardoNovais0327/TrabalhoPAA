import java.util.*;

class Q1b{

	static final int N = 100000;

	@SuppressWarnings("unchecked")
	static Vector<Integer>[] grafo = new Vector[N];
	@SuppressWarnings("unchecked")
	static Vector<Integer>[] ciclos = new Vector[N];
	static int numCiclo;

	static void cicloDfs(int u, int p, int[] corV, int[] mark, int[] pai) {
		if (corV[u] == 2) {
			return;
		}

		if (corV[u] == 1) {
			numCiclo++;
			int cur = p;
			mark[cur] = numCiclo;

			while (cur != u) {
				cur = pai[cur];
				mark[cur] = numCiclo;
			}
			return;
		}

		pai[u] = p;

		corV[u] = 1;

		// dfs no grafo
		for (int v : grafo[u]) {
			if (v == pai[u]) {
				continue;
			}
			cicloDfs(v, u, corV, mark, pai);
		}

		corV[u] = 2;
	}

	static void criaAresta(int u, int v) {
		grafo[u].add(v);
		grafo[v].add(u);
	}

	static void mostraCiclo(int arestas, int mark[]) {
		for (int i = 1; i <= arestas; i++) {
			System.out.println(" Num:" + i + ",valor: "+mark[i]);
			if (mark[i] != 0)
				ciclos[mark[i]].add(i);
		}

		for (int i = 1; i <= numCiclo; i++) {
			System.out.printf("Ciclo %d: ", i);

			for (int x : ciclos[i])
				System.out.printf("%d ", x);

			System.out.println();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < N; i++) {
			grafo[i] = new Vector<>();
			ciclos[i] = new Vector<>();
		}

		criaAresta(1, 2);
		criaAresta(2, 3);
		criaAresta(3, 4);
		criaAresta(4, 6);
		criaAresta(4, 7);
		criaAresta(5, 6);
		criaAresta(3, 5);
		criaAresta(7, 8);
		criaAresta(6, 10);
		criaAresta(5, 9);
		criaAresta(10, 11);
		criaAresta(11, 12);
		criaAresta(11, 13);
		criaAresta(12, 13);


		int[] corV = new int[N];
		int[] pai = new int[N];

		int[] mark = new int[N];

		numCiclo = 0;
		int arestas = 15;

		cicloDfs(1, 0, corV, mark, pai);

		mostraCiclo(arestas, mark);
	}
}