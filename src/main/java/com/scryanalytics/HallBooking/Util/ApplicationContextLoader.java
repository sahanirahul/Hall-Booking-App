package com.scryanalytics.HallBooking.Util;

import org.springframework.context.ApplicationContext;

public class ApplicationContextLoader {

	public static ApplicationContext appContext;
	
	public ApplicationContextLoader()
	{
		
	}

	public static ApplicationContext getAppContext() {
		return appContext;
	}

	public static void setAppContext(ApplicationContext appContext) {
		ApplicationContextLoader.appContext = appContext;
	}
}
