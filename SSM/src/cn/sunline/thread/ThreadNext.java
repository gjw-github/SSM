package cn.sunline.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareObject {
	
	private int flag = 1;
	private Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void printAAA(int loopCount) {
		lock.lock();
		try {
			while ( flag != 1) {
				c1.await();
			}
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮：" + "AAA");
			}
			flag = 2;
			c2.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}
	
	public void printBBB(int loopCount) {
		lock.lock();
		try {
			while ( flag != 2) {
				c2.await();
			}
			for (int i = 0; i < 4; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮：" + "BBB");
			}
			flag = 3;
			c3.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}
	
	public void printCCC(int loopCount) {
		lock.lock();
		try {
			while ( flag != 3) {
				c3.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮：" + "CCC");
			}
			flag = 1;
			c1.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}
	
}

/**
 * 线程接力
 * A线程打印3遍，紧接着B线程打印4遍，再接着C线程打印5遍
 * @author ASUS
 *
 */
//public class ThreadNext {
//	
//	public static void main(String[] args) {
//		ShareObject so = new ShareObject();
//		new Thread(() -> {so.printAAA(1);},"A线程").start();
//		new Thread(() -> {so.printBBB(1);},"B线程").start();
//		new Thread(() -> {so.printCCC(1);},"C线程").start();
//	}
//
//}

/**
 * 线程接力
 * A线程打印3遍，紧接着B线程打印4遍，再接着C线程打印5遍,来五轮
 * @author ASUS
 *
 */
public class ThreadNext {
	
	public static void main(String[] args) {
		ShareObject so = new ShareObject();
		new Thread(() -> {for (int i = 1; i <= 5; i++) so.printAAA(i);}, "A线程").start();
		new Thread(() -> {for (int i = 1; i <= 5; i++) so.printBBB(i);}, "B线程").start();
		new Thread(() -> {for (int i = 1; i <= 5; i++) so.printCCC(i);}, "C线程").start();
	}

}
