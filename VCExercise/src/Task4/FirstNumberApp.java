package Task4;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;

import Task1.Integer;
/**
*
* @author dat
*/
public class FirstNumberApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer so = new Integer();
		so.input();
                boolean flag = true;
		if(so.isPrimeNumber() == true) {
			JOptionPane.showMessageDialog(null, "Hello, The Number enter is a prime number ");
                        flag = false;
		}
		if(so.isSquareNumber() == true) {
			JOptionPane.showMessageDialog(null, "Hello, The Number enter is a square Number ");
                        flag = false;
		}
		if(so.isPerfectNumber() == true) {
			JOptionPane.showMessageDialog(null, "Hello, The Number enter is a perfect Number ");
                        flag = false;
		}
                if(flag == true){
                        JOptionPane.showMessageDialog(null, "Hello, The Number enter is not a perfect Number"
                                + ", a prime number or a square number ");
                }
	}

}
