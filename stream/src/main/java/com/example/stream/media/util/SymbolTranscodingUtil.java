package com.example.stream.media.util;

import org.springframework.stereotype.Component;

/**
 * 视频通道操作工具
 * @author ssq 20190822
 *
 */
@Component
public class SymbolTranscodingUtil {
	/**
	 * 进行简单的符号转码操作
	 * @param channel
	 * @return
	 */
	public static String transcoding(String channel){
		String firstChannel = channel.replace("@","%40");
		String secondChannel = firstChannel.replace("$1$0","");
		String thirdChannel = secondChannel.replace("$", "%24");
		return thirdChannel;
	}
	public static String trans(String channel){
		String newChannel = channel.replace("@","");
		return newChannel;
	}
	public static String trans_$1$0(String channel){
		String newChannel = channel.replace("$1$0","");
		return newChannel;
	}
	public static String trans_$(String channel){
		String newChannel = channel.replace("$", "");
		return newChannel;
	}

	
}
