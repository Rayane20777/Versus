package versus.model;

import versus.model.enums.TournamentStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @ManyToOne
    private Game game;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private int spectatorCount;

    @OneToMany(mappedBy = "tournament")
    private Set<Team> teams;

    @NotNull
    @Min(0)
    private int estimatedDuration;

    @NotNull
    @Min(0)
    private int matchBreakTime;

    @NotNull
    @Min(0)
    private int ceremonyTime;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    // Constructors
    public Tournament() {
    }

    public Tournament(String title, Game game, Date startDate, Date endDate, int spectatorCount, Set<Team> teams, int estimatedDuration, int matchBreakTime, int ceremonyTime, TournamentStatus status) {
        this.title = title;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.spectatorCount = spectatorCount;
        this.teams = teams;
        this.estimatedDuration = estimatedDuration;
        this.matchBreakTime = matchBreakTime;
        this.ceremonyTime = ceremonyTime;
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSpectatorCount() {
        return spectatorCount;
    }

    public void setSpectatorCount(int spectatorCount) {
        this.spectatorCount = spectatorCount;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public int getMatchBreakTime() {
        return matchBreakTime;
    }

    public void setMatchBreakTime(int matchBreakTime) {
        this.matchBreakTime = matchBreakTime;
    }

    public int getCeremonyTime() {
        return ceremonyTime;
    }

    public void setCeremonyTime(int ceremonyTime) {
        this.ceremonyTime = ceremonyTime;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus status) {
        this.status = status;
    }
}
