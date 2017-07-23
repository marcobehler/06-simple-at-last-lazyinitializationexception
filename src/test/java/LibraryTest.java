/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class LibraryTest {

    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void testSomeLibraryMethod() {
        try (Session session = sessionFactory.openSession()) {
            List<Series> list = session.createQuery("from Series", Series.class).list();

            List<Episode> episodes = list.get(0).getEpisodes();

            boolean initialized = Hibernate.isInitialized(episodes);
            System.out.println("Did Hibernate _really_ load all episodes? initialized = " + initialized);

            System.out.println("episodes.class = " + episodes.getClass());
        }
    }


    @Test
    public void render_html() {
        StringBuilder htmlBuilder = new StringBuilder("<html><body><ul>");

        try (Session session = sessionFactory.openSession()) {
            List<Series> list = session.createQuery("from Series", Series.class).list();

            List<Episode> episodes = list.get(0).getEpisodes();

            episodes.forEach(e -> htmlBuilder.append("<li>").append(e.getName()).append("</li>"));

            htmlBuilder.append("<li>").append(episod)

            boolean initialized = Hibernate.isInitialized(episodes);
            System.out.println("Did Hibernate _really_ load all episodes? initialized = " + initialized);

            System.out.println("episodes.class = " + episodes.getClass());
        }

        htmlBuilder.append("</ul></body></html>");

        assertEquals(htmlBuilder.toString(), "<html><body><ul><li>My Series Name</li></ul></body></html>");
    }
}