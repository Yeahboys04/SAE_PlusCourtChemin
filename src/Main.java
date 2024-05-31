import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        GrapheListe graphe = new GrapheListe();
        graphe.ajouterArc("D","C",10);
        graphe.ajouterArc("A","B",12);
        graphe.ajouterArc("A","D",87);
        graphe.ajouterArc("E","D",43);
        graphe.ajouterArc("B","E",11);
        graphe.ajouterArc("C","A",19);
        System.out.println(graphe.toString());

        BellmanFord bf = new BellmanFord();
        Valeur v = bf.resoudre(graphe, "A");
        System.out.println(v);
        System.out.println(v.calculerChemin("E"));

        BellmanFord d = new BellmanFord();

        try {
            GrapheListe g = new GrapheListe("graphes/Graphe1.txt");
            Valeur val = d.resoudre(g,"A");
            System.out.println(val.calculerChemin("1"));

        } catch (FileNotFoundException e){
            System.err.println("Le fichier est introuvable");
        }
        catch (IOException e){
            System.err.println("Un probleme est survenu lors de la lecture du graphe");
        }


    }
}