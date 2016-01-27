package com.arbiterli.map.model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private String name;
	private String city;
	// a string of a list of station id near to this station, seperated by ','
	private String nearStations;
	private float x;
	private float y;
	private List<Chain> chains = new ArrayList<Chain>();

	public Station(String name) {
		this.name = name;
	}

	public Station(String name, float x, float y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public Station(String name, float x, float y, List<Chain> chains) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.chains = chains;
	}

	public String showChains() {
		StringBuilder sb = new StringBuilder();
		for (Chain c : chains) {
			sb.append(c.getName() + ",");
		}
		return sb.toString();
	}

	public void addChain(Chain chain) {
		this.chains.add(chain);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Station other = (Station) o;
		if (name != null ? !name.equals(other.name) : other.name != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash = (name != null ? name.hashCode() : 0);
		return hash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public List<Chain> getChains() {
		return chains;
	}

	public void setChains(List<Chain> chains) {
		this.chains = chains;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNearStations() {
		return nearStations;
	}

	public void setNearStations(String nearStations) {
		this.nearStations = nearStations;
	}
}
