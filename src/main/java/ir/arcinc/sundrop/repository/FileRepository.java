package ir.arcinc.sundrop.repository;

import ir.arcinc.sundrop.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tahae on 7/19/2017.
 */
@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    File findById(Long id);
    void deleteById(Long id);
}
