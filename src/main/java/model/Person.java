package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @Column(name = "personId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @Column(name = "FullName")
    private String FullName;

    @Column(name = "placeLive")
    private String placeLive;

    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date age;

    @Column(name = "generation")
    @Enumerated(EnumType.STRING)
    private age ageEnum;

    public Person() {}

    public Person(String fullName, String placeLive, Date age) {
        FullName = fullName;
        this.placeLive = placeLive;
        this.age = age;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPlaceLive() {
        return placeLive;
    }

    public void setPlaceLive(String placeLive) {
        this.placeLive = placeLive;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public model.age getAgeEnum() {
        return ageEnum;
    }

    public void setAgeEnum(model.age ageEnum) {
        this.ageEnum = ageEnum;
    }
}
