package com.arbiterli.map.collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.arbiterli.map.model.Chain;
import com.arbiterli.map.model.Station;

public class CollectDataTask {
	private Map<String, List<String>> chains = new HashMap<String, List<String>>();
	public void execute() {
		System.out.println("done");
	}
	
	private void requestForData() throws Exception {
		Document doc = Jsoup.connect("http://bus.hangzhou.com.cn/all_line.php").get();
		Elements es = doc.select("li a");
		for(int i=8;i<es.size();++i) {
			Thread.sleep(500);
			Element e = es.get(i);
			String chainName = e.text();
			Document doc_ = Jsoup.connect("http://bus.hangzhou.com.cn/" + e.attr("href")).get();
			Elements es_ = doc_.select("td:eq(1)");
			List<String> stations = new ArrayList<String>();
			for(Element e_ : es_) {
				if(e_.attr("id").endsWith("1")) {
					stations.add(e_.text());
				}
			}
			chains.put(chainName, stations);
			//System.out.println(e);
		}
	}
	
	private void saveToDb() {
		
	}
}
