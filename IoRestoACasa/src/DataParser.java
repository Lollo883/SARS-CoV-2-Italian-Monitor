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

import org.json.JSONArray;
import org.json.JSONObject;

public class DataParser {
	
	StringBuffer nationalContent;
	StringBuffer regionalContent;

	
	public DataParser ()
	{
		this.nationalContent= (new DataDownloader()).getNationalData();
		this.regionalContent= (new DataDownloader()).getRegionalData();

	}
	
	public ArrayList<DailyData> parse ()
	{		
		JSONArray dailyMirror = new JSONArray(nationalContent.toString());
		ArrayList <DailyData> data = new ArrayList <DailyData> ();
		for (int i=0;i<dailyMirror.length();i++)
		{
			data.add(new DailyData(dailyMirror.getJSONObject(i).getString("data"),
					dailyMirror.getJSONObject(i).getInt("ricoverati_con_sintomi"),
					dailyMirror.getJSONObject(i).getInt("terapia_intensiva"),
					dailyMirror.getJSONObject(i).getInt("totale_ospedalizzati"),
					dailyMirror.getJSONObject(i).getInt("isolamento_domiciliare"),
					dailyMirror.getJSONObject(i).getInt("totale_attualmente_positivi"),
					dailyMirror.getJSONObject(i).getInt("nuovi_attualmente_positivi"),
					dailyMirror.getJSONObject(i).getInt("dimessi_guariti"),
					dailyMirror.getJSONObject(i).getInt("deceduti"),
					dailyMirror.getJSONObject(i).getInt("totale_casi"),
					dailyMirror.getJSONObject(i).getInt("tamponi")));	
		}

		return data;
	}
	
	public ArrayList<DailyData> parseRegion(String region)
	{
		JSONArray dailyMirror = new JSONArray(regionalContent.toString());
		ArrayList <DailyData> data = new ArrayList <DailyData> ();
		for (int i=0;i<dailyMirror.length();i++)
		{
			if (dailyMirror.getJSONObject(i).getString("denominazione_regione").equals(region))
				data.add(new DailyData(dailyMirror.getJSONObject(i).getString("data"),
					dailyMirror.getJSONObject(i).getString("denominazione_regione"),
					dailyMirror.getJSONObject(i).getInt("codice_regione"),
					dailyMirror.getJSONObject(i).getInt("ricoverati_con_sintomi"),
					dailyMirror.getJSONObject(i).getInt("terapia_intensiva"),
					dailyMirror.getJSONObject(i).getInt("totale_ospedalizzati"),
					dailyMirror.getJSONObject(i).getInt("isolamento_domiciliare"),
					dailyMirror.getJSONObject(i).getInt("totale_attualmente_positivi"),
					dailyMirror.getJSONObject(i).getInt("nuovi_attualmente_positivi"),
					dailyMirror.getJSONObject(i).getInt("dimessi_guariti"),
					dailyMirror.getJSONObject(i).getInt("deceduti"),
					dailyMirror.getJSONObject(i).getInt("totale_casi"),
					dailyMirror.getJSONObject(i).getInt("tamponi")));	
					
		}

		return data;
	}
		
}
	
	/*
	public static void main (String [] Argv)
	{
		DataParser dp = new DataParser ();
		dp.parse();
	}
	*/
	
	


