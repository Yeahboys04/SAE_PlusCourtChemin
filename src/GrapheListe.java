import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe GrapheListe qui représente une liste de sommets, et la liste des sommets associés.
 * attributs : noeuds, adjacence
 */
public class GrapheListe implements Graphe {
    private ArrayList<String> noeuds;
    private ArrayList<Arcs> adjacence;

    /**
     * Constructeur à partir d'une liste de nœuds et d'une liste d'arcs.
     * @param n ArrayList<String> liste des nœuds
     * @param a ArrayList<Arcs> liste des arcs
     */
    public GrapheListe(ArrayList<String> n, ArrayList<Arcs> a) {
        this.noeuds = n;
        this.adjacence = a;
    }

    /**
     * Constructeur par défaut de GrapheListe
     */
    public GrapheListe() {
        this.noeuds = new ArrayList<String>();
        this.adjacence = new ArrayList<Arcs>();


    }

    /**
     * Constructeur à partir d'un fichier texte
     * @param nom chemin du fichier à lire
     * @throws IOException si le fichier n'est pas trouvé
     */
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

    /**
     * renvoie la liste des nœuds du graphe
     * @return ArrayList<String> liste des nœuds
     */
    @Override
    public ArrayList<String> listeNoeuds() {
        return noeuds;
    }

    /**
     * récupère la liste des arcs partant du nœud n passé en paramètre
     * @param n nœud d'où les arcs à récupérer doivent partir
     * @return List<Arc> la liste des arcs qui partent de n
     */
    @Override
    public List<Arc> suivants(String n) {
        int nb = getIndice(n);
        return adjacence.get(nb).getArcs();
    }

    /**
     * renvoie l'indice du nœud n passé en paramètre
     * @param n nœud dont on veut récupérer l'indice
     * @return int l'indice du nœud n
     */
    public int getIndice(String n) {
        int ind = -1;
        for (int i = 0; i < this.noeuds.size(); i++) {
            if (this.noeuds.get(i).equals(n)) {
                ind = i;
            }
        }
        return ind;
    }

    /**
     * @return String Une représentation en String du graphe
     */
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

    /**
     * Ajoute l'arc avec les attributs passés en paramètre au graphe.
     * Si le sommet depart ou destination n'est pas déjà dans le graphe, il y est ajouté.
     * @param depart String le nœud d'où partira l'arc créé
     * @param destination String le nœud où arrivera l'arc créé
     * @param cout double le coût de l'arc entre les deux sommets
     */
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
