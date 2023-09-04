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
        this.id = id;
        this.fullName = fullName;
    }
    
    String fullName;
    public String getFullName() { return this.fullName; };
    private void setFullName(String fullName) { this.fullName = fullName; };
}
