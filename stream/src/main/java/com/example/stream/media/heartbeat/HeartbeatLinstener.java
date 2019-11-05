package com.example.stream.media.heartbeat;

/**
 * 心跳监听保存信息
 *
 * @author ssq
 * @date 2019-08-28 下午 2:45
 */





import com.example.stream.media.controller.StreamMediaController;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeartbeatLinstener {

	//private ExecutorService executor = Executors.newFixedThreadPool(20);

	private final ConcurrentHashMap<String, Date> nodes = new ConcurrentHashMap<String, Date>();
	private final ConcurrentHashMap<String, Date> nodeStatus = new ConcurrentHashMap<String, Date>();

	private long timeout = 10 * 1000;

	// 服务器监听端口
	private int port = 8089;

	// 单例模式
	private static class SingleHolder {
		private static final HeartbeatLinstener INSTANCE = new HeartbeatLinstener();
	}

	private HeartbeatLinstener() {
	}

	public static HeartbeatLinstener getInstance() {
		return SingleHolder.INSTANCE;
	}

	public ConcurrentHashMap<String, Date> getNodes() {
		return nodes;
	}

	public void registerNode(String nodeId, Date date) {
		System.out.println(nodeId+ " : " + date);
		nodes.put(nodeId, date);
		nodeStatus.put(nodeId, date);
		System.out.println("视频监听注册成功");
	}

	public void removeNode(String nodeID) {
		if (nodes.containsKey(nodeID)) {
			nodes.remove(nodeID);
		}
	}

	// 检测节点是否有效
	public boolean checkNodeValid(String key) {
		Date nowDate = new Date();
		if (!nodes.containsKey(key) || !nodeStatus.containsKey(key)) {
			return false;
		}
		if ((nowDate.getTime() - nodeStatus.get(key).getTime()) > timeout) {
			return false;
		}
		return true;
	}

	// 删除所有失效节点
	public void removeInValidNode() throws InterruptedException {
		Iterator<Map.Entry<String, Date>> it = nodeStatus.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Date> e = it.next();
			System.out.println("判断所有失效节点："+ it);
			Date nowDate = new Date();
			//if ((nowDate.getTime() - nodeStatus.get(e.getKey())) > timeout) {
			if ((nowDate.getTime() - e.getValue().getTime())/1000 > timeout) {
				StreamMediaController streamMediaController = new StreamMediaController();
				nodes.remove(e.getKey());
				streamMediaController.stopTaskByChannel(e.getKey());
				System.out.println("即将中断的通道编号："+ e.getKey());
			}
		}
	}

}