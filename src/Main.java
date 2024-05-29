import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("D","C",10);
        graphe.ajouterArc("A","B",12);
        graphe.ajouterArc("A","D",87);
        graphe.ajouterArc("E","D",43);
        graphe.ajouterArc("B","E",11);
        graphe.ajouterArc("C","A",19);
        System.out.println(graphe.toString());

        BellmanFord bf = new BellmanFord();
        Valeur v = bf.resoudre(graphe, "A");
        System.out.println(v);
    }
}