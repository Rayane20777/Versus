package versus.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    private LocalDate attributionDate = LocalDate.now();

    private String rank;

    @OneToMany(mappedBy = "prize")
    private Set<Team> teams;


    // Constructors
    public Prize() {
    }

    public Prize(int amount, LocalDate attributionDate, String rank) {
        this.amount = amount;
        this.attributionDate = attributionDate;
        this.rank = rank;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getAttributionDate() {
        return attributionDate;
    }

    public void setAttributionDate(LocalDate attributionDate) {
        this.attributionDate = attributionDate;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
