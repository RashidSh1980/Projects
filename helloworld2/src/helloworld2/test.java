package helloworld2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;

public class test {

	public static void main(String[] args) throws InterruptedException {
		
	LongAccumulator ak = new LongAccumulator(Long::sum,100);
	ak.accumulate(100);
	System.out.println(ak.get());

	}
}
