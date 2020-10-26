package com.practice.iplanalysis;

import com.opencsv.bean.CsvBindByName;

public class IplBowling {
	@CsvBindByName(column = "POS", required = true)
	private int position;
	@CsvBindByName(column = "PLAYER", required = true)
	private String player;
	@CsvBindByName(column = "MAT", required = true)
	private int match;
	@CsvBindByName(column = "Inns", required = true)
	private int innings;
	@CsvBindByName(column = "Ov", required = true)
	private double overs;
	@CsvBindByName(column = "Runs", required = true)
	private int runs;
	@CsvBindByName(column = "Wkts", required = true)
	private int wickets;
	@CsvBindByName(column = "BBI", required = true)
	private int bbi;
	@CsvBindByName(column = "Avg", required = true)
	private String average;
	@CsvBindByName(column = "Econ", required = true)
	private double economy;
	@CsvBindByName(column = "SR", required = true)
	private String strikeRate;
	@CsvBindByName(column = "4w", required = true)
	private int fourWickets;
	@CsvBindByName(column = "5w", required = true)
	private int fiveWickets;

	public int getPosition() {
		return position;
	}

	public String getPlayer() {
		return player;
	}

	public int getMatch() {
		return match;
	}

	public int getInnings() {
		return innings;
	}

	public double getOvers() {
		return overs;
	}

	public int getRuns() {
		return runs;
	}

	public int getWickets() {
		return wickets;
	}

	public int getBbi() {
		return bbi;
	}

	public String getAverage() {
		try {
			Double.parseDouble(average);
		} catch (NumberFormatException e) {
			return "10000";
		}
		return average;
	}

	public double getEconomy() {
		return economy;
	}

	public String getStrikeRate() {
		try {
			Double.parseDouble(strikeRate);
		} catch (NumberFormatException e) {
			return "1000";
		}
		return strikeRate;
	}

	public int getFourWickets() {
		return fourWickets;
	}

	public int getFiveWickets() {
		return fiveWickets;
	}

}
