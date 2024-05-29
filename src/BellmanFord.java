import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    public Valeur resoudre(GrapheListe g, String depart) {
        Valeur v = new Valeur();
        v.setValeur(depart, 0);
        ArrayList<Double> lDistanceNoeuds = new ArrayList<Double>();
        List<String> listeNoeuds = g.listeNoeuds();
        int nbNoeuds = listeNoeuds.size();
        for (int i = 0; i < nbNoeuds; i++) {
            v.setValeur(listeNoeuds.get(i), Double.MAX_VALUE);
            lDistanceNoeuds.add(Double.MAX_VALUE);
        }
        lDistanceNoeuds.set(g.getIndice(depart), 0.0);
        ArrayList<Double> nvLDistanceNoeuds = new ArrayList<Double>(lDistanceNoeuds);
        boolean pareil = false;
        majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);
        while (! pareil) {
            int n = 0;
            while (lDistanceNoeuds.get(n).equals(nvLDistanceNoeuds.get(n)) && n < lDistanceNoeuds.size() - 1) {
                n++;
            }
            if (n == lDistanceNoeuds.size() - 1) {
                pareil = true;
            }
            for (int i = 0; i < lDistanceNoeuds.size(); i++) {
                lDistanceNoeuds.set(i, nvLDistanceNoeuds.get(i));
            }
            majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);
        }
        v.setValeur(depart, 0.0);
        return v;
    }

    private void majDistances(GrapheListe g, int nbNoeuds, List<String> listeNoeuds, ArrayList<Double> lDistanceNoeuds, ArrayList<Double> nvLDistanceNoeuds, Valeur v) {
        for (int i = 0; i < nbNoeuds; i++) {
            List<Arc> suivants = g.suivants(listeNoeuds.get(i));
            for (Arc arc : suivants) {
                double distance = arc.getCout() + lDistanceNoeuds.get(i);
                if (distance < lDistanceNoeuds.get(g.getIndice(arc.getDest()))) {
                    nvLDistanceNoeuds.set(g.getIndice(arc.getDest()), distance);
                    v.setValeur(arc.getDest(), distance);
                    v.setParent(arc.getDest(), listeNoeuds.get(i));
                }
            }
        }
    }
}
