package com.lovcreate.greendaodemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wanghaoyu on 2017/11/6.
 */
@Entity
public class User {

    @Id(autoincrement = true)
    private long id;
    private String name;
    private String sex;
    private String phone;
    @Generated(hash = 1268776974)
    public User(long id, String name, String sex, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
