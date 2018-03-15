package jro.techexplorer.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;


    public List<Technology> findAll() {
        return technologyRepository.findAllByOrderByNameAsc();
    }


    public Technology findById(Long id) {
        return technologyRepository.findById(id).orElse(null);
    }

    public Technology save(Technology technology) {
        return technologyRepository.save(technology);
    }


    public void delete(Long id) {
        technologyRepository.deleteById(id);

    }
}
