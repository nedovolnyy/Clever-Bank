/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities;

/**
 *
 * @author AKrot
 */
public class Bank extends BaseEntity{
    public Bank(int id, String name){
        Id = id;
        Name = name;
    }
    
    String Name;
    public String getName() { return this.Name; };
    private void setName(String Name) { this.Name = Name; };
}
