package hello.db;

import hello.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anshumandutta on 10/18/14.
 */
@Repository
public class DBOperations {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertEmployee(Employee employee) throws Exception {
        mongoTemplate.insert(employee);
    }

    public List<Employee> listAll() throws Exception {
        return mongoTemplate.findAll(Employee.class);
    }

    public Employee listById(Long id){
        Query qry = new Query();
        qry.addCriteria(Criteria.where("empid").is(id));
        return mongoTemplate.findOne(qry, Employee.class);
    }

}
