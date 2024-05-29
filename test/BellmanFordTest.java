import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {
    GrapheListe g;

    @BeforeEach
    public void init() {
        ArrayList<String> noeuds = new ArrayList<>();
        noeuds.add("A"); noeuds.add("B"); noeuds.add("C");

        ArrayList<Arcs> listArcs = new ArrayList<>();
        Arcs arcsA = new Arcs();
        arcsA.ajouterArc(new Arc("B", 5));
        arcsA.ajouterArc(new Arc("C", 1));

        Arcs arcsB = new Arcs();
        arcsB.ajouterArc(new Arc("A", 3));

        Arcs arcsC = new Arcs();
        arcsC.ajouterArc(new Arc("B", 2));

        listArcs.add(arcsA); listArcs.add(arcsB); listArcs.add(arcsC);

        g = new GrapheListe(noeuds, listArcs);
    }

    @Test
    void resoudre() {
        BellmanFord b = new BellmanFord();
        Valeur v = b.resoudre(g, "A");
        assertEquals(0, v.getValeur("A"), "La distance de A à A devrait être 0. ");
        assertNull(v.getParent("A"), "A ne devrait pas avoir de parents. ");
        assertEquals(3, v.getValeur("B"), "La distance entre A et B devrait être 3. ");
        assertEquals("C", v.getParent("B"), "Le parent de B devrait être C. ");
        assertEquals(1, v.getValeur("C"), "La distance entre A et C devrait être 1. ");
        assertEquals("A", v.getParent("C"), "Le parent de C devrait être A. ");
    }
}