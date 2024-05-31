import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrapheListe implements Graphe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    public GrapheListe(ArrayList<String> n, ArrayList<Arcs> a) {
        this.noeuds = n;
        this.adjacence = a;
    }

    public GrapheListe() {
        this.noeuds = new ArrayList<String>();
        this.adjacence = new ArrayList<Arcs>();


    }

    public GrapheListe(String nom) throws IOException {
        this.noeuds = new ArrayList<>();
        this.adjacence = new ArrayList<>();
        String line;
        BufferedReader f = new BufferedReader(new FileReader(nom));
        line = f.readLine();
        while (line!=null){
            String[] cout = line.split("\t");
            this.ajouterArc(cout[0],cout[1],Double.parseDouble(cout[2]));
            line = f.readLine();
        }
    }

    @Override
    public ArrayList<String> listeNoeuds() {
        return noeuds;
    }

    @Override
    public List<Arc> suivants(String n) {
        int nb = getIndice(n);
        return adjacence.get(nb).getArcs();
    }

    public int getIndice(String n) {
        int ind = -1;
        for (int i = 0; i < this.noeuds.size(); i++) {
            if (this.noeuds.get(i).equals(n)) {
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

    public void ajouterArc(String depart, String destination, double cout) {
        int indArcDep = getIndice(depart);
        int indArcDes = getIndice(destination);
        if (indArcDep == -1) {
            this.noeuds.add(depart);
            this.adjacence.add(new Arcs());
            indArcDep = getIndice(depart);
        }
        if (indArcDes == -1) {
            this.noeuds.add(destination);
            this.adjacence.add(new Arcs());
            indArcDes = getIndice(destination);
        }
        Arcs dep = this.adjacence.get(indArcDep);
        Arc des = new Arc(destination, cout);
        dep.ajouterArc(des);

    }
}
