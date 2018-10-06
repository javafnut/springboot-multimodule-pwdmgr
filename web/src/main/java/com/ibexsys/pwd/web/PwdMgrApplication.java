package com.ibexsys.pwd.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ibexsys.pwd.repository.AppProfileRepository;
import com.ibexsys.pwd.repository.SiteRepository;
import com.ibexsys.pwd.repository.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import com.ibexsys.pwd.util.PwdApplicationDataDump;

@SpringBootApplication

// Add these below to resolve autowired issues with multimodules
@EnableJpaRepositories(basePackages = {"com.ibexsys.pwd"})
@EntityScan(basePackages = {"com.ibexsys.pwd"})
@ComponentScan(basePackages = {"com.ibexsys.pwd"})
public class PwdMgrApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public SiteRepository siteRepo;

    @Autowired
    public AppProfileRepository userAppProfileRepo;

    public static void main(String[] args) {
        SpringApplication.run(PwdMgrApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        runDumpAppData();
        // quickSave();
    }

//    public void runDumpAppData() {
//
//        PwdApplicationDataDump data = new PwdApplicationDataDump(this);
//        // data.dumpAllTableData();
//        logger.info("Data Dump\n\n" + data.dumpStrings());
//
//        data.createCompleteAppProflie();
//
//    }
}