/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2;

import java.awt.Window;
import java.util.Scanner;

/**
 *
 * @author maidoanh
 */
public class ComplexNumber {
    
    private Double real, im;

    public Double getReal() {
        return real;
    }

    public void setReal(Double real) {
        this.real = real;
    }

    public Double getIm() {
        return im;
    }

    public void setIm(Double im) {
        this.im = im;
    }
    
    public void printComplexNumber(){
        System.out.println(real +" + "+im+"i");
    }
    
    public ComplexNumber PlusToComplexNumber(ComplexNumber other){
        ComplexNumber result = new ComplexNumber();
        result.setReal(this.real + other.getReal());
        result.setIm(this.im + other.getIm());
        return result;
    }
    
    public ComplexNumber MinusToComplexNumber(ComplexNumber other){
        ComplexNumber result = new ComplexNumber();
        result.setReal(this.real - other.getReal());
        result.setIm(this.im - other.getIm());
        return result;
    }
    
    public ComplexNumber TimeToComplexNumber(ComplexNumber other){
        ComplexNumber result = new ComplexNumber();
        result.setReal(this.real*other.getReal() - other.getIm()*this.im);
        result.setIm(this.im*other.getReal() + other.getIm()*this.real);
        return result;
    }
    
    public ComplexNumber DivideToComplexNumber(ComplexNumber other){
        ComplexNumber result = new ComplexNumber();
        result.setReal((this.real*other.getReal() + other.getIm()*this.im)/(Math.pow(other.getReal(), 2)+Math.pow(other.getIm(), 2)));
        result.setIm((this.im*other.getReal()- this.real*other.getIm())/(Math.pow(other.getReal(), 2)+Math.pow(other.getIm(), 2)));
        return result;
    }
    
    public ComplexNumber TimeToRealNumber(Double other){
        ComplexNumber result = new ComplexNumber();
        result.setReal(this.real *other);
        result.setIm(this.im*other);
        return result;  
    }
    
    public static void main(String[] args) {
        
        ComplexNumber cN1 = new ComplexNumber();
        ComplexNumber cN2 = new ComplexNumber();
        ComplexNumber result = new ComplexNumber();
        
        System.out.println("Check get the real or imaginary parts method and print complex number method: ");
        Scanner input = new Scanner(System.in);
        cN1 = new ComplexNumber();
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN1.setIm(input.nextDouble()); 
        System.out.print("Complex Number: ");
        cN1.printComplexNumber();
        System.out.print("Press and Key to continue...");
        input = new Scanner(System.in);
        input.nextLine();

        System.out.println("\nCheck Plus to another complex number Method: ");
        System.out.println("Input the first complex number:");
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("imaginary part: ");
        cN1.setIm(input.nextDouble());
        System.out.println("Input the second complex number:");
        System.out.print("Input Real Part: ");
        cN2.setReal(input.nextDouble());
        System.out.print("imaginary part: ");
        cN2.setIm(input.nextDouble());
        result = cN1.PlusToComplexNumber(cN2);
        System.out.print("Result: ");
        result.printComplexNumber();
        System.out.println("Press and Key to continue...");
        input = new Scanner(System.in);
        input.nextLine();
        
        System.out.println("\nCheck Minus to another complex number Method: ");
        System.out.println("Input the first complex number:");
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("imaginary part: ");
        cN1.setIm(input.nextDouble());
        System.out.println("Input the second complex number:");
        System.out.print("Input Real Part: ");
        cN2.setReal(input.nextDouble());
        System.out.print("imaginary part: ");
        cN2.setIm(input.nextDouble());
        result = cN1.MinusToComplexNumber(cN2);
        System.out.print("Result: ");
        result.printComplexNumber();
        System.out.println("Press and Key to continue...");
        input = new Scanner(System.in);
        input.nextLine();
        
        System.out.println("\nCheck Times to another complex number Method: ");
        System.out.println("Input the first complex number:");
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN1.setIm(input.nextDouble());
        System.out.println("Input the second complex number:");
        System.out.print("Input Real Part: ");
        cN2.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN2.setIm(input.nextDouble());
        result = cN1.TimeToComplexNumber(cN2);
        System.out.print("Result: ");
        result.printComplexNumber();
        System.out.println("Press and Key to continue...");
        input = new Scanner(System.in);
        input.nextLine();
        
        System.out.println("\nCheck Divide to another complex number Method: ");
        System.out.println("Input the first complex number:");
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN1.setIm(input.nextDouble());
        System.out.println("Input the second complex number:");
        System.out.print("Input Real Part: ");
        cN2.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN2.setIm(input.nextDouble());
        result = cN1.DivideToComplexNumber(cN2);
        System.out.print("Result: ");
        result.printComplexNumber();
        System.out.println("Press and Key to continue...");
        input = new Scanner(System.in);
        input.nextLine();
        
        System.out.println("\nCheck Time to Real number Method: ");
        System.out.println("Input the complex number:");
        System.out.print("Input Real Part: ");
        cN1.setReal(input.nextDouble());
        System.out.print("Imaginary part: ");
        cN1.setIm(input.nextDouble());
        System.out.println("Input the Real number:");
        Double real = input.nextDouble();
        result = cN1.TimeToRealNumber(real);
        System.out.print("Result: ");
        result.printComplexNumber();
        System.out.println("Press and Key to Exit...");
        input = new Scanner(System.in);
        input.nextLine();
    }
}
