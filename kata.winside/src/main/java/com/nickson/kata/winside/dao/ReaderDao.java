package com.nickson.kata.winside.dao;

import java.io.IOException;
import java.util.List;

public interface ReaderDao {
	public List<Object> read() throws IOException;
}
