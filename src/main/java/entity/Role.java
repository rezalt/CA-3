/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.User;
import entity.User;
import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author josephawwal
 */
@Entity
public class Role implements Serializable {

    
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private final List<User> users;
    
    public List<User> getUsers(){
        return users;
    }
    private static final long serialVersionUID = 1L;
    @Id
    
    private String roleName;
    
    public Role(String roleName){
        this.users = new ArrayList();
        this.roleName = roleName;
    }
    
    public Role(){
        
        this.users = new ArrayList();
        
    }
    
    public void addUser(User user){
        users.add(user);
        
        
    }
    public String getRoleName(){
        return roleName;
    }
    
    public void setRoleName(String roleName){
        
        this.roleName = roleName;
    }
}
