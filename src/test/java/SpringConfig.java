import org.h2.jdbcx.JdbcDataSource;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
@Configuration
@EnableTransactionManagement
public class SpringConfig {


    @Bean
    public SeriesService service() {
        return new SeriesService();
    }

    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:~/test");
        ds.setUser("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean result = new LocalSessionFactoryBean();
        result.setDataSource(dataSource());
        result.setAnnotatedClasses(Episode.class, Series.class);

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(Environment.DIALECT, H2Dialect.class.getName());
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
        hibernateProperties.setProperty(Environment.SHOW_SQL, "true");
        hibernateProperties.setProperty(Environment.FORMAT_SQL, "true");

        result.setDataSource(dataSource());
        result.setHibernateProperties(hibernateProperties);
        return result;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory().getObject());
    }


}
