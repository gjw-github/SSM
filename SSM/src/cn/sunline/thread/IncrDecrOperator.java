package cn.sunline.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
	
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void increment(int loopCount) {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			++number;
			System.out.println(Thread.currentThread().getName() + "第" + loopCount + "轮" + number);
			condition.signal();
			
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement(int loopCount) {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			--number;
			System.out.println(Thread.currentThread().getName() + "第" + loopCount + "轮" + number);
			condition.signal();
			
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
}

/**
 * 两个线程，A线程加一，B线程减一，来10轮
 * @author ASUS
 *
 */
public class IncrDecrOperator {
	
	public static void main(String[] args) {
		ShareData sd = new ShareData();
		new Thread(() -> {for (int i = 1; i <= 10; i++) sd.increment(i);},"A线程").start();
		new Thread(() -> {for (int i = 1; i <= 10; i++) sd.decrement(i);},"B线程").start();
	}

}
