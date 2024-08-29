package service;

import model.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.childRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class childService {
    private childRepository operations;

    @Autowired
    public void setOperations(childRepository operations) {
        this.operations = operations;
    }
    public List<Child> getAllChild(){
        return operations.findAll();
    }
    public Optional<Child> getConcretChild(int id){
        return operations.findById(id);
    }
    public void saveChild(Child child){
        operations.save(child);
    }
    public void updateChild(int id,Child child){
        child.setChild_id(id);
        operations.save(child);
    }
    public void deleteChild(int id){
        operations.deleteById(id);
    }

    //ANYMORE
    public List<Child> getAllChildWherePartnerIdNULL(){
        return operations.findByPersonIsNull();
    }
}
