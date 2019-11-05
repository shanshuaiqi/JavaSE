package com.example.stream.media.service;


import com.example.stream.media.data.CommandTasker;

import java.util.Collection;

/**
 * 视频最新的请求时间管理
 *
 * @author ssq
 * @date 2019-08-30 上午 11:57
 */
public interface StreamMediaService {

	String updateTaskMap(String channel) throws InterruptedException;//修改视频请求的最新时间
	Collection<CommandTasker> queryAllTask();//查询所有进行的转码任务
	void stopTaskByChannel(String channel);//根据视频channel中断指定的转码线程
	void stopAllTask();//停止所有正在进行的转码操作
	Object transcoding(String channel) throws InterruptedException;//执行视频转码
}
