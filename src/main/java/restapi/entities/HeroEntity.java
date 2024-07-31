package restapi.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "heroes")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "alias")
    private String alias;
    @Column(name = "age")
    private Integer age;
    @Column(name = "team")
    private String team;

    @ManyToMany
    @JoinTable(
        name = "heroes_villains",
        joinColumns = @JoinColumn(name = "hero_id"),
        inverseJoinColumns = @JoinColumn(name = "villain_id")
    )
    private Set<VillainEntity> villains = new HashSet<>();
    
    //Constructors
    public HeroEntity() {
    }

    public HeroEntity(Long id, String name, String alias, Integer age, String team) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.team = team;
    }

    // Setters and Getters
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Set<VillainEntity> getVillains(){
        return villains;
    }

    public void setVillains(Set<VillainEntity> villains) {
        this.villains = villains;
    }

}