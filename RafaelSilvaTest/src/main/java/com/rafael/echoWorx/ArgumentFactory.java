package com.rafael.echoWorx;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rafael.echoWorx.pojo.A;
import com.rafael.echoWorx.pojo.B;
import com.rafael.echoWorx.pojo.Register;

public class ArgumentFactory {

	private static A registerA = null;
	private static B registerB = null;

	public List<String> getInstructions(String nameFile) {
		List<String> linesInstructions = new ArrayList<>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("files/" + nameFile).getFile());
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					linesInstructions.add(line);
				}
				scanner.close();
			} catch (IOException e) {
				throw new IOException("Not possible open a file", e);
			}
		} catch (Exception e) {
			throw new RuntimeException("Error open file");
		}
		return linesInstructions;
	}

	public static void calculateValueArguments(List<String> linesInstructions)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			Class<?> cpuClass = Class.forName("com.rafael.echoWorx.CPU");
			Object cpu = cpuClass.newInstance();
			Object argument1 = null;
			Object argument2 = null;
			for (String instruction : linesInstructions) {
				if (instruction != null) {
					List<String> listParameters = defineParameters(instruction);
					String methodName = listParameters.get(0);
					argument1 = getArgument(listParameters.get(1));
					argument2 = getArgument(listParameters.get(2));
					invokeMethod(cpu, argument1, argument2, methodName);
				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static Object getArgument(String argument) {
		if ("A".equals(argument)) {
			if (registerA == null) {
				return registerA = new A();
			} else {
				return registerA;
			}
		} else if ("B".equals(argument)) {
			if (registerB == null) {
				return registerB = new B();
			} else {
				return registerB;
			}
		} else {
			return Byte.valueOf(argument);
		}
	}

	private static void invokeMethod(Object cpu, Object argument1, Object argument2, String methodName) {
		try {
			Method method = null;
			if (argument2 instanceof Register) {
				Class<?>[] paramTypes = { Register.class, Register.class };
				method = cpu.getClass().getMethod(methodName, paramTypes);
				method.invoke(cpu, argument1, argument2);
			} else {
				Class<?>[] paramTypes = { Register.class, byte.class };
				method = cpu.getClass().getMethod(methodName, paramTypes);
				method.invoke(cpu, argument1, Byte.valueOf(argument2.toString()));
			}
		} catch (Exception ex) {
			throw new IllegalArgumentException("Illegal Arguments", ex);
		}
	}

	private static List<String> defineParameters(String instruction) {
		String[] allValues = instruction.split(" ");
		// First method name
		String methodName = allValues[0].toLowerCase();
		// List Arguments
		String[] arguments = allValues[1].split(",");
		// Argument
		String argument1 = arguments[0].trim();
		String argument2 = arguments[1].trim();

		List<String> listParameters = new ArrayList<>();
		listParameters.add(methodName);
		listParameters.add(argument1);
		listParameters.add(argument2);

		return listParameters;
	}

	public static A getRegisterA() {
		return registerA;
	}

	public static B getRegisterB() {
		return registerB;
	}

}
