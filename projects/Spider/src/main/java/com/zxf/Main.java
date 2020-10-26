package com.zxf;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("https://ext.se.360.cn/webstore/category/").get();
        Elements apps = document.select(".appwrap");
        apps.forEach((app) -> {
            String img = app.selectFirst("img").attr("src");
            String name = app.selectFirst("h3").text();
            String intro = app.selectFirst(".intro").text();
            System.out.println(name + "_" + intro + "_" + img);

            try {
                HttpURLConnection connection = (HttpURLConnection)new URL(img).openConnection();
                connection.setRequestMethod("GET");
                try (InputStream is = connection.getInputStream();
                     FileOutputStream fos = new FileOutputStream("../images/" + name + ".jpg")){

                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = is.read(buffer)) != -1){
                        fos.write(buffer, 0, len);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}
