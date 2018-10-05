package com.ibexsys.pwd.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "User")
@Cacheable // Causes entity to do cache lookup in 2nd level cache
@SQLDelete(sql = "update Site set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
@NamedQueries(value = { @NamedQuery(name = "find_all_users", query = "select u from User u"),
        @NamedQuery(name = "find_User_by_full_name", query = "select u from User u where u.firstName= ? and u.lastName = ?") })
// @SequenceGenerator(name = "userId_gen", sequenceName = "userId_gen",
// initialValue = 1000)
public class User implements Serializable {

    // private static final long serialVersionUID = -6528351877018119894L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private AppProfile appProfile;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "UserSalt")
    private byte[] salt;

    @Column(name = "Password")
    private byte[] password;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private boolean isDeleted;

    protected User() {
    }

    public User(String firstName, String lastName, String email, byte[] userSalt, byte[] password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = userSalt;
        this.password = password;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = this.createdDate;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
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

    public AppProfile getAppProfile() {
        return appProfile;
    }

    public void setAppProfile(AppProfile appProfile) {
        this.appProfile = appProfile;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + Arrays.hashCode(password);
        result = prime * result + Arrays.hashCode(salt);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        User other = (User) obj;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (modifiedDate == null) {
            if (other.modifiedDate != null)
                return false;
        } else if (!modifiedDate.equals(other.modifiedDate))
            return false;
        if (!Arrays.equals(password, other.password))
            return false;
        if (!Arrays.equals(salt, other.salt))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
        return "User [userId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", salt=" + Arrays.toString(salt) + ", password=" + Arrays.toString(password) + ", modifiedDate="
                + modifiedDate + ", createdDate=" + createdDate + "]";
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // /**
    // * @return the userAppProfile
    // */
    // public UserAppProfile getUserAppProfile() {
    // return userAppProfile;
    // }
    //
    // /**
    // * @param userAppProfile the userAppProfile to set
    // */
    // public void setUserAppProfile(UserAppProfile userAppProfile) {
    // this.userAppProfile = userAppProfile;
    // }

}

