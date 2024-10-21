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
    private String ingameName;

    @NotNull
    @Min(13) @Max(99)
    private int age;

    @ManyToOne
    private Team team;


    // Constructors
    public Player() {
    }

    public Player(String name, String ingameName, int age) {
        this.name = name;
        this.ingameName = ingameName;
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

    public @NotNull @Size(min = 3, max = 50) String getIngameName() {
        return ingameName;
    }

    public void setIngameName(@NotNull @Size(min = 3, max = 50) String ingameName) {
        this.ingameName = ingameName;
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
}
