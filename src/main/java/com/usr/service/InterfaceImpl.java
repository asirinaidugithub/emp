package com.usr.service;

import java.util.concurrent.*;

public class InterfaceImpl implements Interface1, Interface2 {

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		ExecutorService service = Executors.newFixedThreadPool(1);

		Callable<Integer> call = () -> {
			try {
				Thread.sleep(TimeUnit.MICROSECONDS.ordinal(), 5000);
				return 1234;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		};

		Future<Integer> future = service.submit(call);

        future.get(5000,TimeUnit.SECONDS);
	}
	@Override
	public void display() {
		this.display();
	}

}

@FunctionalInterface
interface Interface1 {
	void display();

	default void cal() {
		System.out.println("Cal method of Interface1");
	}
}

@FunctionalInterface
interface Interface2 {
	void display();

	default void cal1() {
		System.out.println("Cal method of Interface2");
	}
}
