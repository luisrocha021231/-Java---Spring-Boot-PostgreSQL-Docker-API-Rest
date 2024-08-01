package restapi.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import restapi.entities.HeroEntity;
import restapi.repositories.HeroRepository;

@Service
public class HeroService {

    private static final Logger logger = LoggerFactory.getLogger(HeroService.class);

    @Autowired
    private HeroRepository heroRepository;

    // GET HERO BY ID
    public ResponseEntity<HeroEntity> getHeroById(@PathVariable Long id){

        if (id <= 0 ) {
            logger.error("Bad request, wrong parameter in GET operation ",id);
            return ResponseEntity.badRequest().build();
        }

        logger.info("Fetching hero with id: ", id);
        Optional<HeroEntity> getHero = heroRepository.findById(id);
        
        if(getHero.isPresent()){
            logger.info("Found hero with id: ", id);
            return ResponseEntity.ok(getHero.get());
        }else {
            logger.warn("Not found hero with id: ",id);
            return ResponseEntity.notFound().build();
        }
    }

    // GET ALL HEROES
    public ResponseEntity<List<HeroEntity>> getAllHeroes(){
        
        logger.info("Fetching all heroes");
        List<HeroEntity> heroes = heroRepository.findAll();

        if(heroes.isEmpty()){
            logger.warn("Not found heroes");
            return ResponseEntity.noContent().build();
        }else {
            logger.info("Found heroes");
            return ResponseEntity.ok(heroes);
        }
    }

    // CREATE HERO
    public ResponseEntity<HeroEntity> createNewHero(@RequestBody HeroEntity hero){
        
        if(hero.getId() != null){
            logger.error("Bad request, wrong parameters in CREATE operation");
            return ResponseEntity.badRequest().build();
        }

        HeroEntity res = heroRepository.save(hero);
        logger.info("Created hero");
        return ResponseEntity.ok(res);
    }

    // UPDATE HERO
    public Optional<HeroEntity> updateHero(@RequestBody HeroEntity heroDetails, @PathVariable Long id){

        logger.info("Updating hero with id: ", id);
        return heroRepository.findById(id).map(hero -> {
            hero.setName(heroDetails.getName());
            hero.setAlias(heroDetails.getAlias());
            hero.setAge(heroDetails.getAge());
            hero.setTeam(heroDetails.getTeam());
            hero.setVillains(heroDetails.getVillains());

            logger.info("Updated hero");
            return heroRepository.save(hero);
        });
    }
    
    // DELETE ALL HEROES
    public ResponseEntity<HeroEntity> deleteAllHeroes(){
        logger.info("Deleting all heroes");
        heroRepository.deleteAll();
        logger.info("Deleted heroes");
        return ResponseEntity.noContent().build();
    }

    // DELETE HERO BY ID
    public ResponseEntity<HeroEntity> deleteHeroById(@PathVariable Long id){

        logger.info("Fetching hero with id:" ,id);
        HeroEntity heroformDB = heroRepository.findById(id).orElse(null);
        logger.info("Deleting hero");
        heroRepository.delete(heroformDB);
        logger.warn("Not found hero");
        return ResponseEntity.noContent().build();
        
    }
}
