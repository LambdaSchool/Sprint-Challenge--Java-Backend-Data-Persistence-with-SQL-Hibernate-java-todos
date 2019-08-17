package com.lambda.sprint.mode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "todo",
                cascade = CascadeType.ALL)
    private List<Todo> todols = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, List<UserRole> userRoles)
    {
        setUsername(username);
        setPassword(password);
        for (UserRole ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRole> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRole r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getRolename().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }
    public List<Todo> getTodo(){
        return todols;
    }

}
