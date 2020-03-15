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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataDownloader {
	
	int status = 0 ;
	String line = "";
	URL urlData;
	BufferedReader reader; 
	StringBuffer nationalData;
	StringBuffer regionalData;
	//StringBuffer content =  new StringBuffer();

	
	DataDownloader ()
	{
		
		nationalData=this.dataDownload("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-andamento-nazionale.json");
		regionalData=this.dataDownload("https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-json/dpc-covid19-ita-regioni.json");		
		
	}
	
	public StringBuffer dataDownload( String url )
	{
		StringBuffer content = new StringBuffer ();
		
		try {
			urlData = new URL (url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection connection = (HttpURLConnection) urlData.openConnection();
			connection.setInstanceFollowRedirects(true);
			status = connection.getResponseCode();
			if (status==200)
			{
				int i = 0 ;
				reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));

				while ((line=reader.readLine()) != null)
				{
					if (line.startsWith("ï»¿"))
						line=line.substring(3);
					content.append(line);
					content.append("\n");
				}
			//	System.out.println(content);

					
			}
			else
			{
				//LOCAL RETRIEVE
			}
			connection.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
	
	public StringBuffer getNationalData ()
	{
		return nationalData;
	}
	
	public StringBuffer getRegionalData()
	{
		return regionalData;
	}
	
		

}
