package by.benikov.citybookbot.citybookbot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "name")
    String cityName;

    @Column(name = "description")
    String cityDescription;

}
