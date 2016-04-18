package com.jds.prodord.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogUtils {

	public static void setUp() {
		PropertyConfigurator.configure(
			LogUtils.class.getResource("/prodord-log4j.properties"));
	}
	
	/**
	 * DEBUGログを出力します。
	 * @param log
	 * @param o
	 */
	public static void debug(Logger log, Object o){
		LogUtils.setUp();
		log.debug(o);
	}

	/**
	 * INFOログを出力します。
	 * @param log
	 * @param o
	 */
	public static void info(Logger log, Object o){
		LogUtils.setUp();
		log.info(o);
	}
	
}

