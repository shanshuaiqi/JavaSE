package com.example.demo.javase.Stream;

import com.example.demo.javase.bean.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	public static void main(String args[]){
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		List<String> filtered = strings.stream().filter(string -> !string.isEmpty() && string.length()>=4).collect(Collectors.toList());
//		System.out.println(filtered);
//		strings.forEach(e -> System.out.print(e+","));
		List<String> filter2 = strings.stream().filter(s -> s.length()>2).collect(Collectors.toList());
		filter2.forEach(e -> System.out.println(e));
	}
}
