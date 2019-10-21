package com.example.demo.javase.lamdba;

import java.util.function.Function;

/**
 * a.功能性接口：Function<T, R>
 有输入参数，有返回值
 是对接收一个T类型参数，返回R类型的结果的方法的抽象
 通过调用apply方法执行内容
 需求：给定一个字符串，返回字符串长度
 * @author ssq
 * @date 2019-10-21 下午 4:32
 */
public class Demo1 {
	public static void main(String[] args) {
		// 定义字符串
		String str = "helloworld";

		// 调用方法
		// 在调用的时候写方法体，方法比较灵活
		int length = testFun(str, (s) -> s.length());

		System.out.println(length);
	}

	// 方法
	/**
	 *
	 * @param str 输入参数
	 * @param fun 表达式 String 为输入类型，Integer为输出类型
	 * @return 返回字符串长度
	 */
	public static int testFun(String str, Function<String, Integer> fun) {
		// 执行
		Integer length = fun.apply(str);

		return length;
	}

}
