package cn.sunline.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类,设置票数和可重入锁
class Tickets {
	
	private volatile int tickets = 100;
	private Lock lock = new ReentrantLock();
	
	public void saleTickets() {
		lock.lock();
		try {
			if ( tickets > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出第" + (100 - --tickets) + "张票");
			}
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
}

/**
* 线程操作资源类
* 高类聚+低耦合
* @author guang
*/
public class SaleTickets {

	public static void main(String[] args) {
		Tickets tickets = new Tickets();
		//Lambda表达式
		new Thread(() -> {for (int i = 1; i <= 100; i++) tickets.saleTickets();},"线程A").start();
		new Thread(() -> {for (int i = 1; i <= 100; i++) tickets.saleTickets();},"线程B").start();
		new Thread(() -> {for (int i = 1; i <= 100; i++) tickets.saleTickets();},"线程C").start();
	}
}
