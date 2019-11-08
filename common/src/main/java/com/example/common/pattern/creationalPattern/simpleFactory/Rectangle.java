package com.example.common.pattern.creationalPattern.simpleFactory;

/**
 * 创建实现接口的实体类
 *
 * @author ssq
 * @date 2019-11-08 下午 2:40
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Rectangle::draw() method.");
	}
}