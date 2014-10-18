package hello.controller;

import hello.db.DBOperations;
import hello.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anshumandutta on 10/18/14.
 */
@RestController
@RequestMapping("/db")
public class DBController {

    @Autowired
    private DBOperations dbOperations;

    @RequestMapping (value = "insert", method= RequestMethod.POST)
    public String insertData(@RequestBody Employee employee){
        try {
            dbOperations.insertEmployee(employee);
            return "success";
        } catch (Exception e){
            return e.getMessage();
        }

    }

    @RequestMapping (value = "listall", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> listAll(){
        try {
            return dbOperations.listAll();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
