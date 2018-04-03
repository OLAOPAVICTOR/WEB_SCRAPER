/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web_indexer;

import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.IntStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author OLAOPA
 */
public class WEB_SCRAPER {
    
    static class Spider{
        List<String> uncrawled_links = new ArrayList<String>();
        List<String> crawled_links = new ArrayList<String>();

        
        
        public void indexFrom(String link) throws IOException{
            Document doc = Jsoup.connect(link).get();
            
            if(doc.select("a") != null){
               Elements links = doc.select("a");
               for(int i=0; i<links.size(); i++){
                   String seed = links.get(i).attr("href").toString();
                   
                   if(! crawled_links.contains(seed)){
                       uncrawled_links.add(seed);
                       out.println(i +" "+  seed);
                   }
                   
               }
               
               crawlNext();
               
            }else{
                crawlNext();
            }
            
        }

        private void crawlNext() throws IOException {
            crawled_links.add(uncrawled_links.get(0));
            indexFrom(uncrawled_links.remove(0));
        }
    }
    
    public static void main(String[] args) throws IOException {        
        
    }
    
}
