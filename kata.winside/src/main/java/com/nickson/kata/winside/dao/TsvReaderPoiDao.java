package com.nickson.kata.winside.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.nickson.kata.winside.model.PointOfInterest;

@Repository
public class TsvReaderPoiDao implements ReaderDao{
	
	Logger logger = LoggerFactory.getLogger(TsvReaderPoiDao.class);
	
	private static final String TSV_SEPARATOR = " ";
	
	@Value("${properties.path}")
	String filePath;

	public String getFilePath() {
		return this.filePath;
	}
	
	public String setFilePath(String filePath) {
		return this.filePath = filePath;
	}
	
	@Override
	public List<Object> read() throws IOException{
		if (filePath == null) {
			throw new IOException("File path is null");
		}
		Path path = Paths.get(filePath);
		try (Stream<String> input = Files.lines(path))   {
			return input.map(line -> line.split(TSV_SEPARATOR))
						.skip(1)
						.map(line -> new PointOfInterest(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2])))
						.collect(Collectors.toList());
		} catch (IOException e) {
			logger.error("Error during reading file {}", path);
			throw e;
		}
	}

}
