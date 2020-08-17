package com.clive.utils;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class IdGenerator {
	public static String getGenerator(String fileName){
		//fileName == 1.jpg
		String name = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
		return name;
	}
}
