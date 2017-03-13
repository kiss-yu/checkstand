package com.checkstand.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
@Component
public class CSocket implements InitializingBean {
	@Resource
	private GetPrace prace;
	@Override
	public void afterPropertiesSet(){
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					prace.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
