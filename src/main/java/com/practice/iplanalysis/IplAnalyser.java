package com.practice.iplanalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IplAnalyser {
	public Path iplPath;

	public IplAnalyser(Path iplPath) {

		this.iplPath = iplPath;
	}

	public ArrayList<IplRunsCSV> cSVDataLoaderBatting() throws IplAnalyserException {

		ArrayList<IplRunsCSV> list = new ArrayList<>();

		Reader reader = null;
		try {
			reader = Files.newBufferedReader(iplPath);
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

	public ArrayList<IplBowling> cSVDataLoaderBowling() throws IplAnalyserException {

		ArrayList<IplBowling> list = new ArrayList<>();

		Reader reader = null;
		try {
			reader = Files.newBufferedReader(iplPath);
			CsvToBean<IplBowling> csvToBeanBuilder = new CsvToBeanBuilder<IplBowling>(reader).withType(IplBowling.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			Iterator<IplBowling> iplBattingitr = csvToBeanBuilder.iterator();
			while (iplBattingitr.hasNext()) {

				IplBowling csvReader = iplBattingitr.next();
				list.add(csvReader);
			}
			return list;
		} catch (IOException E1) {
			throw new IplAnalyserException("Wrong Path Provided", IplAnalyserException.ExceptionType.WRONG_PATH);
		}

	}

	public double calculateBattingAvg() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		double maxAvgScore = list.stream().filter(x -> !x.average.equals("-")).map(x -> Double.parseDouble(x.average))
				.max(Double::compare).get();
		ArrayList<IplRunsCSV> maxAvgList = (ArrayList<IplRunsCSV>) list.stream()
				.filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
		for (IplRunsCSV row : maxAvgList)
			System.out.println("player with maximum average:\n" + row.player);
		return maxAvgScore;
	}

	public double maximumStrikeRates() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		double maxStrikeRate = list.stream().map(x -> (x.strikeRate)).max(Double::compare).get();
		ArrayList<IplRunsCSV> maxStrikeRateList = (ArrayList<IplRunsCSV>) list.stream()
				.filter(x -> Double.toString(x.strikeRate).equals(Double.toString(maxStrikeRate)))
				.collect(Collectors.toList());
		System.out.println("Player with maximum strike rate: ");
		for (IplRunsCSV row : maxStrikeRateList)
			System.out.println(row.player);
		return maxStrikeRate;
	}

	public String cricketerWithMax6() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		ArrayList<IplRunsCSV> sortedMax6 = (ArrayList<IplRunsCSV>) list.stream()
				.sorted((player1, player2) -> Integer.compare(player1.sixes, player2.sixes))
				.collect(Collectors.toList());
		Collections.reverse(sortedMax6);
		System.out.println("Player with maximum sixes is");
		System.out.println(sortedMax6.get(0).player + " with total number of sixes " + sortedMax6.get(0).sixes);
		return sortedMax6.get(0).player;
	}

	public String cricketerWithMax4() throws IplAnalyserException {
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		ArrayList<IplRunsCSV> sortedMax4 = (ArrayList<IplRunsCSV>) list.stream().sorted((player1, player2) -> {
			return player2.fours - player1.fours;
		}).collect(Collectors.toList());
		System.out.println("Players with maximum number of 4s is");
		System.out.println(sortedMax4.get(0).player + " with total number of fours " + sortedMax4.get(0).fours);
		return sortedMax4.get(0).player;

	}

	public String bestSRWith4s6s() throws IplAnalyserException {
		// TODO Auto-generated method stub
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		ArrayList<IplRunsCSV> sortedSR = (ArrayList<IplRunsCSV>) list.stream()
				.sorted(Comparator.comparing(IplRunsCSV::getStrikeRate).reversed()).collect(Collectors.toList());
		ArrayList<IplRunsCSV> result = (ArrayList<IplRunsCSV>) sortedSR
				.stream().sorted(Comparator
						.comparing(b -> ((IplRunsCSV) b).getSixes() * 6 + ((IplRunsCSV) b).getFours() * 4).reversed())
				.collect(Collectors.toList());
		return result.get(0).player;
	}

	public String playerWithBestSRAndAvg() throws IplAnalyserException {
		// TODO Auto-generated method stub
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		ArrayList<IplRunsCSV> sortedSR = (ArrayList<IplRunsCSV>) list.stream()
				.sorted(Comparator.comparing(IplRunsCSV::getStrikeRate).reversed()).collect(Collectors.toList());

		ArrayList<IplRunsCSV> sortedSRAndAvg = (ArrayList<IplRunsCSV>) sortedSR.stream()
				.sorted(Comparator.comparing(x -> Double.parseDouble(((IplRunsCSV) x).getAverage())).reversed())
				.collect(Collectors.toList());
		return sortedSRAndAvg.get(0).player;
	}

	public String playerWithBestAvgAndMostRuns() throws IplAnalyserException {
		// TODO Auto-generated method stub
		ArrayList<IplRunsCSV> list = cSVDataLoaderBatting();
		ArrayList<IplRunsCSV> sortedAvg = (ArrayList<IplRunsCSV>) list.stream()
				.sorted(Comparator.comparing(x -> Double.parseDouble(((IplRunsCSV) x).getAverage())).reversed())
				.collect(Collectors.toList());
		ArrayList<IplRunsCSV> sortedRunsAndAvg = (ArrayList<IplRunsCSV>) sortedAvg.stream()
				.sorted(Comparator.comparing(IplRunsCSV::getRuns).reversed()).collect(Collectors.toList());
		System.out.println(sortedRunsAndAvg.get(0).player);
		return sortedRunsAndAvg.get(0).player;
	}

	public String playerWithTopBowlingAvg() throws IplAnalyserException {
		// TODO Auto-generated method stub
		ArrayList<IplBowling> list = cSVDataLoaderBowling();
		ArrayList<IplBowling> sortedAvg = (ArrayList<IplBowling>) list.stream()
				.sorted(Comparator.comparing(x -> Double.parseDouble(((IplBowling) x).getAverage())))
				.collect(Collectors.toList());
		return sortedAvg.get(0).getPlayer();
	}

	public String playerWithTopBowlingSR() throws IplAnalyserException{
		// TODO Auto-generated method stub
		ArrayList<IplBowling> list = cSVDataLoaderBowling();
		ArrayList<IplBowling> sortedSR = (ArrayList<IplBowling>) list.stream()
				.sorted(Comparator.comparing(x -> Double.parseDouble(((IplBowling) x).getStrikeRate())))
				.collect(Collectors.toList());
		System.out.println("ddddd"+sortedSR.get(0).getPlayer());
		return sortedSR.get(0).getPlayer();
	}
}
