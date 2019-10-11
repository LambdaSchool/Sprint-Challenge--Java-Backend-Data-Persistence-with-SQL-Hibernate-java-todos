package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends AudiTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column(nullable = false,
            unique = true)
    private String name;

    @OneToMany(mappedBy = "role",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRole> userroles = new ArrayList<>();

    public Role()
    {
    }

    public long getRoleid()
    {
        return roleid;
    }

    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<UserRole> getUserroles()
    {
        return userroles;
    }

    public void setUserroles(List<UserRole> userroles)
    {
        this.userroles = userroles;
    }
}
