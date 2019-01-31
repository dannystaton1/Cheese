package org.launchcode.cheesemvc.models.data;


import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

// Dao stands for data access object
// interface to access cheese class and how it will interact with database

// Crud repository specifies methods to get data in and out
@Repository // Tells spring that this is a repo and that it needs to manage this.
@Transactional // All the methods in the interface should be wrapped in a transaction
public interface CheeseDao extends CrudRepository<Cheese,Integer> {

}
