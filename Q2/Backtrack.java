public class Backtrack {

 public int[][] solucao;

 public Backtrack(int N) {
   
   solucao = new int[N][N];
   
   for (int i = 0; i < N; i++) {
     for (int j = 0; j < N; j++) {
       solucao[i][j] = 0;
     }
   }
   
 }

 public void caminhaLabirinto(int[][] labirinto, int N) {
   if (Caminhos(labirinto, 0, 0, N, "baixo")) {
     print(solucao, N);
   }else{
     System.out.println("Sem mais caminhos!");
   }
 
 }

 public boolean Caminhos(int[][] labirinto, int x, int y, int N, String direction) {
   if(x == N - 1 && y == N - 1){
     solucao[x][y] = 1;
     return true;
   }
   
   if (caminhoLivre(labirinto, x, y, N)) {
     solucao[x][y] = 1; 
 
     if(direction!="sobe" && Caminhos(labirinto, x+1, y, N, "baixo")){ 
       return true;
     }
     if(direction!="esq" && Caminhos(labirinto, x, y+1, N,"dir")){ 
       return true;
     }
     if(direction!="baixo" && Caminhos(labirinto, x–1, y, N, "sobe")){ 
       return true;
     }
     if(direction!="dir" && Caminhos(labirinto, x, y–1, N, "esq")){ 
       return true;
     }
     solucao[x][y] = 0;
     return false;
   }  
   return false;
 }

 public boolean caminhoLivre(int[][] labirinto, int x, int y, int N) {
   if (x >= 0 && y >= 0 && x < N && y < N && labirinto[x][y] != 0) {
     return true;
   }
   return false;
 }
  
 public void print(int [][] solucao, int N){
   
   for (int i = 0; i < N; i++) {
     for (int j = 0; j < N; j++) {
       System.out.print(" " + solucao[i][j]);
     }
     System.out.println();
   }
 }
  
 public static void main(String[] args) {
   int N = 5; // tam Matriz
   
   int[][] labirinto = {{ 1, 0, 1, 1, 0 },
                        { 1, 1, 1, 0, 1 },
                        { 0, 1, 0, 1, 1 },
                        { 1, 1, 1, 1, 1 },
                        { 0, 0, 0, 1, 1 }};
   
   Backtrack btL = new Backtrack(N);
   btL.caminhaLabirinto(labirinto, N);
 }

}
