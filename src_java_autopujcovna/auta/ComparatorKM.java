/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auta;



public class ComparatorKM implements java.util.Comparator<IAuto>{
    
    @Override
    public int compare(IAuto vkladane, IAuto vSeznamu ) {
        if (vkladane.getPocetKilometru() < vSeznamu.getPocetKilometru()) {
            return -1;
        }else if(vkladane.getPocetKilometru() > vSeznamu.getPocetKilometru()){
            return 1;
        }else{
            return 0;
        }
       
    }
    
}
