package com.ibexsys.pwd.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibexsys.pwd.entity.AppProfile;
import com.ibexsys.pwd.entity.Site;
import com.ibexsys.pwd.entity.User;

@Repository
@Transactional
public class AppProfileRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    public AppProfile findById(Long id) {
        AppProfile userAppProfile = em.find(AppProfile.class, id);
        return userAppProfile;
    }

    public List<AppProfile> findAll() {
        TypedQuery<AppProfile> namedQuery = em.createNamedQuery("find_all_user_profiles", AppProfile.class);
        return namedQuery.getResultList();
    }

    public AppProfile findUserAppProfileByUserId(Long id) {
        TypedQuery<AppProfile> namedQuery = em.createNamedQuery("find_user_profile_by_user_id", AppProfile.class);
        namedQuery.setParameter(0, id);
        return namedQuery.getSingleResult();
    }

    public AppProfile findUserAppProfileByLogin(String login) {
        TypedQuery<AppProfile> namedQuery = em.createNamedQuery("find_user_profile_by_login", AppProfile.class);
        namedQuery.setParameter(0, login);
        return namedQuery.getSingleResult();
    }

    @Transactional
    public void deleteAppProfile(AppProfile profile) {

        if (em.contains(profile)) {
            em.remove(profile);
            em.flush();
        }

    }

    @Transactional
    public AppProfile insertUserAndAppProfile(User user, AppProfile profile) {
        em.persist(user);
        em.persist(profile);

        profile.setUser(user);
        user.setAppProfile(profile);

        em.persist(profile);

        return profile;

    }

    @Transactional
    public AppProfile saveSite(AppProfile profile, Site site) {

        em.persist(site);
        // em.persist(profile);

        site.setAppProfile(profile);
        profile.addSite(site);
        em.merge(profile);

        return profile;
    }

    @Transactional
    public void deleteAppProfile(Long id) {

        AppProfile profile = findById(id);
        // List<Site> sites =

        em.remove(profile);
    }

    // public AppProfile insertSite(AppProfile profile, Site site) {
    //
    // if (profile != null && site != null) {
    // em.persist(site);
    // try {
    // profile.addSite(site);
    // em.persist(profile);
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }
    //
    // return profile;
    // }

    // public AppProfile saveAppProfile(AppProfile appProfile) {
    //
    // if (appProfile != null) {
    // em.p
    // }
    //
    //
    // AppProfile profile;
    //
    //
    // return profile;
    // }

    // if (site != null && appProfile != null) {
    // try {
    // appProfile.addSite(site);
    // site.setAppProfile(appProfile);
    //
    // em.persist(appProfile);
    // em.persist(site);
    //
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }
    // }

}
