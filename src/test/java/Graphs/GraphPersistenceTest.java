package Graphs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphPersistenceTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("graphPU");
        em = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void testCreateGraph() {
        em.getTransaction().begin();

        Graph graph = new Graph("testUser");
        graph.setName("testGraph");
        graph.setDir("testDir");

        Vertex vertex1 = new Vertex("v1");
        Vertex vertex2 = new Vertex("v2");

        graph.addVertex(vertex1.getLabel());
        graph.addVertex(vertex2.getLabel());
        graph.addEdge(vertex1.getLabel(), vertex2.getLabel());

        em.persist(graph);
        em.getTransaction().commit();

        TypedQuery<Graph> query = em.createQuery("SELECT g FROM Graph g WHERE g.name = :name", Graph.class);
        query.setParameter("name", "testGraph");
        List<Graph> result = query.getResultList();

        assertFalse(result.isEmpty());
        assertEquals("testUser", result.get(0).getUser());
    }
}