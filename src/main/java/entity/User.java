package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import security.IUser;

@Entity
public class User implements IUser, Serializable
{

    @Id
    @javax.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String password;  //Pleeeeease dont store me in plain text

    @ElementCollection
    @CollectionTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    @Column(name = "ROLES")
    List<String> roles = new ArrayList();

    
    public User()
    {

    }

    public User(String userName, String password)
    {
        this.userName = userName;
        this.password = password;

    }

    public User(String userName, String password, List<String> roles)
    {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public void addRole(String role)
    {
        roles.add(role);
    }

    @Override
    public List<String> getRolesAsStrings()
    {
        return roles;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    

}
