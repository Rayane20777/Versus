package versus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Min(1)
    @Max(10)
    private int difficulty;

    @Min(1)
    private int averageMatchDuration;

    // Constructors
    public Game() {
    }

    public Game(String name, int difficulty, int averageMatchDuration) {
        this.name = name;
        this.difficulty = difficulty;
        this.averageMatchDuration = averageMatchDuration;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull @Size(min = 3, max = 50) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 3, max = 50) String name) {
        this.name = name;
    }

    @Min(1)
    @Max(10)
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(@Min(1) @Max(10) int difficulty) {
        this.difficulty = difficulty;
    }

    @Min(1)
    public int getAverageMatchDuration() {
        return averageMatchDuration;
    }

    public void setAverageMatchDuration(@Min(1) int averageMatchDuration) {
        this.averageMatchDuration = averageMatchDuration;
    }
}
