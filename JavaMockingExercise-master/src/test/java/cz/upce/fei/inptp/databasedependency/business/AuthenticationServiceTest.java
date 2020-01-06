/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.Database;
import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author st52520
 */
public class AuthenticationServiceTest {
    
    public AuthenticationServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO: add tests
    // TODO: Authenticate("user", "pass") - Person("user", encryptPwd("pass")) - pass
    // TODO: Authenticate("user", "invalid") - Person("user", encryptPwd("pass")) - fail
    // TODO: Authenticate("user", "pass") - nonexistent person - fail
    
    @org.junit.Test
    public void testAuthenticate1() {
        PersonDAO dao = mock(PersonDAO.class); 
        AuthenticationService serv = new AuthenticationService(dao);
        
        when(dao.load(any())).thenReturn(new Person(1,"user", AuthenticationService.encryptPassword("pass")));
        assertEquals(true, serv.Authenticate("user", "pass"));  
    }
    
    @org.junit.Test
    public void testAuthenticate2() {       
        PersonDAO dao = mock(PersonDAO.class); 
        AuthenticationService serv = new AuthenticationService(dao);
        
        when(dao.load(any())).thenReturn(null);
        
       assertEquals(false, serv.Authenticate("user", "pass"));
    }
    
    @org.junit.Test
    public void testAuthenticate3() {       
        PersonDAO dao = mock(PersonDAO.class); 
        AuthenticationService serv = new AuthenticationService(dao);
        
        when(dao.load(any())).thenReturn(new Person(1,"user", AuthenticationService.encryptPassword("invalid")));
        
       assertEquals(false, serv.Authenticate("user", "pass"));
    }
    
    /**
     * Test of Authenticate method, of class AuthenticationService.
     */
   /* @org.junit.Test
    public void testAuthenticate() {
        System.out.println("Authenticate");
        String login = "";
        String password = "";
        AuthenticationService instance = new AuthenticationService();
        boolean expResult = false;
        boolean result = instance.Authenticate(login, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @org.junit.Test
    public void testEncryptPassword() {
        System.out.println("encryptPassword");
        String password = "";
        String expResult = "";
        String result = AuthenticationService.encryptPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
