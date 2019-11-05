package com.example.stream.media.controller;


import com.example.stream.media.data.CommandTasker;
import com.example.stream.media.service.CommandManager;
import com.example.stream.media.service.StreamMediaService;
import com.example.stream.media.service.impl.CommandManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 通过ffmpeg完成视频转码
 */
@RestController
@RequestMapping(value = "/streamMedia")
public class StreamMediaController {

	@Autowired
	private StreamMediaService streamMediaService;

	/**
	 * 仅做测试使用
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/test")
	public Object test() {
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp
		//测试多个任何同时执行和停止情况
		//默认方式发布任务
		manager.start("tomcat", "ffmpeg -rtsp_transport tcp -i rtsp://19.67.161.199:9090/dss/monitor/params?cameraid="
				+ "1000003%240&substream=1 -f hls -hls_time 2 -hls_list_size 3 -hls_wrap 6 -vcodec copy -an " +
//					"E:/streaming_media/nginx-1.7.11.3-Gryphon/nginx-rtmp-module/hls/" +
				"/usr/local/nginx/html/hls/" +
				"1000003.m3u8",true);

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 停止全部任务
		manager.stopAll();
		return "http://localhost:36083/streamMedia/hello";
	}

	/**
	 * 此方法暂时只支持一路视频转码
	 *
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/transcoding/{channel}")
	public synchronized Object transcoding(@PathVariable String channel) throws InterruptedException {
		return streamMediaService.transcoding(channel);
	}

	/**
	 * 中断所有的线程
	 *
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "/stopTranscoding")
	public synchronized void stopTranscoding() throws InterruptedException {
		streamMediaService.stopAllTask();
	}

	/**
	 * 获取所有的任务线程
	 *
	 * @return
	 */
	public Collection<CommandTasker> queryAllTask() {
		return streamMediaService.queryAllTask();
	}

	/**
	 * 根据任务id停止对应的线程
	 *
	 * @param channel
	 * @throws InterruptedException
	 */
	public synchronized void stopTaskByChannel(String channel) throws InterruptedException {
		streamMediaService.stopTaskByChannel(channel);
	}

	/**
	 * 心跳机制中断转码线程
	 *
	 * @param channel
	 */
	@RequestMapping(value = "/taskHeart/{channel}")
	public synchronized Object taskHeart(@PathVariable String channel) throws InterruptedException {
		System.out.println("保活通道："+channel);
		String back = streamMediaService.updateTaskMap(channel);
		return back;
	}

}
