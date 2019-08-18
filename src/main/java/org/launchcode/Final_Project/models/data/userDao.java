package org.launchcode.Final_Project.models.data;


import org.apache.catalina.User;
import org.launchcode.Final_Project.models.user;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface userDao extends CrudRepository<user, Integer> {

    //added method to grab user data by username, not just ID
    user findByUsername(String username);
}
