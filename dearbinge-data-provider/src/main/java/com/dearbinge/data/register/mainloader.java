package com.dearbinge.data.register;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainloader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dubbo-spring-mybatis.xml");
		ctx.refresh();
		ctx.start();
		Scanner scan = new Scanner(System.in);
		System.out.println("service started");
		Boolean wait = true;
		while (wait) {
			String result = scan.nextLine();
			while (!result.equals("stop")) {
				System.out.println("service continue");
				result = scan.nextLine();
			}
			System.out.println("are you sure stop services?");
			result = scan.nextLine();
			if (!result.equals("y")) {
				System.out.println("service continue");
				continue;
			}
			wait = false;
		}
		System.out.println("service end");
		ctx.close();
		scan.close();
	}
}
