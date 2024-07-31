package restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import restapi.entities.VillainEntity;

public interface VillainRepository extends JpaRepository<VillainEntity, Long>{

    
}
