package com.example.demo.soutu;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SauSearch {
//    private char index_hmags = '0';
//    private char index_reserved = '0';
//    private char index_hcg = '0';
//    private char index_ddbobjects = '0';
//    private char index_ddbsamples = '0';
//    private char index_pixiv = '1';
//    private char index_pixivhistorical = '1';
//    private char index_seigaillust = '1';
//    private char index_danbooru = '0';
//    private char index_drawr = '1';
//    private char index_nijie = '1';
//    private char index_yandere = '0';
//    private char index_animeop = '0';
//    private char index_shutterstock = '0';
//    private char index_fakku = '0';
//    private char index_hmisc = '0';
//    private char index_2dmarket = '0';
//    private char index_medibang = '0';
//    private char index_anime = '0';
//    private char index_hanime = '0';
//    private char index_movies = '0';
//    private char index_shows = '0';
//    private char index_gelbooru = '0';
//    private char index_konachan = '0';
//    private char index_sankaku = '0';
//    private char index_animepictures = '0';
//    private char index_e621 = '0';
//    private char index_idolcomplex = '0';
//    private char index_bcyillust = '0';
//    private char index_bcycosplay = '0';
//    private char index_portalgraphics = '0';
//    private char index_da = '1';
//    private char index_pawoo = '0';
//    private char index_madokami = '0';
//    private char index_mangadex = '0';
    //    private String minsim = "80!";
    private String sauApi;
//    private long db_bitmask = getDb_bitmask();
    ArrayList<SoutuRes> ls = new ArrayList<>();

    public SauSearch(String sauApi, String imgurl) throws IOException {
        this.sauApi = sauApi;
        imgurl = URLEncoder.encode(imgurl, "UTF-8");
//        String url = "https://saucenao.com/search.php?output_type=2&numres=10"/*&minsim=" + minsim */ + "&dbmask=" + db_bitmask + "&api_key=" + sauApi + "&url=" + imgurl;
        String url = "https://saucenao.com/search.php?output_type=2&numres=16" + "&api_key=" + sauApi + "&url=" + imgurl;
        System.out.println(url);
        HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
        con.connect();
        InputStream in = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        isr.close();
        str = sb.toString();
//        System.out.println(str);
        JsonObject jsonObject = (new JsonParser()).parse(str).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("results").getAsJsonArray();
        for (JsonElement json : jsonArray) {
            SoutuRes soutuRes = (new Gson()).fromJson(json, SoutuRes.class);
            Double simi = Double.parseDouble(soutuRes.getHeader().getSimilarity());
            if (simi > 80) {
                ls.add(soutuRes);
//                System.out.println(soutuRes.getHeader().getThumbnail());
            }
        }
    }

    public ArrayList<SoutuRes> getLs() {
        return ls;
    }
//    public String getMinsim() {
//        return minsim;
//    }
//
//    public void setMinsim(String minsim) {
//        this.minsim = minsim;
//    }



    public String getSauApi() {
        return sauApi;
    }

    public void setSauApi(String sauApi) {
        this.sauApi = sauApi;
    }

//    public long getDb_bitmask() {
//        return Long.parseLong("" + index_mangadex + index_madokami + index_pawoo + index_da + index_portalgraphics + index_bcycosplay + index_bcyillust + index_idolcomplex + index_e621 + index_animepictures + index_sankaku + index_konachan + index_gelbooru + index_shows + index_movies + index_hanime + index_anime + index_medibang + index_2dmarket + index_hmisc + index_fakku + index_shutterstock + index_reserved + index_animeop + index_yandere + index_nijie + index_drawr + index_danbooru + index_seigaillust + index_anime + index_pixivhistorical + index_pixiv + index_ddbsamples + index_ddbobjects + index_hcg + index_hanime + index_hmags, 2);
//    }

}
