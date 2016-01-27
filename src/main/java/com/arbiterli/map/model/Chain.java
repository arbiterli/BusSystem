package com.arbiterli.map.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chain", catalog = "bussystem")
public class Chain {
	private Map<Station, Integer> stations = new HashMap<Station, Integer>();
	@Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(name = "name", length = 255)
	private String name;
	@Column(name = "startAt")
	private Time startAt;
	@Column(name = "finishAt")
	private Time finishAt;
	@Column(name = "city", length = 255)
	private String city;

	public Chain(String name) {
		this.name = name;
	}

	public String showStations() {
		StringBuilder sb = new StringBuilder();
		for (Station s : stations.keySet()) {
			sb.append(s.getName() + ",");
		}
		return sb.toString();
	}

	public void addStation(Station station, int pos) {
		this.stations.put(station, pos);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Chain other = (Chain) o;
		if (name != null ? !name.equals(other.name) : other.name != null) {
			return false;
		}
		if (startAt != null ? !startAt.equals(other.startAt)
				: other.startAt != null) {
			return false;
		}
		if (finishAt != null ? !finishAt.equals(other.finishAt)
				: other.finishAt != null) {
			return false;
		}
		if (city != null ? !city.equals(other.city) : other.city != null) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash = (name != null ? name.hashCode() : 0);
		hash = (startAt != null ? startAt.hashCode() : 0);
		hash = (finishAt != null ? finishAt.hashCode() : 0);
		hash = (city != null ? city.hashCode() : 0);
		return hash;
	}

	public Map<Station, Integer> getStations() {
		return stations;
	}

	public void setStations(Map<Station, Integer> stations) {
		this.stations = stations;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getStartAt() {
		return startAt;
	}

	public void setStartAt(Time startAt) {
		this.startAt = startAt;
	}

	public Time getFinishAt() {
		return finishAt;
	}

	public void setFinishAt(Time finishAt) {
		this.finishAt = finishAt;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
