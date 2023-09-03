/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities;

/**
 *
 * @author AKrot
 */
public class User extends BaseEntity{
    public User(int id, String fullName){
        Id = id;
        FullName = fullName;
    }
    
    String FullName;
    public String getFullName() { return this.FullName; };
    private void setFullName(String FullName) { this.FullName = FullName; };
}
