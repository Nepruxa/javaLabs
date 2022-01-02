package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.School;

import java.util.*;

@Service
public class WebService {
    @Autowired
    SchoolRepository repository;

    public List<DTO> findAll() {
        List<School> schools = repository.findAll();
        List<DTO> dtos = new ArrayList<DTO>();
        for (School school: schools) {
            dtos.add(new DTO(school.getNum(), school.getAddress().getAddress()));
        }
        return dtos;
    }
}
