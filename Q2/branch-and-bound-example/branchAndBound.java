import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class branchAndBound {
    public static boolean getMaiorItem(item a, item b){
        return (a.valor/a.peso) > b.valor/b.peso;
    }

    public static int getLimiteSuperiorMaximo(no a, int valor, float peso, item itens[]){
        if (a.peso >= peso){
            return 0;
        }

        int lucroVinculado = a.lucro;
        int j;
        float pesoTotal = a.peso;

        for(j = a.index + 1; j < valor && pesoTotal + itens[j].peso <= peso; j++){
            pesoTotal +=  itens[j].peso;
            lucroVinculado += itens[j].valor;
        }

        if (j < valor){
            lucroVinculado += (peso - pesoTotal) * (itens[j].valor / itens[j].peso);
        }

        return lucroVinculado;
    }

    public static float getMaximoLucro(float peso, item itens[], int n){
        Arrays.sort(itens);

        Queue<no> nos = new PriorityQueue<no>();
        no u = new no(-1, 0, 0, 0);
        no v = new no(-1, 0, 0, 0);

        nos.add(u);
        int lucroMax = 0 ;
        while (! nos.isEmpty()){
            u = nos.peek();
            nos.remove();

            if (u.index == -1){
                v.index = 0;
            }

            if (u.index == n-1){
                continue;
            }

            v.index = u.index + 1;
            v.peso = u.peso + itens[v.index].peso;
            v.lucro = u.lucro + itens[v.index].valor;

            if (v.peso <= peso && v.lucro > lucroMax){
                lucroMax = v.lucro;
            }

            v.limiteSuperiorDoLucro = getLimiteSuperiorMaximo(v, n, peso, itens);
            if (v.limiteSuperiorDoLucro > lucroMax){
                nos.add(v);
            }

            v.peso = u.peso;
            v.lucro = u.lucro;
            v.limiteSuperiorDoLucro = getLimiteSuperiorMaximo(v, n, peso, itens);

            if (v.limiteSuperiorDoLucro > lucroMax){
                nos.add(v);
            }

        }
        return lucroMax;

    }

    public static void main(String[] args) {
        int peso = 10; //peso m'ximo que a mochila é capaz de carregar
        ArrayList<item> itens= new ArrayList(10);

        itens.add(new item(2, 40));
        itens.add(new item(3, 50));
        itens.add(new item(7, 100));
        itens.add(new item(3, 30));
        itens.add(new item(5, 95));

        System.out.println("A capacidade máxima é de " + getMaximoLucro(peso, itens, itens.size()));
    }
}
