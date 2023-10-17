package com.workintech.demo.rest;

import com.workintech.demo.dto.DeveloperResponse;
import com.workintech.demo.model.Developer;
import com.workintech.demo.model.DeveloperFactory;
import com.workintech.demo.tax.Taxable;
import com.workintech.demo.validation.DeveloperValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {


    private Map<Integer,Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }
@Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer) {
        if(DeveloperValidation.isDeveloperExist(developers, developer.getId())){
            return new DeveloperResponse(null,
                    "Developer with given id already exist: " + developer.getId(),
                    400);
        }
        Developer savedDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        if (savedDeveloper != null) {
            developers.put(savedDeveloper.getId(), savedDeveloper);
        }
        return new DeveloperResponse(savedDeveloper, "Success", 200);
    }
    @GetMapping("/")
    public List<Developer> get() {
        return developers.values().stream().toList();
    }
    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable int id) {
        if (DeveloperValidation.isDeveloperExist(developers, id)) {
            return new DeveloperResponse(developers.get(id), "Success", 200);
        }
        return new DeveloperResponse(null, "Developer with given id is not exist: " + id,
                404);
    }
    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable int id, @RequestBody Developer developer) {
        if(!DeveloperValidation.isDeveloperExist(developers, id)){
            return new DeveloperResponse(null, "Developer with given id is not exist: " + id,
                    400);
        }
        developer.setId(id);
        Developer updatedDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        developers.put(id, updatedDeveloper);
        return new DeveloperResponse(updatedDeveloper, "Success", 200);
    }

    @DeleteMapping("/{id}")
    public Developer remove(@PathVariable int id) {
        Developer removedDeveloper = developers.get(id);
        developers.remove(id);
        return removedDeveloper;
    }


}
