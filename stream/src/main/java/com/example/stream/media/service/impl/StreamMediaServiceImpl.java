package com.example.stream.media.service.impl;



import com.example.stream.media.config.ProgramConfig;
import com.example.stream.media.data.CommandTasker;
import com.example.stream.media.service.CommandManager;
import com.example.stream.media.service.StreamMediaService;
import com.example.stream.media.util.SymbolTranscodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 类功能描述
 *
 * @author ssq
 * @date 2019-08-30 下午 1:33
 */
@Service
public class StreamMediaServiceImpl implements StreamMediaService {

	public static CommandManager manager = new CommandManagerImpl();

	public static Map<String, Date> TASKMAP = new HashMap<>();
	@Autowired
	private ProgramConfig programConfig;

	@Override
	public synchronized String updateTaskMap(String channel) throws InterruptedException {
		if (channel == null) {
			Set<Map.Entry<String, Date>> set = TASKMAP.entrySet();
			Iterator<Map.Entry<String, Date>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Date> entry = iterator.next();
				String id = entry.getKey();
				Date streamDate = entry.getValue();
				Date nowDate = new Date();
				long timeDifference = (nowDate.getTime() - streamDate.getTime()) / 1000;
				if (timeDifference > 10) {
//					removeTaskMap(id);
//					stopTaskByChannel(id);
					iterator.remove();
					stopTaskByChannel(id);
					System.out.println("这个任务被停止了:" + id + "-----" + streamDate);
				}
				System.out.println(TASKMAP);

			}
			return null;
		} else {
//			SysUser sessionUser = SSOUtil.getSession().getSysUser();
//			String user_id = sessionUser.getId();//获取用户id
//			System.out.println(sessionUser.getName());
			if (TASKMAP.size() > 4) {
				return "failed";
			} else {
				Date date = new Date();
				TASKMAP.put(channel, date);
				return "success";
			}
		}
	}

	/**
	 * 获取所有的任务线程
	 *
	 * @return
	 */
	@Override
	public Collection<CommandTasker> queryAllTask() {
		Collection<CommandTasker> all = manager.queryAll();
//		System.out.println("all:" + all);
		return all;
	}

	@Override
	public void stopTaskByChannel(String channel) {
		manager.stop(channel);
	}

	@Override
	public void stopAllTask() {
		manager.stopAll();
	}

	@Override
	public synchronized Object transcoding(String channel) throws InterruptedException {
//		Map<String, Object> map = new HashMap<String, Object>();
		CommandTasker commandTasker = manager.query(channel);
		String newChannel = SymbolTranscodingUtil.transcoding(channel);
		String videoName = SymbolTranscodingUtil.trans_$(SymbolTranscodingUtil.trans_$1$0(SymbolTranscodingUtil.trans(channel)));
//		String url = "http://19.67.159.2:36083/hls/" + videoName + ".m3u8";
		String url = programConfig.getRequesturl().replace("name",videoName);
		String rtspChannel = programConfig.getRtspurl().replace("channel",newChannel);
		String pushAddress = programConfig.getPushaddress().replace("name",videoName);
		if (StringUtils.isEmpty(commandTasker)){
			updateTaskMap(channel);
//			String command = "ffmpeg -rtsp_transport tcp -i rtsp://19.67.161.199:9090/dss/monitor/params?cameraid="
//					+ newChannel + "&substream=1 -f hls -hls_time 2 -hls_list_size 3 -hls_wrap 6 -vcodec copy -an " +
////					"E:/streaming_media/nginx-1.7.11.3-Gryphon/nginx-rtmp-module/hls/" +
//					"/usr/local/nginx/html/hls/" +
//					videoName +
//					".m3u8";
			String command = "ffmpeg -rtsp_transport tcp -i "+ rtspChannel +" -f hls -hls_time " +
					"2 -hls_list_size 3 -hls_wrap 6 -vcodec copy -an " +pushAddress;
			manager.start(channel, command,true);
			Collection<CommandTasker> all = manager.queryAll();
			System.out.println("all:" + all);
			Thread.sleep(3000);//延时两秒，让工具开始实现转码
//		// 停止全部任务
//		manager.stopAll();
//			map.put("url", url);
			return url;
		}
//		map.put("url",url);
		return url;

	}
}
