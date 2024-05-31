import java.util.ArrayList;
import java.util.List;

public class MainDijkstra {
    public static void main(String[] args) {
        // Création du graphe par défaut
        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("E", "D", 43);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);

        // Création de l'instance de Dijkstra
        Algorithme dijkstra = new Dijkstra();

        // Calcul des chemins les plus courts à partir de A
        Valeur valeurs = dijkstra.resoudre(graphe, "A");

        // Affichage des chemins pour des nœuds donnés
        afficherChemin(valeurs, "A", "B");
        afficherChemin(valeurs, "A", "C");
        afficherChemin(valeurs, "A", "D");
        afficherChemin(valeurs, "A", "E");



        GrapheListe g2 = new GrapheListe();
        g2.ajouterArc("A","G",6);
        g2.ajouterArc("A","H",2);
        g2.ajouterArc("B","D",4);
        g2.ajouterArc("B","I",3);
        g2.ajouterArc("C","B",3);
        g2.ajouterArc("C","I",5);
        g2.ajouterArc("D","E",3);
        g2.ajouterArc("F","C",3);
        g2.ajouterArc("F","H",1);
        g2.ajouterArc("G","D",2);
        g2.ajouterArc("H","B",1);
        g2.ajouterArc("H","G",3);
        g2.ajouterArc("I","E",3);
        Valeur v2 = dijkstra.resoudre(g2,"A");
        afficherChemin(v2,"A","F");


    }

    public static void afficherChemin(Valeur valeurs, String depart, String arrivee) {
        List<String> chemin = valeurs.calculerChemin(arrivee);
        System.out.print("Chemin le plus court de " + depart + " à " + arrivee + " : ");
        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé.");
        } else {
            for (String noeud : chemin) {
                System.out.print(noeud + " ");
            }
            System.out.println("(Coût total : " + valeurs.getValeur(arrivee) + ")");
        }
    }
}
