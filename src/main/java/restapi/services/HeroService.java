package restapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import restapi.entities.HeroEntity;
import restapi.repositories.HeroRepository;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    // GET HERO BY ID
    public ResponseEntity<HeroEntity> getHeroById(@PathVariable Long id){

        if (id <= 0 ) {
            return ResponseEntity.badRequest().build();
        }

        Optional<HeroEntity> getHero = heroRepository.findById(id);
        
        if(getHero.isPresent()){
            return ResponseEntity.ok(getHero.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET ALL HEROES
    public ResponseEntity<List<HeroEntity>> getAllHeroes(){
        
        List<HeroEntity> heroes = heroRepository.findAll();

        if(heroes.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(heroes);
        }
    }

    // CREATE HERO
    public ResponseEntity<HeroEntity> createNewHero(@RequestBody HeroEntity hero){
        
        if(hero.getId() != null){
            return ResponseEntity.badRequest().build();
        }

        HeroEntity res = heroRepository.save(hero);
        return ResponseEntity.ok(res);
    }

    // UPDATE HERO
    public Optional<HeroEntity> updateHero(@RequestBody HeroEntity heroDetails, @PathVariable Long id){

        return heroRepository.findById(id).map(hero -> {
            hero.setName(heroDetails.getName());
            hero.setAlias(heroDetails.getAlias());
            hero.setAge(heroDetails.getAge());
            hero.setTeam(heroDetails.getTeam());
            hero.setVillains(heroDetails.getVillains());
            return heroRepository.save(hero);
        });
    }
    
    // DELETE ALL HEROES
    public ResponseEntity<HeroEntity> deleteAllHeroes(){
        heroRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    // DELETE HERO BY ID
    public ResponseEntity<HeroEntity> deleteHeroById(@PathVariable Long id){

        HeroEntity heroformDB = heroRepository.findById(id).orElse(null);
        heroRepository.delete(heroformDB);
        return ResponseEntity.noContent().build();
        
    }


    


    
}
