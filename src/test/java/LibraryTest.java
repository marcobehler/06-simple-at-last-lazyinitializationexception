/*
 * This Java source file was generated by the Gradle 'init' task.
 */

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class LibraryTest {

    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
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
        StringBuilder htmlBuilder = new StringBuilder("<html><body><h3>all episodes</h3><ul>");

        // step 1: open up connectino to db...load stuff...close db connection

        List<Episode> episodes;
        try (Session session = sessionFactory.openSession()) {
            List<Series> list = session.createQuery("from Series s", Series.class).list();
            episodes = list.get(0).getEpisodes();
            Hibernate.initialize(episodes);
        }

        // step 2: sometime later....render the html....ouch!

        episodes.forEach(e -> htmlBuilder.append("<li>").append(e.getName()).append("</li>"));

        htmlBuilder.append("</ul></body></html>");
        assertEquals(htmlBuilder.toString(), "<html><body><h3>all episodes</h3><ul><li>our first episode</li></ul></body></html>");
    }


    @Test(expected = LazyInitializationException.class)
    public void spring_test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        SeriesService service = ctx.getBean(SeriesService.class);

        List<Series> list = service.getSeries();  // DB CONNECTION IS CLOSED after this line!!!
        List<Episode> episodes = list.get(0).getEpisodes();

        StringBuilder htmlBuilder = new StringBuilder("<html><body><h3>all episodes</h3><ul>");
        episodes.forEach(e -> htmlBuilder.append("<li>").append(e.getName()).append("</li>"));
        htmlBuilder.append("</ul></body></html>");

        assertEquals(htmlBuilder.toString(), "<html><body><h3>all episodes</h3><ul><li>our first episode</li></ul></body></html>");
    }

    @Test
    public void opensessionInViewFilter() {

    }


    public static class EpisodeServlet extends HttpServlet {

    }

    public static class OpenSessionInViewFilter implements Filter {


        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        }

        @Override
        public void destroy() {

        }
    }


}