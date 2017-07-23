import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
@Entity
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "series")
    private List<Episode> episodes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
