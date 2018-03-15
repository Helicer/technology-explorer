package jro.techexplorer.technology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;


    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }


    public Optional<Technology> findById(Long id) {
        return technologyRepository.findById(id);
    }


    public Technology save(Technology technology) {
        return technologyRepository.save(technology);
    }


    public void delete(Long id) {
        technologyRepository.deleteById(id);

    }
}
