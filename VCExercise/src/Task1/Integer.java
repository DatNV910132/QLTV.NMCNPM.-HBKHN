/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task1;
import javax.swing.JOptionPane;
/**
 *
 * @author Duong
 */
public class Integer {
    int number;
    
    public void input(){
        String so = JOptionPane.showInputDialog("Input a number:  ");
		number = java.lang.Integer.parseInt(so);
    }
    
    public boolean isPrimeNumber(){
        if((number == 1) || (number < 0)){
            return false;
        }
        int i;
        boolean flag = true;
        for(i = 2; i <= number/2; i++){
            if(number%i == 0){
                flag = false;
                break;
            }                
        }
        return flag;
    }
    
    public boolean isCompositeNumber(){
        if((number == 1) || (number < 0))
            return false;
        return !isPrimeNumber();
    }
    
    public boolean isSquareNumber(){
        if(number < 0){
            return false;
        }
        boolean flag = false;
        int i;
        for(i=1; i<=Math.sqrt(number); i++){
            if(i*i == number){
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    public boolean isPerfectNumber(){
        if((number == 1) || (number < 0)){
            return false;
        }
        int i, sum;
        sum = 1;
        for(i=2; i<=number/2; i++){
            if(number%i == 0){
                sum += i;
            }
        }
        if(sum == number)
            return true;
        else 
            return false;
    }
}
