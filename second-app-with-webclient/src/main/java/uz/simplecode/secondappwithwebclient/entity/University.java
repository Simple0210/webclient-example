package uz.simplecode.secondappwithwebclient.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "address")
    private String address;

    public University(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
