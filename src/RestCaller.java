import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestCaller {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json1 = readJsonFromUrl("https://api.phila.gov/airport-parking/v1/");
    JSONObject json2 = readJsonFromUrl("http://apps.tsa.dhs.gov/MyTSAWebService/GetTSOWaitTimes.ashx?ap=PHL&output=json");
    JSONArray garages = json1.getJSONArray("garages");
    JSONArray waitTimes = json2.getJSONArray("WaitTimes");
    WaitTimeParser parser1 = new WaitTimeParser(waitTimes);
    GarageJSONParser parser2 = new GarageJSONParser(garages);
//    WaitTimeCollection col1 = new WaitTimeCollection(parser1);
    GarageCollection col2 = new GarageCollection(parser2);
    DataAnalyzer analyzer = new DataAnalyzer(parser1, parser2);
    System.out.println(analyzer.getTimeAtCheckpoint());
    System.out.println("\n");
//    for (int i = 0; i < col1.getWaitTimes().size(); i++) {
//    	System.out.println(col1.getWaitTimes().get(i).getCheckPoint());
//    	System.out.println(col1.getWaitTimes().get(i).getCreatedTime());
//    	System.out.println(col1.getWaitTimes().get(i).getWait());
//    	System.out.println("\n");
//    }
    System.out.println("Garages' information: \n");
    for (int i = 0; i < col2.getGarages().size(); i++) {
    	System.out.println(col2.getGarages().get(i).getName());
    	System.out.println(col2.getGarages().get(i).getLatitude());
    	System.out.println(col2.getGarages().get(i).getLongitude());
    	System.out.println(col2.getGarages().get(i).getAdaSpaces());
    	System.out.println(col2.getGarages().get(i).getAvailSpaces());
    	System.out.println(col2.getGarages().get(i).getTotalSpaces());
    	System.out.println("\n");
    }
    
  }
}