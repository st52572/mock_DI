package cz.upce.fei.inptp.databasedependency;

import cz.upce.fei.inptp.databasedependency.business.AuthorizationService;
import cz.upce.fei.inptp.databasedependency.business.AuthenticationService;
import cz.upce.fei.inptp.databasedependency.business.AccessOperationType;
import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;
import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.dao.Database;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
public class Main implements CommandLineRunner {

    /*
    TODO: Tasks:
     - Create required unit tests for AuthenticationService
     - Create required unit tests for AuthorizationService
     - Create service UserManagerService with methods:
      - Service MUST depend only on DAO objects, no specific code for DB
      - CreateUser(String name, String password) : Person
      - DeleteUser(Person p) : boolean
      - ChangePassword(Person p, String newPassword) : boolean
     - Create service UserRoleManagerService
      - ...
     */
    @Autowired
    Database database;
    @Autowired
    PersonDAO personDao;
    @Autowired
    AuthenticationService authentication;
    @Autowired
    AuthorizationService authorization;
    @Autowired
    Person person;

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        //Database database = new Database();
        database.open();

        // create person
        person.setId(10); person.setName("Peter"); person.setPassword(AuthenticationService.encryptPassword("rafanovsky"));
        //Person person = new Person(10, "Peter", AuthenticationService.encryptPassword("rafanovsky"));
        personDao.save(person);

        // load person
        person = personDao.load("id = 10");
        System.out.println(person);

        // test authentication       
        System.out.println(authentication.Authenticate("Peter", "rafa"));
        System.out.println(authentication.Authenticate("Peter", "rafanovsky"));

        // check user roles
        PersonRole pr = new PersonRolesDAO().load("name = 'yui'");
        System.out.println(pr);

        // test authorization
        person = personDao.load("id = 2");
        boolean authorizationResult = authorization.Authorize(person, "/finance/report", AccessOperationType.Read);
        System.out.println(authorizationResult);

        // load all persons from db
        try {
            Statement statement = database.createStatement();
            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        database.close();
    }
}
