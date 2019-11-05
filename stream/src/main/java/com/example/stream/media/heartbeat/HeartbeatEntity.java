package com.example.stream.media.heartbeat;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 心跳包实体类
 *
 * @author ssq
 * @date 2019-08-28 下午 2:42
 */
@Data
public class HeartbeatEntity implements Serializable {
	private Date time;
	private String nodeID;
	private String error;
	private Map<String, Object> info = new HashMap<String, Object>();
}

