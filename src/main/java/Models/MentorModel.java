package Models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity(name = "mentor")
@Table(name = "mentor")
public class MentorModel extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "gelar",nullable = true)
    public String gelar;

    @Column(name = "status",nullable = false)
    public int status;


}
