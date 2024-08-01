package restapi.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import restapi.entities.HeroEntity;
import restapi.services.HeroService;

@CrossOrigin
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private static final Logger logger = LoggerFactory.getLogger(HeroController.class);

    @Autowired
    private HeroService heroService;

    @GetMapping
    public ResponseEntity<List<HeroEntity>> getAllHeroes(){
        logger.info("Received request to fetch all heroes");
        return heroService.getAllHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroEntity> getHeroId(@PathVariable Long id) {
        logger.info("Received request to fetch hero with id: ",id);
        return heroService.getHeroById(id);
    }

    @PostMapping
    public ResponseEntity<HeroEntity> createHeroe(@RequestBody HeroEntity hero){
        logger.info("Received request to create new hero");
        return heroService.createNewHero(hero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroEntity> updateHero(@PathVariable Long id, @RequestBody HeroEntity heroDetails) {
        logger.info("Received request to update hero with id: ",id);
        Optional<HeroEntity> updatedHero = heroService.updateHero(heroDetails, id);
        return updatedHero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<HeroEntity> deleteAllHeroes() {
        logger.info("Received request to delete all heroes");
        return heroService.deleteAllHeroes();
        //return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HeroEntity> delectHeroById(@PathVariable Long id) {
        logger.info("Received request to delete hero with id: ",id);
        return heroService.deleteHeroById(id);
        //return ResponseEntity.noContent().build();
    }
    
}
