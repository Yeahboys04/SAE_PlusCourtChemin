import java.util.ArrayList;
import java.util.List;

public class BellmanFord {
    public Valeur resoudre(GrapheListe g, String depart) {
        Valeur v = new Valeur();
        v.setValeur(depart, 0);
        ArrayList<Double> lDistanceNoeuds = new ArrayList<Double>();
        ArrayList<Double> nvLDistanceNoeuds = new ArrayList<Double>();
        List<String> listeNoeuds = g.listeNoeuds();
        int nbNoeuds = listeNoeuds.size();
        for (int i = 0; i < nbNoeuds; i++) {
            v.setValeur(listeNoeuds.get(i), Double.MAX_VALUE);
            lDistanceNoeuds.add(Double.MAX_VALUE);
        }
        lDistanceNoeuds.set(g.getIndice(depart), 0.0);
        boolean pareil = false;
        majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);
        while (! pareil) {
            int n = 0;
            while (lDistanceNoeuds.get(n).equals(nvLDistanceNoeuds) && n < lDistanceNoeuds.size()) {
                n++;
            }
            if (n == lDistanceNoeuds.size()) {
                pareil = true;
            }
            lDistanceNoeuds = nvLDistanceNoeuds;
            majDistances(g, nbNoeuds, listeNoeuds, lDistanceNoeuds, nvLDistanceNoeuds, v);
        }
    }

    private void majDistances(GrapheListe g, int nbNoeuds, List<String> listeNoeuds, ArrayList<Double> lDistanceNoeuds, ArrayList<Double> nvLDistanceNoeuds, Valeur v) {
        for (int i = 0; i < nbNoeuds; i++) {
            List<Arc> suivants = g.suivants(listeNoeuds.get(i));
            for (Arc arc : suivants) {
                double distance = arc.getCout() + lDistanceNoeuds.get(i);
                if (distance < lDistanceNoeuds.get(g.getIndice(arc.getDest()))) {
                    nvLDistanceNoeuds.set(g.getIndice(arc.getDest()), distance);
                    v.setValeur(arc.getDest(), distance);
                    v.setParent(listeNoeuds.get(i), arc.getDest());
                }
            }
        }
    }
}

/*
si on commence par A :
B est placé à 12 et D est passé à 87
puis E est passé à 12 + 11 (23)
puis C est passé à 87 + 10 (97)
puis on compare E + 43 = 23 + 43 = 66 avec D (87)
comme passer par E est plus court D est passé à 66
on recalcule C 66 + 10 = 76
 */