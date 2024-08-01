package restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restapi.entities.HeroEntity;
import restapi.services.HeroService;

@CrossOrigin
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public ResponseEntity<List<HeroEntity>> getHeroes(){
        return heroService.getAllHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroEntity> getHeroId(@PathVariable Long id) {
        return heroService.getHeroById(id);
    }

    @PostMapping
    public ResponseEntity<HeroEntity> createHeroe(@RequestBody HeroEntity hero){
        return heroService.createNewHero(hero);
    }
    
}
