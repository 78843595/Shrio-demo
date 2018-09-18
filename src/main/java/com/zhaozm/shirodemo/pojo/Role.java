package com.zhaozm.shirodemo.pojo;

import java.util.HashSet;

public class Role {
    private Integer rid;
    private String rname ;
    private HashSet<Permission> permissions=new HashSet<Permission>();
    private HashSet<User> users=new HashSet<User>();

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public Integer getRid() {

        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public HashSet<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }
}
