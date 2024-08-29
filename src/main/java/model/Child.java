package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "childs")
public class Child {
    @Id
    @Column(name = "childId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int child_id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "personId",referencedColumnName = "personId")
    private Person person;

    public Child() {}

    public Child(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getChild_id() {
        return child_id;
    }

    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
