package clsses;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;

public class WikiSearcher {

    private final String staticUrl;
    private final String query;

    public WikiSearcher(String staticUrl, String query) {
        this.staticUrl = staticUrl;
        this.query = query;
    }

    public String getHTML() {
        String json = getJSON();
        ArrayList<ArrayList<String>> jsonObject = parseJSON(json);
        String resultHtml = new String();
        for (int i = 0; i < jsonObject.get(0).size(); i++) {
            resultHtml += "<a target='_blank' href='" + jsonObject.get(2).get(i) + "'>";
            resultHtml += "<div class='block well'>";
            resultHtml += "<span class='title'>" + jsonObject.get(0).get(i) + "</span><br>" + jsonObject.get(1).get(i);
            resultHtml += "<br>";
            resultHtml += "</div>";
            resultHtml += "</a>";
        }
        return resultHtml;
    }

    private String getJSON() {
        String json = new String();
        try {
            URL url = new URL(staticUrl + query);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            String finalUrl = uri.toASCIIString();
            url = new URL(finalUrl);
            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                json += scan.nextLine();
            }
            scan.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WikiSearcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(WikiSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }   
    
    private ArrayList<ArrayList<String>> parseJSON(String json) {
        ArrayList<ArrayList<String>> jsonArrayList = new ArrayList<ArrayList<String>>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 1; i < jsonArray.length(); i++) {
                JSONArray subJsonArray = jsonArray.getJSONArray(i);
                ArrayList<String> subArrayList = new ArrayList<String>();
                for (int j = 0; j < subJsonArray.length(); j++) {
                    subArrayList.add(subJsonArray.getString(j));
                }
                jsonArrayList.add(subArrayList);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(WikiSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonArrayList;
    }
}
