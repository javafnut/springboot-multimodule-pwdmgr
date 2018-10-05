package com.ibexsys.pwd.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibexsys.pwd.entity.Site;

@Repository
@Transactional
public class SiteRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    public Site findById(Long id) {
        Site site = em.find(Site.class, id);
        return site;
    }

    public Site save(Site site) {
        if (site.getId() != null) {
            em.persist(site);
        } else {
            em.merge(site);
        }

        return site;
    }

    public List<Site> findAll() {
        TypedQuery<Site> namedQuery = em.createNamedQuery("find_all_sites", Site.class);
        return namedQuery.getResultList();
    }

    public Site findSiteByName(String name) {
        TypedQuery<Site> namedQuery = em.createNamedQuery("find_site_by_name", Site.class);
        namedQuery.setParameter(0, name);

        return namedQuery.getSingleResult();
    }
}
