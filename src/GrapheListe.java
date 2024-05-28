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
        return null;
    }

    public int getIndice(String n){
        int ind =0;
        for(int i =0;i<this.noeuds.size();i++){
            if(this.noeuds.get(i).compareTo(n)==0){
                ind = i;
            }
        }
        return ind;
    }
}
