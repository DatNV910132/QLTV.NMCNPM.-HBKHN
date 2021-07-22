package Task3;

import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Task2.ComplexNumber;

class Nguoi {
	private String sName;
	private static Double birthdate;
	// Set/get name of a person
	public void setName(String sName) {
		this.sName = sName;
	}

	public String getName() {
		return sName;
	}
	
	public Double getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Double birthdate) {
		Nguoi.birthdate = birthdate;
	}

	// tính tuổi
	public double getAge() {
		// Tạo đối tượng hôm nay và sinh nhật
		Calendar today = Calendar.getInstance();
		ComplexNumber todayreal = new ComplexNumber();
		ComplexNumber birthday = new ComplexNumber();
		ComplexNumber tuoi = new ComplexNumber();
		double year = (double)today.get(Calendar.YEAR);
		todayreal.setIm((double)0);
		birthday.setIm((double)0);
		todayreal.setReal(year);
		birthday.setReal(Nguoi.birthdate);
		// Tính chênh lệnh giữa các năm
			tuoi = todayreal.MinusToComplexNumber(birthday);
			double tuoichinh = tuoi.getReal();
			return tuoichinh;
	}

}
public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nguoi Person = new Nguoi();
		Scanner nhap = new Scanner(System.in);
		Person.setName(JOptionPane.showInputDialog("Your Name is:  "));
		String nam = JOptionPane.showInputDialog("Your Birthday is(yyyy): ");
		double i = Double.parseDouble(nam);
		Person.setBirthdate(i);
		int t = (int) Person.getAge();
		JOptionPane.showMessageDialog(null,"Hello "+Person.getName()+", Your Age là: "+t);
	}

}
