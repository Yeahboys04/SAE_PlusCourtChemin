import java.io.IOException;

public class ComparerAlgo {
    public static void main(String[] args) throws IOException {
        long start = System.nanoTime();
        GrapheListe graphe = new GrapheListe("graphes/Graphe905.txt");
        long finish = System.nanoTime();
        System.out.println("Construction du graphe : " + (finish - start)/1_000_000 + " ms");


        BellmanFord bf = new BellmanFord();
        Algorithme d = new Dijkstra();

        start = System.nanoTime();
        Valeur v1 = bf.resoudre(graphe, "A");
        finish = System.nanoTime();
        System.out.println("Bellman-Ford : " + (finish - start)/1_000_000 + " ms");

        start = System.nanoTime();
        Valeur v3 = d.resoudre(graphe, "A");
        finish = System.nanoTime();
        System.out.println("Dijkstra : " + (finish - start)/1_000_000 + " m s");
    }
}
