package org.launchcode.Final_Project.models.data;

import org.launchcode.Final_Project.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface gamesDao extends CrudRepository<Game, Integer> {
}
