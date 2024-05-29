import java.util.ArrayList;
import java.util.List;

/**
 * Classe implémentant l'algorithme de Bellman-Ford pour trouver les plus courts chemins
 * depuis un nœud de départ dans un graphe orienté avec des poids.
 */
public class BellmanFord {

    /**
     * Résout le problème des plus courts chemins à partir d'un nœud de départ donné.
     *
     * @param g      le graphe représenté par une liste d'adjacence
     * @param depart le nœud de départ
     * @return une instance de Valeur contenant les distances minimales et les parents des nœuds
     */
    public Valeur resoudre(GrapheListe g, String depart) {
        Valeur v = new Valeur();
        v.setValeur(depart, 0);  // Initialiser la distance du nœud de départ à 0

        ArrayList<Double> lDistanceNoeuds = new ArrayList<Double>();
        List<String> listeNoeuds = g.listeNoeuds();
        int nbNoeuds = listeNoeuds.size();

        // Initialiser les distances de tous les nœuds à l'infini
        for (int i = 0; i < nbNoeuds; i++) {
            v.setValeur(listeNoeuds.get(i), Double.MAX_VALUE);
            lDistanceNoeuds.add(Double.MAX_VALUE);
        }

        // La distance du nœud de départ à lui-même est 0
        lDistanceNoeuds.set(g.getIndice(depart), 0.0);

        // Créer une copie des distances pour suivre les mises à jour
        ArrayList<Double> nvLDistanceNoeuds = new ArrayList<Double>(lDistanceNoeuds);
        boolean pareil = false;

        // Mettre à jour les distances initiales
        majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);

        // Boucle principale pour vérifier les mises à jour des distances
        while (!pareil) {
            int n = 0;
            // Vérifier si les listes de distances sont identiques
            while (lDistanceNoeuds.get(n).equals(nvLDistanceNoeuds.get(n)) && n < lDistanceNoeuds.size() - 1) {
                n++;
            }
            if (n == lDistanceNoeuds.size() - 1) {
                pareil = true;  // Si toutes les distances sont les mêmes, on arrête la boucle
            }
            // Copier les nouvelles distances dans l'ancienne liste
            for (int i = 0; i < lDistanceNoeuds.size(); i++) {
                lDistanceNoeuds.set(i, nvLDistanceNoeuds.get(i));
            }
            // Mettre à jour les distances à nouveau
            majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);
        }

        v.setValeur(depart, 0.0);  // Réinitialiser la distance du nœud de départ à 0
        return v;  // Retourner les distances et parents des nœuds
    }

    /**
     * Met à jour les distances minimales et les parents des nœuds dans le graphe.
     *
     * @param g                 le graphe représenté par une liste d'adjacence
     * @param nbNoeuds          le nombre de nœuds dans le graphe
     * @param listeNoeuds       la liste des nœuds du graphe
     * @param lDistanceNoeuds   la liste des distances actuelles des nœuds
     * @param nvLDistanceNoeuds la liste des nouvelles distances mises à jour des nœuds
     * @param v                 une instance de Valeur pour stocker les distances et parents
     */
    private void majDistances(GrapheListe g, int nbNoeuds, List<String> listeNoeuds, ArrayList<Double> lDistanceNoeuds, ArrayList<Double> nvLDistanceNoeuds, Valeur v) {
        for (int i = 0; i < nbNoeuds; i++) {
            List<Arc> suivants = g.suivants(listeNoeuds.get(i));  // Obtenir les arcs sortants du nœud actuel
            for (Arc arc : suivants) {
                double distance = arc.getCout() + lDistanceNoeuds.get(i);  // Calculer la distance via cet arc
                if (distance < lDistanceNoeuds.get(g.getIndice(arc.getDest()))) {
                    // Si une distance plus courte est trouvée, mettre à jour
                    nvLDistanceNoeuds.set(g.getIndice(arc.getDest()), distance);
                    v.setValeur(arc.getDest(), distance);
                    v.setParent(arc.getDest(), listeNoeuds.get(i));  // Mettre à jour le parent pour reconstituer le chemin
                }
            }
        }
    }
}
