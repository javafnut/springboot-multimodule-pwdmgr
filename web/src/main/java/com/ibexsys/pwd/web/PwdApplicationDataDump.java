package com.ibexsys.pwd.web;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import com.ibexsys.pwd.web.PwdMgrApplication;
import com.ibexsys.pwd.entity.AppProfile;
import com.ibexsys.pwd.entity.Site;
import com.ibexsys.pwd.entity.User;
import com.ibexsys.pwd.repository.AppProfileRepository;
import com.ibexsys.pwd.repository.SiteRepository;
import com.ibexsys.pwd.repository.UserRepository;

public class PwdApplicationDataDump {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public AppProfileRepository appProfileRepo;

    @Autowired
    SiteRepository siteRepo;

    public PwdApplicationDataDump() {
    }

    public PwdApplicationDataDump(PwdMgrApplication app) {

        this.appProfileRepo = app.userAppProfileRepo;
        this.siteRepo = app.siteRepo;
        this.userRepo = app.userRepo;

    }

    public void sayFoo() {
        logger.info("This is foo'd");
    }

    public void dumpAllTableData() {
        dumpUsers();
        dumpSites();
        dumpAppProfiles();
    }

    public StringBuffer dumpStrings() {

        StringBuffer buffer = new StringBuffer();
        String demarker = "\n=========================================================================\n";

        buffer.append(demarker);

        List<User> allUsers = userRepo.findAll();

        for (User user : allUsers) {
            buffer.append(user.toString()).append("\n");
        }

        buffer.append(demarker);

        List<Site> allSites = siteRepo.findAll();

        for (Site site : allSites) {
            buffer.append(site.toString()).append("\n");
        }

        buffer.append(demarker);

        List<AppProfile> appProfiles = appProfileRepo.findAll();

        for (AppProfile userProfile : appProfiles) {
            buffer.append(userProfile.toString()).append("\n");
        }

        buffer.append(demarker);

        return buffer;

    }

    public void dumpUsers() {

        List<User> allUsers = userRepo.findAll();

        for (User user : allUsers) {
            logger.info("User -> {}", user);
        }
    }

    public void dumpSites() {

        List<Site> allSites = siteRepo.findAll();

        for (Site site : allSites) {
            logger.info("Site -> {}", site);
        }
    }

    public void dumpAppProfiles() {

        List<AppProfile> appProfiles = appProfileRepo.findAll();

        for (AppProfile userAppProfile : appProfiles) {
            logger.info("UserAppProfile -> {}", userAppProfile);
        }
    }

    public void createCompleteAppProflie() {

        byte[] salt = "UserSite".getBytes();
        byte[] password = "TestPassword".getBytes();

        Site site = null;

        User user = new User("FooName", "fooLastName", "foo@foo.bar", salt, password);
        AppProfile appProfile = new AppProfile("New User XX", "Foo.xml");

        AppProfile profile = appProfileRepo.insertUserAndAppProfile(user, appProfile);

        for (int i = 0; i < 5; i++) {
            site = new Site("Test Site - " + i, "ROOT", "test@test.com", "testLogin", password, "notes");
            appProfileRepo.saveSite(profile, site);
        }

        logger.info("================>\n" + profile.toString());

    }
}
