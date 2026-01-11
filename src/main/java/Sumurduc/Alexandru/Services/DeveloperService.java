package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    public void addDeveloper(Developer developer) {
        developerRepository.add(developer);
    }

    public boolean deleteByUsername(String username) {
        return developerRepository.deleteByUsername(username) > 0;
    }
}
