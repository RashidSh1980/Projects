package helloworld3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;


public class test3 {
	
	public static void main(String[] args)  {

		CompletableFuture<Integer> future = new CompletableFuture<>();
		new Thread(()->{
			try {
				int result = 100;
				Thread.sleep(3000);
				future.complete(result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
       future.thenAccept(System.out::println);
       System.out.println("Ok");
       
       CompletableFuture.supplyAsync(()->{
    	 return 10;  
       }).thenApply( i -> i + 10).thenAccept(System.out::println);
       
       CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
    	   return 10;
       }); 
       
       CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
    	   return 20;
       }); 

       CompletableFuture[] tasks = {future1,future2};
       
       Object obj = CompletableFuture.anyOf(tasks).join();
       System.out.println(obj);
       
       CompletableFuture.allOf(future1,future2).thenAccept(task -> {
    	  List<Object> objs = Stream.of(tasks).map(CompletableFuture::join).toList();
    	  System.out.println(objs);
       });
       
              
	
	}
	}

