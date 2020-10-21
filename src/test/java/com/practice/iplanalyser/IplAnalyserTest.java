package com.practice.iplanalyser;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.practice.iplanalysis.IplAnalyser;
import com.practice.iplanalysis.IplAnalyserException;

public class IplAnalyserTest {
	
	public static final String FILE_PATH_RUNS = "E:\\Capg_training\\eclipse_work\\IplAnalysis\\MostRuns.csv";
	
	@Test
	public void givenIPLFile_FindMaxAvgTest() throws IplAnalyserException {
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplLeagueAnalyser = new IplAnalyser(pathname);
		double maxAvg = iplLeagueAnalyser.calculateBattingAvg();
		System.out.println(maxAvg);
		Assert.assertEquals(83.2, maxAvg, 0.0);
	}
}
