package org.launchcode.cheesemvc.models.data;

import org.launchcode.cheesemvc.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository // Tells spring that this is a repo and that it needs to manage this.
@Transactional // All the methods in the interface should be wrapped in a transaction
public interface CategoryDao extends CrudRepository<Category,Integer> {
}

