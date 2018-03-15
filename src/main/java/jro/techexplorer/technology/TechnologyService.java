package jro.techexplorer.technology;

import java.util.List;
import java.util.Optional;

public interface TechnologyService {
    List<Technology> findAll();

    Technology findById(Long id);

    Technology save(Technology technology);

    void delete(Long id);


}
