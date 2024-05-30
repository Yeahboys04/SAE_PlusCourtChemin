import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class DijkstraTest {
    GrapheListe graphe;

    @BeforeEach
    public void init(){
        graphe = new GrapheListe();
        graphe.ajouterArc("D", "C", 10);
        graphe.ajouterArc("A", "B", 12);
        graphe.ajouterArc("A", "D", 87);
        graphe.ajouterArc("E", "D", 43);
        graphe.ajouterArc("B", "E", 11);
        graphe.ajouterArc("C", "A", 19);
    }

    @Test
    public void testResoudreCheminMin() {
        Algorithme d = new Dijkstra();
        Valeur v = d.resoudre(graphe, "A");
        ArrayList<String> res = new ArrayList<>(v.calculerChemin("C"));
        ArrayList<String> chemin = new ArrayList<>(List.of("A", "B", "E", "D", "C"));
        assertEquals(chemin, res);
    }

    @Test
    public void testResoudreCheminMinVersE() {
        Algorithme d = new Dijkstra();
        Valeur v = d.resoudre(graphe, "A");
        ArrayList<String> res = new ArrayList<>(v.calculerChemin("E"));
        ArrayList<String> chemin = new ArrayList<>(List.of("A", "B", "E"));
        assertEquals(chemin, res);
    }

    @Test
    public void testNoeudNonConnecte() {
        Algorithme d = new Dijkstra();
        graphe.ajouterArc("F", "G", 5);
        Valeur v = d.resoudre(graphe, "A");
        assertEquals(Double.MAX_VALUE, v.getValeur("F"));
    }

    @Test
    public void testGraphAvecCycles() {
        Algorithme d = new Dijkstra();
        graphe.ajouterArc("C", "E", 8);
        graphe.ajouterArc("E", "A", 7);
        Valeur v = d.resoudre(graphe, "A");
        ArrayList<String> res = new ArrayList<>(v.calculerChemin("E"));
        ArrayList<String> chemin = new ArrayList<>(List.of("A", "B", "E"));
        assertEquals(chemin, res);
    }
}
