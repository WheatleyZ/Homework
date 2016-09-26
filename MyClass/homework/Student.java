package homework;

import java.util.*;

public class Student{
	public String name;
	public int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String toString() {
		return name + "," + score;
	}

	public static void main(String[] args) {
		String[] names = { "张三", "李四", "小明", "王五", "何六" };
		int[] scores = { 90, 80, 85, 60, 70 };
		Vector<Student> group1 = new Vector<Student>(5);
		for (int i = 0; i < 5; i++) {
			group1.add(new Student(names[i], scores[i]));
		}
		for (Student s : group1) {
			if (s.score == 60)
				System.out.println(s);
		}

	}
}
