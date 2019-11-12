package com.example.common.springFramework.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;

/**
 * 类功能描述
 *
 * @author ssq
 * @date 2019-11-12 下午 3:00
 */
public class ResourceImplDemo {
	public static void main(String[] args) throws IOException {

//Resource 接口一些比较重要的方法如下：
//	• getInputStream(): 定位并打开资源，返回读取此资源的一个 InputStream。每次调用预期会返回一个新的 InputStream，由调用者负责关闭这个流。
//	• exists(): 返回标识这个资源在物理上是否的确存在的 boolean 值。
//	• isOpen(): 返回标识这个资源是否有已打开流的处理类的 boolean 值。如果为 true，则此InputStream 就不能被多次读取， 而且只能被读取一次然后关闭以避免资	  		源泄漏。除了 InputStreamResource，常见的resource实现都会返回 false。
//	• getDescription(): 返回资源的描述，一般在与此资源相关的错误输出时使用。此描述通常是完整的文件名或实际的URL地址
//		以下为spring当中resource接口的实现类及其使用：

//		UrlResource 封装了java.net.URL，它能够被用来访问任何通过URL可以获得的对象，例如：文件、HTTP对象、FTP对象等。所有的URL都有个标准的 String表示，
// 		这些标准前缀可以标识不同的URL类型，包括file:访问文件系统路径，http: 通过HTTP协议访问的资源，ftp: 通过FTP访问的资源等等
		UrlResource urlResource = new UrlResource("http://www.slience.com/book.xml");
		System.out.println("urlResource-Filename: "+urlResource.getFilename());
		System.out.println("urlResource-Description: "+urlResource.getDescription());
		System.out.println("urlResource-File: "+urlResource.isFile());
		System.out.println("--------------------------------------------------------------");


//		ClassPathResource
		ClassPathResource classPathResource = new ClassPathResource("classpath:com/example/common/springFramework/resource/book.xml");
		System.out.println("classPathResource-Filename: "+classPathResource.getFilename());
		System.out.println("classPathResource-Path: "+classPathResource.getPath());
		System.out.println("classPathResource-Description: "+classPathResource.getDescription());
		System.out.println("classPathResource-File: "+classPathResource.isFile());
		System.out.println("--------------------------------------------------------------");

//		这是为处理 java.io.File 而准备的Resource实现。它既可以作为File提供，也可以作为URL。
//		FileSystemResource
		FileSystemResource fileSystemResource = new FileSystemResource("");

//		ServletContextResource
//		ServletContextResource servletContextResource = new ServletContextResource("","");

//		InputStreamResource

//		ByteArrayResource


//		ResourceLoader 接口由能返回(或者载入)Resource 实例的对象来实现。 实现getResource方法
// 		classpath: classpath:com/myapp/config.xml    从classpath中加载。
// 		file:      file:/data/config.xml             作为 URL 从文件系统中加载
// 		http:      http://myserver/logo.png          作为 URL 加载。
// 		(none)     /data/config.xml                  根据 ApplicationContext 进行判断。




		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
		Resource template1 = classPathXmlApplicationContext.getResource("some/resource/path/myTemplate.txt");
		Resource template2 = classPathXmlApplicationContext.getResource("classpath:some/resource/path/myTemplate.txt");
		Resource template3 = classPathXmlApplicationContext.getResource("file:/some/resource/path/myTemplate.txt");
		Resource template4 = classPathXmlApplicationContext.getResource("http://myhost.com/resource/path/myTemplate" +
				".txt");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:application.yml");
		System.out.println(ctx.getResource("application.yml").getFilename());
		System.out.println("--------------------------------------------------------------");
	}


}
