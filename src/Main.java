import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Arcs a = new Arcs();
        Arcs b = new Arcs();
        Arcs c = new Arcs();
        Arcs d = new Arcs();
        Arcs e = new Arcs();

        d.ajouterArc(new Arc("C",10));
        a.ajouterArc(new Arc("B",12));
        d.ajouterArc(new Arc("B",23));
        a.ajouterArc(new Arc("D",87));
        e.ajouterArc(new Arc("D",43));
        b.ajouterArc(new Arc("E",11));
        c.ajouterArc( new Arc("A",19));


        ArrayList<String> arcs = new ArrayList<>();
        arcs.add("A");
        arcs.add("B");
        arcs.add("C");
        arcs.add("D");
        arcs.add("E");

        ArrayList<Arcs> listArcs = new ArrayList<>();
        listArcs.add(a);
        listArcs.add(b);
        listArcs.add(c);
        listArcs.add(d);
        listArcs.add(e);

        GrapheListe graphe = new GrapheListe(arcs,listArcs);
        System.out.println(graphe.toString());

        BellmanFord bf = new BellmanFord();
        Valeur v = bf.resoudre(graphe, "A");
        System.out.println(v);
    }
}
