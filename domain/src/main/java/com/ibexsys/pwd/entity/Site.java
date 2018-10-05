package com.ibexsys.pwd.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Site")
@NamedQueries(value = { @NamedQuery(name = "find_all_sites", query = "select s from Site s"),
        @NamedQuery(name = "find_site_by_name", query = "select s from Site s where name=?") })
public class Site implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category_Name")
    private String category;

    @ManyToOne
    private AppProfile appProfile;

    @Column(name = "SiteURL")
    private String siteURL;

    @Column(name = "Login")
    private String login;

    @Column(name = "SitePwd")
    private byte[] password;

    @Column(name = "Notes")
    private String notes;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    protected Site() {
    }

    public Site(String name, String category, String siteURL, String login, byte[] password, String notes) {

        this.name = name;
        this.category = category;
        this.siteURL = siteURL;
        this.login = login;
        this.password = password;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public AppProfile getAppProfile() {
        return appProfile;
    }

    public void setAppProfile(AppProfile appProfile) {

        if (appProfile != null)
            this.appProfile = appProfile;
    }

    public String getSiteName() {
        return name;
    }

    public void setSiteName(String siteName) {
        this.name = siteName;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void setSiteURL(String siteURL) {
        this.siteURL = siteURL;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Site [id=" + id + ", siteName=" + name + ", category=" + category + ", appProfile=" + appProfile.getId()
                + ", siteURL=" + siteURL + ", login=" + login + ", password=" + Arrays.toString(password) + ", notes="
                + notes + ", modifiedDate=" + modifiedDate + ", createdDate=" + createdDate + "]";
    }

}
