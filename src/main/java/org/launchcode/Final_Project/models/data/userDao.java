package org.launchcode.Final_Project.models.data;


import org.launchcode.Final_Project.models.user;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface userDao extends CrudRepository<user, Integer> {

}
