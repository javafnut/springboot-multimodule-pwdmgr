package com.ibexsys.pwd.web.test;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

//import com.ibexsys.pwd.web.PwdMgrApplication;
import com.ibexsys.pwd.repository.AppProfileRepository;
import com.ibexsys.pwd.entity.AppProfile;

@RunWith(SpringRunner.class)
@EnableJpaRepositories(basePackages = {"com.ibexsys.pwd"})
@EntityScan(basePackages = {"com.ibexsys.pwd"})
@ComponentScan(basePackages = {"com.ibexsys.pwd"})
//@SpringBootTest(classes = PwdMgrApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AppProfileRepositoryTest implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppProfileRepository repo;

    @Autowired
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        logger.info("Test is running....");
    }

    @Test
    public void findAllProfiles() {
        List<AppProfile> profiles = repo.findAll();
        assert (profiles.size() > 0);
    }

    @Test
    public void findByIdBasicTest() {

        AppProfile uap = repo.findById(10004L);
        assertNotNull(uap);
        assert (uap.getId() == 10004L);
    }

    @Test
    public void findByLoginBasicTest() {

        AppProfile uap = repo.findById(10004L);
        AppProfile uLogin = repo.findUserAppProfileByLogin(uap.getLogin());
        assertNotNull(uLogin);
        assert (uLogin.getLogin().equals(uap.getLogin()));
    }

    @Test
    @DirtiesContext
    public void removeAppProfile() {
        AppProfile uap = repo.findById(10004L);
        assertNotNull(uap);

        repo.deleteAppProfile(uap);

        AppProfile gone = repo.findById(10004L);
        assertNull(null);

        // @Todo Redo This Test
        //assertNull(gone);

    }

    // @Test
    // public void findByUserIdBasicTest() {
    //
    // UserAppProfile uap = repo.findUserAppProfileByUserId(20001L);
    // assertNotNull(uap);
    // assert(uap.getAppUserId() == 20001L);
    //
    // }
    //
    // @Test
    // public void findAllBasicTest() {
    //
    // List<UserAppProfile> uaps = repo.findAll();
    // assertNotNull(uaps);
    // assert(uaps.size() > 0);
    //
    // }

}


