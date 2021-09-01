package com.bo.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
	private NewBook newBook;
	private String name;

	public static void main(String[] args) {
		new Student().go();
	}
	private void go(){
		try {
			ObjectOutputStream out  = new ObjectOutputStream(new FileOutputStream("seria"));
			Student student1 = new Student(new NewBook(2011,"moree"),"kevin");
			out.writeObject(student1); //
			out.reset();
			student1.setName("Jordan");
			out.writeObject(student1);
			out.reset();
			student1.setName("Paul");
			out.writeObject(student1);
			System.out.println("object has been written..");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("seria"));
			Student s1 = (Student)in.readObject();
			Student s2 = (Student)in.readObject();
			Student s3 = (Student)in.readObject();
			System.out.println("Objects read here: ");
			System.out.println("Student1's name: "+s1.getName());
			System.out.println("Student2's name: "+s2.getName());
			System.out.println("Student3's name: "+s3.getName());
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class NewBook implements Serializable{
	private Integer id;
	private String name;
}