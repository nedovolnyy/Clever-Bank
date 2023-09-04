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
        this.id = id;
        this.name = name;
    }
    
    String name;
    public String getName() { return this.name; };
    private void setName(String name) { this.name = name; };
}
