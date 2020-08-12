package com.hollowambitionproject.legacyofbrynjolf.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * The Class RoomConstantDaoImpl. Only for debugging purposes. Use txt file for
 * actual input. To use this, replace bean in InitializeRoomService with this
 * qualifier.
 */
@Deprecated
@Component("stringRoomDaoImpl")
public class RoomConstantDaoImpl implements RoomDao {

	/**
	 * Gets the room data.
	 *
	 * @return the room data
	 */
	@Override
	public List<String> getRoomData() {
		// Only for debugging. Use room.txt file for actual inputs.
		List<String> lines = new ArrayList<>();
		lines.add(". . o x .");
		lines.add("g x . . .");
		lines.add(". b . . x");
		lines.add("x . g . .");
		lines.add("x . g . .");
		return lines;
	}

}
