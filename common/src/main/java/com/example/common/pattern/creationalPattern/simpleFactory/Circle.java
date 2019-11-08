package com.example.common.pattern.creationalPattern.simpleFactory;

/**
 * 类功能描述
 *
 * @author ssq
 * @date 2019-11-08 下午 2:41
 */
public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Circle::draw() method.");
	}
}