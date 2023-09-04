/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Common.Entities.Comparators;

import Common.Entities.Bill;

/**
 *
 * @author AKrot
 */

public class BillsComparator implements java.util.Comparator<Bill> {
    @Override
    public int compare(Bill a, Bill b) {
        return a.getId()- b.getId();
    }
}