/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.inptp.databasedependency.business;

import cz.upce.fei.inptp.databasedependency.dao.PersonDAO;
import cz.upce.fei.inptp.databasedependency.dao.PersonRolesDAO;
import cz.upce.fei.inptp.databasedependency.entity.Person;
import cz.upce.fei.inptp.databasedependency.entity.PersonRole;
import cz.upce.fei.inptp.databasedependency.entity.Role;
import java.util.ArrayList;
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
public class AuthorizationServiceTest {

    public AuthorizationServiceTest() {
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
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", rw)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", ro)]) - fail
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section/subsection", admin)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/section", admin)]) - pass
    // TODO: Authorize(person, "/section/subsection", rw) - PersonRole([Role("/", admin)]) - pass
    /**
     * Test of Authorize method, of class AuthorizationService.
     */
    @Test
    public void testAuthorize() {
        System.out.println("test ");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section/subsection", "rw", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
    
    @Test
    public void testAuthorize1() {
        System.out.println("test 1");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section/subsection", "ro", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertFalse(a);
    }
    
    @Test
    public void testAuthorize2() {
        System.out.println("test 2");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section", "rw", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
    
    @Test
    public void testAuthorize3() {
        System.out.println("test 3");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section", "ro", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertFalse(a);
    }

    
    @Test
    public void testAuthorize4() {
        System.out.println("test 4");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/", "rw", ""));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
    
    @Test
    public void testAuthorize6() {
        System.out.println("test 6");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section/subsection", "admin", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
    
    @Test
    public void testAuthorize7() {
        System.out.println("test 7");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/section", "admin", " "));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
    
    @Test
    public void testAuthorize8() {
        System.out.println("test 8");
        PersonDAO persondao = mock(PersonDAO.class);
        PersonRolesDAO personRolesDao = mock(PersonRolesDAO.class);
        AuthorizationService servis = new AuthorizationService(persondao, personRolesDao);
        
        when(persondao.getRoleWhereStringFor(any())).thenReturn("id = 0");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role("/", "admin", ""));
        
        when(personRolesDao.load(any())).thenReturn(new PersonRole(new Person(0, "nn", "aa"),  roles));
        
        boolean a = servis.Authorize(new Person(0, "nn", "aa"), "/section/subsection", AccessOperationType.Write);
        assertTrue(a);
    }
}
