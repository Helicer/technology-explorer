package jro.techexplorer.category;

import jro.techexplorer.technology.Technology;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String name;

    // TODO: Connect foreign relations
    @OneToMany(mappedBy = "category")
    public Set<Technology> technologies;

}
