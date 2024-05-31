import java.io.IOException;

public class ComparerAlgo {
    public static void main(String[] args) throws IOException {
        double start = System.nanoTime();
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("D","C",10);
        graphe.ajouterArc("A","B",12);
        graphe.ajouterArc("A","D",87);
        graphe.ajouterArc("E","D",43);
        graphe.ajouterArc("B","E",11);
        graphe.ajouterArc("C","A",19);
        System.out.println(graphe.toString());
        double finish = System.nanoTime();
        System.out.println("Construction du graphe : " + (finish - start)/1000 + " µs");


        BellmanFord bf = new BellmanFord();
        Algorithme d = new Dijkstra();

        start = System.nanoTime();
        Valeur v1 = bf.resoudre(graphe, "A");
        finish = System.nanoTime();
        System.out.println("Bellman-Ford : " + (finish - start)/1_000 + " µs");

        start = System.nanoTime();
        Valeur v3 = d.resoudre(graphe, "A");
        finish = System.nanoTime();
        System.out.println("Dijkstra : " + (finish - start)/1_000 + " µs");
    }
}
