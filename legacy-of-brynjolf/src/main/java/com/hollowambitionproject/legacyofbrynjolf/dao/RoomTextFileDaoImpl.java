package com.hollowambitionproject.legacyofbrynjolf.dao;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.hollowambitionproject.legacyofbrynjolf.util.FileUtil;

/**
 * The Class RoomTextFileDaoImpl.
 */
@Component
@Primary
public class RoomTextFileDaoImpl implements RoomDao {

	/** The Constant LOG. */
	private final static Logger LOG = LogManager.getLogger(RoomTextFileDaoImpl.class);

	/** The Constant ROOM_FILE_PATH. */
	private final static String ROOM_FILE_PATH = "input/room.txt";

	/**
	 * Gets the room data. Parses text file line by line.
	 *
	 * @return the room data
	 */
	@Override
	public List<String> getRoomData() {
		List<String> lines = null;
		try {
			lines = FileUtil.parseTextFileToStringList(ROOM_FILE_PATH);
		} catch (IOException e) {
			LOG.error("Unable to parse room.txt file", e);
			Runtime runtime = Runtime.getRuntime();
			runtime.exit(1);
		}

		return lines;
	}

}
