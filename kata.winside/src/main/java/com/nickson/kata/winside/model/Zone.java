package com.nickson.kata.winside.model;

public class Zone {
	
	Double minLatitude;
	Double minLongitude;
	Double maxLatitude;
	Double maxLongitude;
	
	public Zone(Double minLatitude, Double minLongitude, Double maxLatitude, Double maxLongitude) {
		super();
		this.minLatitude = minLatitude;
		this.minLongitude = minLongitude;
		this.maxLatitude = maxLatitude;
		this.maxLongitude = maxLongitude;
	}

	public Double getMinLatitude() {
		return minLatitude;
	}

	public Double getMinLongitude() {
		return minLongitude;
	}

	public Double getMaxLatitude() {
		return maxLatitude;
	}

	public Double getMaxLongitude() {
		return maxLongitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maxLatitude == null) ? 0 : maxLatitude.hashCode());
		result = prime * result + ((maxLongitude == null) ? 0 : maxLongitude.hashCode());
		result = prime * result + ((minLatitude == null) ? 0 : minLatitude.hashCode());
		result = prime * result + ((minLongitude == null) ? 0 : minLongitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zone other = (Zone) obj;
		if (maxLatitude == null) {
			if (other.maxLatitude != null)
				return false;
		} else if (!maxLatitude.equals(other.maxLatitude))
			return false;
		if (maxLongitude == null) {
			if (other.maxLongitude != null)
				return false;
		} else if (!maxLongitude.equals(other.maxLongitude))
			return false;
		if (minLatitude == null) {
			if (other.minLatitude != null)
				return false;
		} else if (!minLatitude.equals(other.minLatitude))
			return false;
		if (minLongitude == null) {
			if (other.minLongitude != null)
				return false;
		} else if (!minLongitude.equals(other.minLongitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Zone [minLatitude=" + minLatitude + ", minLongitude=" + minLongitude + ", maxLatitude=" + maxLatitude
				+ ", maxLongitude=" + maxLongitude + "]";
	}
	
}
