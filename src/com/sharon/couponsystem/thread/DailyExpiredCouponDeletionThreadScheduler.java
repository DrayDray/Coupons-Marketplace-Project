package com.sharon.couponsystem.thread;

public class DailyExpiredCouponDeletionThreadScheduler extends Thread {

	public void run(){
		while (true){
			DailyExpiredCouponDeletionThread worker = new DailyExpiredCouponDeletionThread();
			worker.start();
			try{
				//wait until going through the task again for 24 hours (86400000milliseconds)
				Thread.sleep(86400000);;
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
