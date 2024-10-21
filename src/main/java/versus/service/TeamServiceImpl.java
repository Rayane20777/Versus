package versus.service;

import versus.repository.interfaces.TeamRepository;
import versus.service.interfaces.TeamService;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

}
