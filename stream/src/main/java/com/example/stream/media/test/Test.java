package com.example.stream.media.test;

import com.alibaba.fastjson.JSONObject;
import com.example.stream.media.commandbuidler.CommandBuidlerFactory;
import com.example.stream.media.data.CommandTasker;
import com.example.stream.media.service.CommandManager;
import com.example.stream.media.service.impl.CommandManagerImpl;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * 测试
 * @author eguid
 * @since jdk1.7
 * @version 2017年10月13日
 */
public class Test {
	/**
	 * 命令组装器测试
	 * @throws InterruptedException
	 */
	public static void test1() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		Map<String,String> map = new HashMap<String,String>();
		map.put("appName", "test.m3u8");
		map.put("input", "rtsp://19.67.161.199:9090/dss/monitor/params?cameraid=1000003%241&substream=1");
		map.put("output", "E:/streaming_media/nginx-1.7.11.3-Gryphon/nginx-rtmp-module/hls/");
		map.put("codec", "h264");
		map.put("fmt", "hls");
		map.put("fps", "25");
		map.put("rs", "640x360");
		map.put("twoPart", "1");
		// 执行任务，id就是appName，如果执行失败返回为null
		String id = manager.start(map);
		System.out.println(id);
		// 通过id查询
		CommandTasker info = manager.query(id);
		System.out.println(info);
		// 查询全部
		Collection<CommandTasker> infoList = manager.queryAll();
		System.out.println(infoList);
		Thread.sleep(30000);
		// 停止id对应的任务
		manager.stop(id);
	}
	/**
	 * 默认方式，rtsp->rtmp转流单个命令测试
	 * @throws InterruptedException
	 */
	public static void test2() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp
		//测试多个任何同时执行和停止情况
		//默认方式发布任务
		//-re 按照时间戳顺序推流 不加的话直接把所有文件都推过去
		manager.start("1000003", "ffmpeg -i rtsp://19.67.161.199:9090/dss/monitor/params?cameraid=1000003%241&substream=1 -f hls -hls_time 2 -hls_list_size 5 -hls_wrap 6 -vcodec copy -an E:/streaming_media/nginx-1.7.11.3-Gryphon/nginx-rtmp-module/hls/test.m3u8");
		Collection<CommandTasker> all = manager.queryAll();
		System.out.println("all:"+all);
		Thread.sleep(60000);
//		// 停止全部任务
		manager.stopAll();
	}
	/**
	 * 完整ffmpeg路径测试
	 * @throws InterruptedException
	 */
	public static void test4() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp
		//测试多个任何同时执行和停止情况
		//默认方式发布任务
		manager.start("tomcat", "D:/TestWorkspaces/FFmpegCommandHandler/src/cc/eguid/FFmpegCommandManager/ffmpeg/ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",true);

		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}

	/**
	 * rtsp-rtmp转流多任务测试
	 * @throws InterruptedException
	 */
	public static void test3() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp
		//测试多个任何同时执行和停止情况
		//false表示使用配置文件中的ffmpeg路径，true表示本条命令已经包含ffmpeg所在的完整路径
		manager.start("tomcat", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",false);
		manager.start("tomcat1", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat1",false);
		manager.start("tomcat2", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat2",false);
		manager.start("tomcat3", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat3",false);
		manager.start("tomcat4", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat4",false);
		manager.start("tomcat5", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat5",false);
		manager.start("tomcat6", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat6",false);
		manager.start("tomcat7", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat7",false);
		manager.start("tomcat8", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat8",false);
		manager.start("tomcat9", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat9",false);

		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}

	/**
	 * 测试流式命令行构建器
	 * @throws InterruptedException
	 */
	public static void testStreamCommandAssmbly() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test1"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	/**
	 * 测试任务中断自动重启任务
	 * @throws InterruptedException
	 */
	public static void testBroken() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test1"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
		manager.destory();
	}
	/**
	 * 批量测试任务中断自动重启任务
	 * @throws InterruptedException
	 */
	public static void testBrokenMuti() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test1"));
		manager.start("test2", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test2"));
		manager.start("test3", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test3"));
		manager.start("test4", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test4"));
		manager.start("test5", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
//				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test5"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
		manager.destory();
	}
	public static void interfaceUtil(String path,String data) {
		try {
			URL url = new URL(path);
			//打开和url之间的连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			PrintWriter out = null;
			OutputStream out = null; //写
			//请求方式,默认为GET请求
			conn.setRequestMethod("POST");
//           //设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			//一定要设置 Content-Type 要不然服务端接收不到参数
			//conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
			//设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
			//最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
			//post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//获取URLConnection对象对应的输出流
//			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数即数据
//			out.print(data);

			//传入参数
			out = conn.getOutputStream();
			out.write(data.getBytes());
			//缓冲数据
			out.flush();
			//获取URLConnection对象对应的输入流
			InputStream is = conn.getInputStream();
			//构造一个字符流缓存
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			//关闭流
			is.close();
			//断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
			//固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
			conn.disconnect();
			System.out.println("完整结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		JSONObject json = new JSONObject();
		json.put("DEPT_NAME", "测试");
		json.put("FIELD_2_1", "测试1");
		json.put("FIELD_2_2", "测试2");
		json.put("FIELD_2_3", "测试3");
		interfaceUtil("http://192.168.0.41:36084/streamMedia/hello/1000390@001$1$0$0", json.toJSONString());
//		test1();
//		test2();
//		test3();
//		test4();
//		testStreamCommandAssmbly();
//		testBroken();
//		testBrokenMuti();
//		String channel = "1000022@001$1$0$4";
//		String firstChannel = channel.replace("@","%40");
//		String secondChannel = firstChannel.replace("$1$0","");
//		String thirdChannel = secondChannel.replace("$", "%24");

		//日期处理----------------------------------------------
//		Date date = new Date();
//		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar.getTime());
//		Date date1 =calendar.getTime();
//		calendar.add(Calendar.MINUTE,-1);
//		System.out.println(calendar.getTime());
//		Date date2 =calendar.getTime();
//		System.out.println((date1.getTime()-date2.getTime())/1000);

//map测试
//		Map<Integer,Integer> map=new HashMap<>();
//		//初始化map
//		for(int i=0;i<10;i++){
//			map.put(i,i);
//		}
//		for(Map.Entry<Integer,Integer> entry:map.entrySet()){
//			if(entry.getKey()==1){
//				map.remove(entry.getKey());
//			}
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
//		System.out.println(UUID.randomUUID().toString());
	}
}
