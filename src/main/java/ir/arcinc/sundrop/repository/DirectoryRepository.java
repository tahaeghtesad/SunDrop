package ir.arcinc.sundrop.repository;

import ir.arcinc.sundrop.model.Directory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tahae on 7/17/2017.
 */
@Repository
public interface DirectoryRepository extends CrudRepository<Directory, Long> {
    Directory findById(Long id);
    Directory deleteById(Long id);
}
