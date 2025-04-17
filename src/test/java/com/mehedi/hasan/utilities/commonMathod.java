package com.mehedi.hasan.utilities;

import com.mehedi.hasan.drivers.BaseDriver;

public class commonMathod extends BaseDriver {
	
	public void timeout(int time) throws InterruptedException {
      Thread.sleep(time);
	}

}
