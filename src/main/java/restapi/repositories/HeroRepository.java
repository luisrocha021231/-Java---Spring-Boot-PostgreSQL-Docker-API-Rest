package restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.entities.HeroEntity;

public interface HeroRepository extends JpaRepository<HeroEntity, Long>{

    
} 
