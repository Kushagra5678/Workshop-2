package com.practice.iplanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplAnalyser {
	public Path iplMostRunPath;

	public IplAnalyser(Path iplMostRunPath) {

		this.iplMostRunPath = iplMostRunPath;
	}

	public ArrayList<IplRunsCSV> cSVDataLoader() throws IplAnalyserException {

		ArrayList<IplRunsCSV> list = new ArrayList<>();

		Reader reader = null;
		try {
			reader = Files.newBufferedReader(iplMostRunPath);
			CsvToBean<IplRunsCSV> csvToBeanBuilder = new CsvToBeanBuilder<IplRunsCSV>(reader).withType(IplRunsCSV.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			Iterator<IplRunsCSV> iplBattingitr = csvToBeanBuilder.iterator();
			while (iplBattingitr.hasNext()) {

				IplRunsCSV csvReader = iplBattingitr.next();
				list.add(csvReader);
			}
			return list;
		} catch (IOException E1) {
			throw new IplAnalyserException("Wrong Path Provided", IplAnalyserException.ExceptionType.WRONG_PATH);
		}

	}

	public double calculateBattingAvg() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoader();
		double maxAvgScore = list.stream().filter(x -> !x.average.equals("-"))
							 .map(x -> Double.parseDouble(x.average))
							 .max(Double::compare).get();
		ArrayList<IplRunsCSV> maxAvgList = (ArrayList<IplRunsCSV>) list.stream()
										   .filter(x -> x.average.equals(Double.toString(maxAvgScore)))
										   .collect(Collectors.toList());
		for (IplRunsCSV row : maxAvgList)
			System.out.println("player with maximum average:\n"+row.player);
		return maxAvgScore;
	}

	public double maximumStrikeRates() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoader();
		double maxStrikeRate = list.stream()
								 .map(x -> Double.parseDouble(x.strikeRate))
								 .max(Double::compare).get();
		ArrayList<IplRunsCSV> maxStrikeRateList = (ArrayList<IplRunsCSV>) list.stream()
												  .filter(x -> x.strikeRate.equals(Double.toString(maxStrikeRate)))
												  .collect(Collectors.toList());
		System.out.println("Player with maximum strike rate: ");
		for (IplRunsCSV row : maxStrikeRateList)
			System.out.println(row.player);
		return maxStrikeRate;
	}
}
