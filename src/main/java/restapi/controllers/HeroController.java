package restapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
