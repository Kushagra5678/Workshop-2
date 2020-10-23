package com.practice.iplanalyser;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.practice.iplanalysis.IplAnalyser;
import com.practice.iplanalysis.IplAnalyserException;

public class IplAnalyserTest {
	
	public static final String FILE_PATH_RUNS = "E:\\Capg_training\\eclipse_work\\IplAnalysis\\MostRuns.csv";
	public static final String FILE_PATH_WICKETS = "E:\\Capg_training\\eclipse_work\\IplAnalysis\\MostWickets.csv";
	
	@Test
	public void givenIPLFile_FindMaxAvgTest() throws IplAnalyserException {
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		double maxAvg = iplAnalyser.calculateBattingAvg();
		System.out.println(maxAvg);
		Assert.assertEquals(83.2, maxAvg, 0.0);
	}
	
	@Test
	public void givenIPLFile_FindMaxStrikeRate() throws IplAnalyserException {
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		double maximumStrikeRate = iplAnalyser.maximumStrikeRates();
		System.out.println(maximumStrikeRate);
		Assert.assertEquals(333.33, maximumStrikeRate, 0.0);
	}
	
	@Test
	public void playerWithMaximum6sTest() throws IplAnalyserException {
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String playerWithMax6 = iplAnalyser.cricketerWithMax6();
		Assert.assertEquals("Andre Russell", playerWithMax6);
	}

	@Test
	public void playerWithMaximum4sTest() throws IplAnalyserException {
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String playerWithMax4 = iplAnalyser.cricketerWithMax4();
		Assert.assertEquals("Shikhar Dhawan", playerWithMax4);
	}
	
	@Test
	public void playerWithBestSRWith4s6sTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.bestSRWith4s6s();
		Assert.assertEquals("Andre Russell", player);
	}
	
	@Test
	public void playerWithBestSRAndAverageTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.playerWithBestSRAndAvg();
		Assert.assertEquals("MS Dhoni", player);
	}
	
	@Test
	public void playerWithHighestRunsAndAverageTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_RUNS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.playerWithBestAvgAndMostRuns();
		Assert.assertEquals("David Warner", player);
	}
	
	@Test
	public void playerWithTopBowlingAvgTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_WICKETS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.playerWithTopBowlingAvg();
		Assert.assertEquals("Anukul Roy", player);
	}
	
	@Test
	public void playerWithTopBowlingSRTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_WICKETS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.playerWithTopBowlingSR();
		Assert.assertEquals("Alzarri Joseph", player);
	}
	
	@Test
	public void playerWithBestEconomyTest() throws IplAnalyserException{
		Path pathname = Paths.get(FILE_PATH_WICKETS);
		IplAnalyser iplAnalyser = new IplAnalyser(pathname);
		String player = iplAnalyser.playerWithBestEconomy();
		Assert.assertEquals("Shivam Dube", player);
	}
}
