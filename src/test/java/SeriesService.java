import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
@Service
public class SeriesService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<Series> getSeries() {
        return sessionFactory.getCurrentSession().createQuery("from Series s", Series.class).list();
    }
}
