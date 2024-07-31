package restapi.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "villains")
public class VillainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "alias")
    private String alias;
    @Column(name = "age")
    private String age;
    @Column(name = "team")
    private String team;
    
    @ManyToMany(mappedBy = "villains")
    private Set<HeroEntity> heroes = new HashSet<>();

    //Constructors
    public VillainEntity() {
    }

    public VillainEntity(Long id, String name, String alias, String age, String team) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.team = team;
    }

    //Setters and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Set<HeroEntity> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<HeroEntity> heroes) {
        this.heroes = heroes;
    }
    
    
}
