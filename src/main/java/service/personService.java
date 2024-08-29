package service;

import model.Person;
import model.age;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.personRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class personService {
    private personRepository operations;

    @Autowired
    public void setOperations(personRepository operations) {
        this.operations = operations;
    }

    public List<Person> getAllPerson(){
        return operations.findAll();
    }
    public Optional<Person> getConcretPerson(int id){
        return operations.findById(id);
    }
    public void savePerson(Person person){
        int ages = getAge(person.getAge());
        if(ages>40)
            person.setAgeEnum(age.generationX);
        else if (ages>20)
            person.setAgeEnum(age.generationY);
        else if (ages>=15)
            person.setAgeEnum(age.generationZ);
        else
            person.setAgeEnum(age.generationA);
        operations.save(person);
    }
    public void updatePerson(int id,Person person){
        person.setPerson_id(id);
        int ages = getAge(person.getAge());
        if(ages>40)
            person.setAgeEnum(age.generationX);
        else if (ages>20)
            person.setAgeEnum(age.generationY);
        else if (ages>=15)
            person.setAgeEnum(age.generationZ);
        else
            person.setAgeEnum(age.generationA);
        operations.save(person);
    }
    public void deletePerson(int id){
        operations.deleteById(id);
    }
    //Вспомогательные функциий
    public static int getAge(Date birthDate) {
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }
}
