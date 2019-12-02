package cn.sunline.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PrintClass {
	
	private int flag =1;
	private Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
	
	public void printA(int loopCount) {
		lock.lock();
		try {
			while (flag != 1) {
				c1.await();
			}
			for (int i = 1; i <= 3; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮:" + "AAA");
			}
			flag = 2;
			c2.signal();
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void printB(int loopCount) {
		lock.lock();
		try {
			while (flag != 2) {
				c2.await();
			}
			for (int i = 1; i <= 4; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮:" + "BBB");
			}
			flag = 3;
			c3.signal();
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
		}
	}
	
	public void printC(int loopCount) {
		lock.lock();
		try {
			while (flag != 3) {
				c3.await();
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + ",第" + loopCount + "轮:" + "CCC");
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
 * A线程打印"AAA"3次,接着B线程打印"BBB"4次，再接着C线程打印"CCC"5次,共5轮
 * @author ASUS
 *
 */
public class ThreadNext {
	
	public static void main(String[] args) {
		PrintClass pc = new PrintClass();
		new Thread(() -> { for (int i = 1; i <= 5; i++) pc.printA(i);},"A线程").start();
		new Thread(() -> { for (int i = 1; i <= 5; i++) pc.printB(i);},"B线程").start();
		new Thread(() -> { for (int i = 1; i <= 5; i++) pc.printC(i);},"C线程").start();
		
	}

}
