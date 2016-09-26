package homework;

import java.util.*;

public class Student {
	public String name;
	public int score;

	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public Integer getScore() {
		return this.score;
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

		Collections.sort(group1, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return -(o1.getScore().compareTo(o2.getScore()));
			}
		});

		for (Student s : group1) {
			if (s.score == 60)
				System.out.println("*" + s);
			else
				System.out.println(s);
		}

		for (int i = 0; i < group1.capacity(); i++) {
			for (int j = group1.capacity()-1; j > i; j--) {
				if (group1.get(j).score > group1.get(j - 1).score) {
					Student tmp = group1.get(j);
					group1.remove(j);
					group1.add(j - 1, tmp);
				}
			}
		}

		for (Student s : group1) {
			if (s.score == 60)
				System.out.println("*" + s);
			else
				System.out.println(s);
		}

	}
}
