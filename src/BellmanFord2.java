import java.util.List;

public class BellmanFord2 implements Algorithme {

    public Valeur resoudre(Graphe g, String depart) {
        // Initialisation de la fonction de valeur
        Valeur v = new Valeur();

        // Initialisation des valeurs de tous les noeuds à Double.MAX_VALUE et des parents à null
        List<String> noeuds = g.listeNoeuds();
        for (String noeud : noeuds) {
            v.setValeur(noeud, Double.MAX_VALUE);
            v.setParent(noeud, null);
        }

        // Définir la valeur du départ à 0
        v.setValeur(depart, 0);

        // Algorithme du point fixe (Bellman-Ford) avec une boucle while
        boolean modifie = true;
        while (modifie) {
            modifie = false;
            for (String noeud : noeuds) {
                List<Arc> arcs = g.suivants(noeud);
                for (Arc arc : arcs) {
                    double nouvelleValeur = v.getValeur(noeud) + arc.getCout();
                    if (nouvelleValeur < v.getValeur(arc.getDest())) {
                        v.setValeur(arc.getDest(), nouvelleValeur);
                        v.setParent(arc.getDest(), noeud);
                        modifie = true;
                    }
                }
            }
        }

        return v;
    }
}
