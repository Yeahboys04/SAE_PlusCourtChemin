import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrapheListTest {
    GrapheListe g;

    @BeforeEach
    public void init() {
        ArrayList<String> noeuds = new ArrayList<>();
        noeuds.add("A"); noeuds.add("B");

        ArrayList<Arcs> listArcs = new ArrayList<>();
        Arcs arcsA = new Arcs();
        arcsA.ajouterArc(new Arc("B", 2));

        Arcs arcsB = new Arcs();
        arcsB.ajouterArc(new Arc("A", 5));

        listArcs.add(arcsA); listArcs.add(arcsB);

        g = new GrapheListe(noeuds, listArcs);
    }

    @Test
    public void testListeNoeuds() {
        ArrayList<String> liste = g.listeNoeuds();
        assertEquals("A", liste.getFirst(), "Le premier nœud devrait être A. ");
        assertEquals("B", liste.getLast(), "Le dernier nœud devrait être B. ");
    }

    @Test
    public void testSuivants() {
        List<Arc> liste = g.suivants("A");
        assertEquals("B", liste.getFirst().getDest(), "Le nœud suivant devrait être B. ");
        assertEquals(2, liste.getFirst().getCout(), "Le nœud suivant devrait être à 3 de distance. ");
    }

    @Test
    public void testGetIndice() {
        assertEquals(0, g.getIndice("A"), "Le nœud A devrait être le 0-ième de la liste. ");
    }

    @Test
    public void testAjouterArc() {
        g.ajouterArc("A", "A", 0.5);
        List<Arc> liste = g.suivants("A");
        assertEquals("B", liste.getFirst().getDest(), "Le nœud suivant devrait être B. ");
        assertEquals(2, liste.getFirst().getCout(), "Le nœud suivant devrait être à 3 de distance. ");
        assertEquals("A", liste.getLast().getDest(), "Le nœud suivant devrait être A. ");
        assertEquals(0.5, liste.getLast().getCout(), "Le nœud suivant devrait être à 0.5 de distance. ");
        System.out.println(g);
    }
}
