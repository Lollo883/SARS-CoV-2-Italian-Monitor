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

import java.util.ArrayList;

public class DataFinder {
	
	ArrayList <DailyData> data = new ArrayList <DailyData> ();
	DailyData singleDay ;
	DataParser dataParser;
	static String region="";
	public static void setRegion(String regiond)
	{
		region=regiond;
	}
	public String getRegion()
	{
		return region;
	}
	
	public DataFinder() {
		dataParser=new DataParser();
	}
	
	
	public ArrayList <DailyData> getData()
	{
		data = dataParser.parse();
		return data;
	}
	public DailyData getLastDayInfo()
	{
		data = dataParser.parse();
		return data.get(data.size()-1);
	}
	
	public ArrayList<DailyData> getRegionData(String region)
	{
		return dataParser.parseRegion(region);
	}
	public DailyData getYesterdayInfo() {
		data = dataParser.parse();
		return data.get(data.size()-2);
	}

}
