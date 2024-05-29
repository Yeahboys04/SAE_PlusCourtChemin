import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {
    GrapheListe graphe = new GrapheListe();
    @BeforeEach
    public void init(){
        graphe.ajouterArc("D","C",10);
        graphe.ajouterArc("A","B",12);
        graphe.ajouterArc("A","D",87);
        graphe.ajouterArc("E","D",43);
        graphe.ajouterArc("B","E",11);
        graphe.ajouterArc("C","A",19);
    }
    @Test
    public void test_resoudre(){
        Dijkstra d = new Dijkstra();

    }


}