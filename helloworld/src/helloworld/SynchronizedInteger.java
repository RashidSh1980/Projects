package helloworld;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SynchronizedInteger {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("Start ....");

		Callable<String> eval1 = new Callable<String>() {
			public String call() throws InterruptedException {
                Thread.sleep(2000);
				return "Java1";
			}
		};

		Callable<String> eval2 = new Callable<String>() {
			public String call() throws InterruptedException {
                Thread.sleep(2000);
				return "Java2";
			}
		};

        List<Callable<String>> list = new ArrayList<Callable<String>>();		
		//FutureTask<String> ft = new FutureTask<String>(eval);
		//ft.run(); 

		final ExecutorService exec = Executors.newFixedThreadPool(2);
		CompletionService completionService = new ExecutorCompletionService(exec);
		
		completionService.submit(eval1);
		completionService.submit(eval2);
		
		try {
            for(int i = 0; i < 3; i++) {
			Future<String> result = completionService.take();
			System.out.println(result.get());
            }
		} catch (InterruptedException | ExecutionException e) {
			System.err.println("Stop");
	       
		}
		
		exec.shutdown();
           
	}
}