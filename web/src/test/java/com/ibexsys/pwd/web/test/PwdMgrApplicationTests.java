package com.ibexsys.pwd.web.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibexsys.pwd.entity.AppProfile;
import com.ibexsys.pwd.entity.Site;
import com.ibexsys.pwd.entity.User;
import com.ibexsys.pwd.repository.AppProfileRepository;
import com.ibexsys.pwd.repository.SiteRepository;
import com.ibexsys.pwd.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PwdMgrApplicationTests implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SiteRepository siteRepo;

    @Autowired
    private AppProfileRepository appProfileRepo;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Test is running....");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    public void createCompleteAppProfileTest() {

        byte[] salt = "UserSite".getBytes();
        byte[] password = "TestPassword".getBytes();
        Site site = null;

        AppProfile profile = new AppProfile("TestLogin", "PWDFile.pwd");
        User user = new User("Foo", "Barr", "Foo@Foo.bar", salt, password);
        appProfileRepo.insertUserAndAppProfile(user, profile);

        for (int i = 0; i < 4; i++) {
            site = new Site("Test Site " + i, "ROOT", "test@test.com", "testLogin", password, "notes");
            profile = appProfileRepo.saveSite(profile, site);
            // em.merge(site);
        }

        assertNotNull(profile);
        assertTrue(profile.getId() > 0);
        assertTrue(profile.getUser().getId() > 0);
        assertTrue(profile.getUser().getEmail().equalsIgnoreCase("Foo@Foo.bar"));
        assertTrue(profile.getSiteList().size() == 4);
        ;

    }

    // @ToDo Does AppProfilie removal remove the user ?
    @Test
    @Transactional
    public void createAndDeleteCompleteAppProfileTest() {

        byte[] salt = "UserSite".getBytes();
        byte[] password = "TestPassword".getBytes();
        Site site = null;

        AppProfile profile = new AppProfile("TestLogin", "PWDFile.pwd");
        User user = new User("Foo", "Barr", "Foo@Foo.bar", salt, password);
        appProfileRepo.insertUserAndAppProfile(user, profile);

        for (int i = 0; i < 4; i++) {
            site = new Site("Test Site " + i, "ROOT", "test@test.com", "testLogin", password, "notes");
            profile = appProfileRepo.saveSite(profile, site);
            // em.merge(site);
        }

        assertNotNull(profile);
        assertTrue(profile.getId() > 0);
        assertTrue(profile.getUser().getId() > 0);
        assertTrue(profile.getUser().getEmail().equalsIgnoreCase("Foo@Foo.bar"));
        assertTrue(profile.getSiteList().size() == 4);

        //appProfileRepo.deleteAppProfile(profile.getId());
        appProfileRepo.deleteAppProfile(profile);


        assertNull(appProfileRepo.findById(profile.getId()));

        // Make sure the sites have been removed
 //       assertTrue(siteRepo.findAll().size() == 0);

        // profile still exists locally, lets make sure the user is deleted
        // assertNull(userRepo.findById(profile.getUser().getId()));

    }

    @Test
    public void contextLoads() {
    }

}