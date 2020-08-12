package com.hollowambitionproject.legacyofbrynjolf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

/**
 * The Class FileUtil.
 */
public class FileUtil {

	/**
	 * Parses the text file to string list. Reads file line by line and adds to a
	 * list.
	 *
	 * @param path the path
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static List<String> parseTextFileToStringList(String path) throws IOException {
		List<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(new ClassPathResource(path).getInputStream(), "UTF-8"));
		String line;
		while ((line = reader.readLine()) != null) {
			lines.add(line);
		}
		reader.close();
		return lines;

	}

}
