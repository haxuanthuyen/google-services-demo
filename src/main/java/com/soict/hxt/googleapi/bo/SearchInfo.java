package com.soict.hxt.googleapi.bo;

import com.google.gson.annotations.Expose;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class SearchInfo {

    @Expose(serialize = true)
    private String title;
    @Expose(serialize = true)
    private String packageName;
    @Expose(serialize = true)
    private String linkImages;
    @Expose(serialize = false)
    private long versionCode;
    @Expose(serialize = false)
    private long installSize;
    @Expose(serialize = true)
    private String numDownload;
    @Expose(serialize = true)
    private String uploadDate;
    @Expose(serialize = true)
    private String shareUrl;
    @Expose(serialize = true)
    private double starRating;
    @Expose(serialize = false)
    private String developerEmail;
    @Expose(serialize = false)
    private String developerWebsite;

    private int offerType;

    public SearchInfo() {}

    public int getOfferType() {
        return offerType;
    }

    public void setOfferType(int offerType) {
        this.offerType = offerType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLinkImages() {
        return linkImages;
    }

    public void setLinkImages(String linkImages) {
        this.linkImages = linkImages;
    }

    public long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(long versionCode) {
        this.versionCode = versionCode;
    }

    public long getInstallSize() {
        return installSize;
    }

    public void setInstallSize(long installSize) {
        this.installSize = installSize;
    }

    public String getNumDownload() {
        return numDownload;
    }

    public void setNumDownload(String numDownload) {
        this.numDownload = numDownload;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    public String getDeveloperWebsite() {
        return developerWebsite;
    }

    public void setDeveloperWebsite(String developerWebsite) {
        this.developerWebsite = developerWebsite;
    }
}
