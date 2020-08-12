package com.hollowambitionproject.legacyofbrynjolf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * The Class LegacyOfBrynjolfApplication.
 */
@SpringBootApplication
public class LegacyOfBrynjolfApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LegacyOfBrynjolfApplication.class);
		// Starts the program.
		ctx.getBean(LegacyOfBrynjolfRunner.class).start(args.length == 0 ? "" : args[0]);
	}

}
