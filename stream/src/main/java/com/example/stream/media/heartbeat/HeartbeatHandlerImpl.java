package com.example.stream.media.heartbeat;

/**
 * 类功能描述
 *
 * @author ssq
 * @date 2019-08-28 下午 2:50
 */

import java.util.Date;
import java.util.Map;

public class HeartbeatHandlerImpl implements HeartbeatHandler {

	@Override
	public Cmder sendHeartBeat(HeartbeatEntity info) {
		HeartbeatLinstener linstener = HeartbeatLinstener.getInstance();

		// 添加节点
//		if (!linstener.checkNodeValid(info.getNodeID())) {
			linstener.registerNode(info.getNodeID(), info.getTime());
//		}

		// 其他操作
		Cmder cmder = new Cmder();
		cmder.setNodeID(info.getNodeID());
		// ...

		System.out.println("current all the nodes: ");
		Map<String, Date> nodes = linstener.getNodes();
		for (Map.Entry e : nodes.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		System.out.println("hadle a heartbeat");
		return cmder;
	}
}
