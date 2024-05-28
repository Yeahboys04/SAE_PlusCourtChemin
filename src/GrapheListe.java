import java.util.ArrayList;
import java.util.List;

public class GrapheListe implements Graphe{
    ArrayList<String> noeuds;
    ArrayList<Arcs> adjacence;

    @Override
    public List<String> listeNoeuds() {
        return null;
    }

    @Override
    public List<Arc> suivants(String n) {
        int nb = getIndice(n);
        return adjacence.get(nb).getArcs();
    }

    public int getIndice(String n){
        int ind =0;
        for(int i =0;i<this.noeuds.size();i++){
            if(this.noeuds.get(i).equals(n)){
                ind = i;
            }
        }
        return ind;
    }

    public String toString() {
        String res = "";
        for (String noeud : noeuds) {
            // On ajoute le nœud
            res += noeud + " ->";
            List<Arc> suivants = suivants(noeud);
            for (Arc arc : suivants) {
                // On ajoute les nœuds suivants
                res += ' ' + arc.getDest() + '(' + arc.getCout() + ')';
            }
            res += '\n';
        }
        return res;
    }
}
