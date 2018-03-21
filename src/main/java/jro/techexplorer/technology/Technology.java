package jro.techexplorer.technology;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="technology")
public class Technology {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;

    // TODO: Add slug to use in URLs

    @Lob
    public String business_value;

    @Lob
    public String tech_description;

    // TODO: Example usecases
    // TODO: Related tech
    // TODO: Links
    // TODO: Acronym / AKA
    // TODO: Category

}
