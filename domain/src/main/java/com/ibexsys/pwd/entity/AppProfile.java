package com.ibexsys.pwd.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "App_Profile")
@Cacheable // Causes entity to do cache lookup in 2nd level cache
@SQLDelete(sql = "update App_Profile set is_deleted=true where id=?") // Hibernate Specific
@Where(clause = "is_deleted = false")
@NamedQueries(value = { @NamedQuery(name = "find_all_user_profiles", query = "select u from AppProfile u"),
        @NamedQuery(name = "find_user_profile_by_user_id", query = "select u from AppProfile u where id=?"),
        @NamedQuery(name = "find_user_profile_by_login", query = "select u from AppProfile u where login=?") })
public class AppProfile {

    // @ToDo clean this up
    public static final byte TRUE = 1;
    public static final byte FALSE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "appProfile")
    private User user;

    // @OneToMany(fetch=FetchType.EAGER,
    // mappedBy="appProfile",
    // cascade = CascadeType.REMOVE)

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appProfile")
    private List<Site> sites = new ArrayList<Site>();

    @Column(name = "Login")
    private String login;

    @Column(name = "LastLoginDTM")
    private LocalDateTime lastLoginDTM;

    @Column(name = "Active")
    private boolean isActive;

    @Column(name = "AccountLocked")
    private boolean isLocked;

    @Column(name = "PwdFileName")
    private String pwdFileName;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    protected AppProfile() {
    };

    // Assume that the date/time fields are not set by either ORM and/or DB trigger
    public AppProfile(String login, String pwdFileName) {
        this.login = login;
        this.pwdFileName = pwdFileName;
        this.isLocked = false;
        this.isActive = true;
        this.lastLoginDTM = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
        this.isDeleted = false;
    }

    public void addSite(Site site) {

        // if (site == null || this.getSiteList() == null)
        // throw new Exception("Either Site or SiteList is Null");

        this.getSiteList().add(site);
    }

    public String getPwdFileName() {
        return pwdFileName;
    }

    public void setPwdFileName(String pPwdFileName) {
        pwdFileName = pPwdFileName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getLastLoginDTM() {
        return lastLoginDTM;
    }

    public void setLastLoginDTM(LocalDateTime lastLoginDTM) {
        this.lastLoginDTM = lastLoginDTM;
    }

    // public User getAppUser() {
    // return user;
    // }
    //
    // public void setAppUser(User appUser) {
    // this.user = appUser;
    // }

    public List<Site> getSiteList() {
        return this.sites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppProfile other = (AppProfile) obj;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AppProfile [id=" + id + ", user=" + user + "\n, sites=" + sites + "\n, login=" + login
                + ", lastLoginDTM=" + lastLoginDTM + ", isActive=" + isActive + ", isLocked=" + isLocked
                + "\n, pwdFileName=" + pwdFileName + ", modifiedDate=" + modifiedDate + ", createdDate=" + createdDate
                + "]";
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
