package org.launchcode.Final_Project.models.data;

import org.launchcode.Final_Project.models.Bonus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface bonusDao extends CrudRepository<Bonus,Integer> {
}
