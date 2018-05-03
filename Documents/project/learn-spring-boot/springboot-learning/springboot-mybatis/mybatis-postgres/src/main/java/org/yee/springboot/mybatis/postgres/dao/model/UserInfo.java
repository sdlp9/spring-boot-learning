package org.yee.springboot.mybatis.postgres.dao.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "mybatis.user_info")
public class UserInfo implements Serializable {
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private Short sex;

    private Short age;

    @Column(name = "creation_time")
    private Long creationTime;

    @Column(name = "modified_time")
    private Long modifiedTime;

    private String creator;

    private String modifier;

    private static final long serialVersionUID = 1L;

    public UserInfo(Long id, String userName, String password, Short sex, Short age, Long creationTime, Long modifiedTime, String creator, String modifier) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.creationTime = creationTime;
        this.modifiedTime = modifiedTime;
        this.creator = creator;
        this.modifier = modifier;
    }

    public UserInfo() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return sex
     */
    public Short getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Short sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public Short getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Short age) {
        this.age = age;
    }

    /**
     * @return creation_time
     */
    public Long getCreationTime() {
        return creationTime;
    }

    /**
     * @param creationTime
     */
    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return modified_time
     */
    public Long getModifiedTime() {
        return modifiedTime;
    }

    /**
     * @param modifiedTime
     */
    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getCreationTime() == null ? other.getCreationTime() == null : this.getCreationTime().equals(other.getCreationTime()))
            && (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getModifier() == null ? other.getModifier() == null : this.getModifier().equals(other.getModifier()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getCreationTime() == null) ? 0 : getCreationTime().hashCode());
        result = prime * result + ((getModifiedTime() == null) ? 0 : getModifiedTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getModifier() == null) ? 0 : getModifier().hashCode());
        return result;
    }
}