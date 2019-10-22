package com.example.demo.javase.stream;

import com.example.demo.javase.bean.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8中新增Stream
 *
 * @author ssq
 * @date 2019-10-21 上午 11:06
 */
public class StreamDemo {

	public static void userList(){
		List<User> userList  = new ArrayList<>();
//		userList.stream().
	}

	public static void main1(String args[]){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty() && string.length()>=4).collect(Collectors.toList());
//		System.out.println(filtered);
//		strings.forEach(e -> System.out.print(e+","));
		List<String> filter2 = strings.stream().filter(s -> s.length()>2).collect(Collectors.toList());
		filter2.forEach(e -> System.out.println(e));
	}
	public static void main2(String args[]){
		// 创建集合
		List<String> list = new ArrayList<>();

		// 添加元素
		list.add("sdf");
		list.add("a");
		list.add("asdf");
		list.add("d");
		list.add("basdfgh");
		long count = list.stream().filter(s -> s.length() > 2).count();
		System.out.println(count);
		List<String> list1 = list.stream().filter(s -> s.length() > 2).collect(Collectors.toList());
		list1.forEach(System.out :: println);
		List<String> list2 = list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
		list2.forEach(System.out :: println);
	}

	public static void main3(String[] args) {
		Stream<?> flatMap = Stream.of(Arrays.asList("a", "b"), Arrays.asList(1, 2, 3)).flatMap((s) -> s.stream());
		flatMap.forEach(System.out :: println);
	}
	public static void main4(String[] args) {
		List<Long> list = new ArrayList<>();

		// 封装到集合
		for (long i = 1; i <= 100; i++) {
			list.add(i);
		}

		// 计算
		// reduce：参1，和的初始值
		Long sum = list.stream().parallel().reduce(0L, (r, l) -> r + l);

		System.out.println(sum);
	}

	public static void main5(String[] args) {
		Instant now = Instant.now();
		System.out.println(now);
		LocalDate now2 = LocalDate.now();
		LocalTime now3 = LocalTime.now();
		System.out.println(now2.toString());
		System.out.println(now3.toString());
		String formatterTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(LocalDateTime.now());
		System.out.println(formatterTime);
	}

	public static void main(String[] args) {
		String[] arr = {"a","b","c"};
		String string = String.join("-", arr);
		System.out.println(string);
	}
}
