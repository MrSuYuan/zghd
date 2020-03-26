package com.zghd.entity.BaiDu;

public  class GlobalToken {

	public static String url = "http://api.ydtad.com/ydt-server/";
	public static String token="";
	/***反作弊redis key过期日期*/
	public static int redis_exp_second =60*40;
	
	public static boolean outputlog=false; //日志输出
	public static boolean SPARK=false; //数据入kafka
}
