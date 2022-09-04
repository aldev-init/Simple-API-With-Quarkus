package Models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

/*
Make your entities extend PanacheEntity: it has an ID field that is auto-generated.
If you require a custom ID strategy, you can extend PanacheEntityBase instead and handle
the ID yourself.
 */


//mapping entity untuk bagian Class.List(*query)
@Entity(name = "course")
@Table(name = "course")
public class CourseModel extends PanacheEntityBase {

    //generation type = Identity for auto_increment SQL;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course", unique = true, nullable = false)
    public long id_course;

    @Column(name = "name")
    public String name;

    @Column(name = "difficult")
    public String difficult;

    @Column(name = "price")
    public String price;

}
