/*
 * Copiright 2020 Lorenzo Ganci © 
 * This file is part of SARS-CoV-2 Italian Monitor.

    SARS-CoV-2 Italian Monitor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    SARS-CoV-2 Italian Monitor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with SARS-CoV-2 Italian Monitor.  If not, see <http://www.gnu.org/licenses/>.
 */
public class DailyData {
	String data = "";
	String region = "";
	int regionCode=0;
	int hospitalized=0;
	int intensiveCare=0;
	int totalHospitalized=0;
	int homeIsolated=0;
	int totalPositive=0;
	int newPositive=0;
	int healed=0;
	int dead=0;
	int totalCases=0;
	int swabs=0;
	
	
	
	
	public DailyData(String data, String region, int regionCode, int hospitalized, int intensiveCare,
			int totalHospitalized, int homeIsolated, int totalPositive, int newPositive, int healed, int dead,
			int totalCases, int swabs) {
		super();
		this.data = data;
		this.region = region;
		this.regionCode = regionCode;
		this.hospitalized = hospitalized;
		this.intensiveCare = intensiveCare;
		this.totalHospitalized = totalHospitalized;
		this.homeIsolated = homeIsolated;
		this.totalPositive = totalPositive;
		this.newPositive = newPositive;
		this.healed = healed;
		this.dead = dead;
		this.totalCases = totalCases;
		this.swabs = swabs;
	}
	public DailyData(String data, int hospitalized, int intensiveCare, int totalHospitalized, int homeIsolated,
			int totalPositive, int newPositive, int healed, int dead, int totalCases, int swabs) {
		super();
		this.data = data;
		this.hospitalized = hospitalized;
		this.intensiveCare = intensiveCare;
		this.totalHospitalized = totalHospitalized;
		this.homeIsolated = homeIsolated;
		this.totalPositive = totalPositive;
		this.newPositive = newPositive;
		this.healed = healed;
		this.dead = dead;
		this.totalCases = totalCases;
		this.swabs = swabs;
		this.region = "TotaleItalia";
		this.regionCode=0;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(int regionCode) {
		this.regionCode = regionCode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getHospitalized() {
		return hospitalized;
	}
	public void setHospitalized(int hospitalized) {
		this.hospitalized = hospitalized;
	}
	public int getIntensiveCare() {
		return intensiveCare;
	}
	public void setIntensiveCare(int intensiveCare) {
		this.intensiveCare = intensiveCare;
	}
	public int getTotalHospitalized() {
		return totalHospitalized;
	}
	public void setTotalHospitalized(int totalHospitalized) {
		this.totalHospitalized = totalHospitalized;
	}
	public int getHomeIsolated() {
		return homeIsolated;
	}
	public void setHomeIsolated(int homeIsolated) {
		this.homeIsolated = homeIsolated;
	}
	public int getTotalPositive() {
		return totalPositive;
	}
	public void setTotalPositive(int totalPositive) {
		this.totalPositive = totalPositive;
	}
	public int getNewPositive() {
		return newPositive;
	}
	public void setNewPositive(int newPositive) {
		this.newPositive = newPositive;
	}
	public int getHealed() {
		return healed;
	}
	public void setHealed(int healed) {
		this.healed = healed;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getSwabs() {
		return swabs;
	}
	public void setSwabs(int swabs) {
		this.swabs = swabs;
	}
	
	
	
	

}
