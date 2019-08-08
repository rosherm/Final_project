package org.launchcode.Final_Project.models.data;

import org.launchcode.Final_Project.models.Game;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface gamesDao extends CrudRepository<Game, Integer> {
}
