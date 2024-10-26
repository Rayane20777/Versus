package versus.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String inGameName;

    @NotNull
    @Min(13) @Max(99)
    private int age;

    @ManyToOne
    private Team team;


    // Constructors
    public Player() {
    }

    public Player(String name, String inGameName, int age) {
        this.name = name;
        this.inGameName = inGameName;
        this.age = age;
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

    public @NotNull @Size(min = 3, max = 50) String getInGameName() {
        return inGameName;
    }

    public void setInGameName(@NotNull @Size(min = 3, max = 50) String inGameName) {
        this.inGameName = inGameName;
    }

    @NotNull
    @Min(13)
    @Max(99)
    public int getAge() {
        return age;
    }

    public void setAge(@NotNull @Min(13) @Max(99) int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
