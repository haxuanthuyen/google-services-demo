package com.soict.hxt.googleapi.googleplay;

import com.soict.hxt.googleapi.GooglePlay;
import com.soict.hxt.googleapi.bo.SearchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class GPlayBuilder {
    private static final Logger logger = LoggerFactory.getLogger("CrawlerLogs");

    private GooglePlayAPI services;
    private String email;
    private String pass;
    private String androidId;

    private static GPlayBuilder gPlayBuilder;

    private GPlayBuilder() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("conf.properties"));
            this.email = properties.getProperty("email");
            this.pass = properties.getProperty("pass");
            this.androidId = properties.getProperty("android.id");
            services = new GooglePlayAPI(email, pass, androidId);
            services.login();
            logger.info("login successful");
        } catch (Exception e) {
            logger.error("error login ", e);
        }
    }

    public static GPlayBuilder getInstance() {
        if (gPlayBuilder == null) {
            gPlayBuilder = new GPlayBuilder();
        }
        return gPlayBuilder;
    }

    public void getCategory() throws Exception {
        GooglePlay.BrowseResponse browseResponse = services.browse("GAME", null);
        List<GooglePlay.BrowseLink> lst = browseResponse.getCategoryList();
        for (GooglePlay.BrowseLink link : lst) {
            System.out.println(link.getDataUrl());
        }
    }

    public List<SearchInfo> searchApp(String text) throws IOException {
        GooglePlay.SearchResponse searchResponse = services.search(text);
        List<GooglePlay.DocV2> lstSearch = searchResponse.getDocList().get(0).getChildList();
        List<SearchInfo> results = new ArrayList<>();
        for (GooglePlay.DocV2 doc : lstSearch) {
            GooglePlay.AppDetails appDetails = doc.getDetails().getAppDetails();
            SearchInfo info = new SearchInfo();
            info.setTitle(doc.getTitle());
            info.setPackageName(doc.getDocid());
            info.setLinkImages(doc.getImage(0).getImageUrl());
            info.setShareUrl(doc.getShareUrl());
            info.setStarRating(doc.getAggregateRating().getStarRating());
            info.setOfferType(doc.getOffer(0).getOfferType());
            info.setVersionCode(appDetails.getVersionCode());
            info.setInstallSize(appDetails.getInstallationSize());
            info.setNumDownload(appDetails.getNumDownloads());
            info.setUploadDate(appDetails.getUploadDate());
            info.setDeveloperEmail(appDetails.getDeveloperEmail());
            info.setDeveloperWebsite(appDetails.getDeveloperWebsite());
            results.add(info);
        }
        return results;
    }

    public InputStream downloadApkFile(String packageName) throws IOException {
        GooglePlay.DetailsResponse details = services.details(packageName);
        GooglePlay.AppDetails appDetails = details.getDocV2().getDetails().getAppDetails();
        GooglePlay.Offer offer = details.getDocV2().getOffer(0);

        int versionCode = appDetails.getVersionCode();
        long installSize = appDetails.getInstallationSize();
        int offerType = offer.getOfferType();
        boolean checkoutRequired = offer.getCheckoutFlowRequired();

        // paid application...ignore
        if (checkoutRequired) {
            return null;
        }

        InputStream downloadStream = services.download(appDetails.getPackageName(), versionCode, offerType);
        return downloadStream;
//        FileOutputStream outputStream = new FileOutputStream(File.createTempFile(packageName, ".apk"));
//        byte[] buffer = new byte[1024];
//        for (int k = 0; (k = downloadStream.read(buffer)) != -1;) {
//            outputStream.write(buffer, 0, k);
//        }
//        downloadStream.close();
//        outputStream.close();
    }

}
