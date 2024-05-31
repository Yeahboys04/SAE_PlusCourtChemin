import java.util.ArrayList;
import java.util.List;

public class BellmanFord implements Algorithme {

    /**
     * Résout le problème des plus courts chemins à partir d'un nœud de départ donné avec l'algorithme de Bellman-Ford.
     * @param g      le graphe représenté par une liste d'adjacence
     * @param depart le nœud de départ
     * @return une instance de Valeur contenant les distances minimales et les parents des nœuds
     */
    public Valeur resoudre(Graphe g, String depart) {
        Valeur v = new Valeur();
        List<String> listeNoeuds = g.listeNoeuds();
        int nbNoeuds = listeNoeuds.size();

        // Initialiser les distances de tous les nœuds à l'infini sauf le départ
        for (String noeud : listeNoeuds) {
            v.setValeur(noeud, Double.MAX_VALUE);
        }
        v.setValeur(depart, 0.0);

        // Liste des distances à mettre à jour
        ArrayList<Double> lDistanceNoeuds = new ArrayList<>();
        for (String noeud : listeNoeuds) {
            lDistanceNoeuds.add(v.getValeur(noeud));
        }

        int iterations = 0;
        // Mettre à jour les distances en faisant nbNoeuds - 1 passes
        for (int k = 0; k < nbNoeuds - 1; k++) {
            for (int i = 0; i < nbNoeuds; i++) {
                String noeud = listeNoeuds.get(i);
                List<Arc> suivants = g.suivants(noeud);
                for (Arc arc : suivants) {
                    double nouvelleDistance = v.getValeur(noeud) + arc.getCout();
                    if (nouvelleDistance < v.getValeur(arc.getDest())) {
                        v.setValeur(arc.getDest(), nouvelleDistance);
                        v.setParent(arc.getDest(), noeud);
                    }
                    iterations++;
                }
            }
        }
        System.out.println("Complexité Bellman-Ford : " + iterations);

        // Vérification des cycles négatifs
        for (int i = 0; i < nbNoeuds; i++) {
            String noeud = listeNoeuds.get(i);
            List<Arc> suivants = g.suivants(noeud);
            for (Arc arc : suivants) {
                double nouvelleDistance = v.getValeur(noeud) + arc.getCout();
                if (nouvelleDistance < v.getValeur(arc.getDest())) {
                    throw new IllegalArgumentException("Le graphe contient un cycle de poids négatif");
                }
            }
        }

        return v;
    }
}