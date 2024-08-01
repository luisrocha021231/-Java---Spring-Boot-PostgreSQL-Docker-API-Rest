package restapi.controllers;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<HeroEntity> updateHero(@PathVariable Long id, @RequestBody HeroEntity heroDetails) {
        Optional<HeroEntity> updatedHero = heroService.updateHero(heroDetails, id);
        return updatedHero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<HeroEntity> deleteAllHeroes() {
        heroService.deleteAllHeroes();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HeroEntity> delectHeroById(@PathVariable Long id) {
        heroService.deleteHeroById(id);
        return ResponseEntity.noContent().build();
    }
    
}
