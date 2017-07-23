import javax.persistence.*;

/**
 * Thanks for watching this episode! Send any feedback to info@marcobehler.com!
 */
@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    public String getName() {
        return name;
    }
}
