package com.example.stream.media.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 程序基础配置
 * 
 * @author eguid
 * 
 */
@Component
@ConfigurationProperties(prefix = "stream-media", ignoreInvalidFields = true)
@Data
public class ProgramConfig {
	
	private String path;//默认命令行执行根路径
	private boolean debug;//是否开启debug模式
	private Integer size;//任务池大小
	private String callback;//回调通知地址
	private boolean keepalive;//是否开启保活
	private String requesturl;//访问请求地址
	private String rtspurl;//拉流地址
	private String pushaddress;//推送服务器地址

	@Override
	public String toString() {
		return "ProgramConfig [path=" + path + ", debug=" + debug + ", size=" + size + ", callback=" + callback
				+ ", keepalive=" + keepalive + "]";
	}
}
