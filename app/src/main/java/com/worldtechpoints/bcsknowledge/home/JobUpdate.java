package com.worldtechpoints.bcsknowledge.home;

public class JobUpdate {


    String mPostTime;
    String mJobTitle;
    String mJobBoardDescription;
    String mJobShortDescription;
    String mJobWebsiteLink;
    String mJobInfoImageUrl;

    public JobUpdate() {
    }

    public JobUpdate(String mPostTime, String mJobTitle, String mJobBoardDescription, String mJobShortDescription, String mJobWebsiteLink, String mJobInfoImageUrl) {
        this.mPostTime = mPostTime;
        this.mJobTitle = mJobTitle;
        this.mJobBoardDescription = mJobBoardDescription;
        this.mJobShortDescription = mJobShortDescription;
        this.mJobWebsiteLink = mJobWebsiteLink;
        this.mJobInfoImageUrl = mJobInfoImageUrl;
    }

    public String getmPostTime() {
        return mPostTime;
    }

    public void setmPostTime(String mPostTime) {
        this.mPostTime = mPostTime;
    }

    public String getmJobTitle() {
        return mJobTitle;
    }

    public void setmJobTitle(String mJobTitle) {
        this.mJobTitle = mJobTitle;
    }

    public String getmJobBoardDescription() {
        return mJobBoardDescription;
    }

    public void setmJobBoardDescription(String mJobBoardDescription) {
        this.mJobBoardDescription = mJobBoardDescription;
    }

    public String getmJobShortDescription() {
        return mJobShortDescription;
    }

    public void setmJobShortDescription(String mJobShortDescription) {
        this.mJobShortDescription = mJobShortDescription;
    }

    public String getmJobWebsiteLink() {
        return mJobWebsiteLink;
    }

    public void setmJobWebsiteLink(String mJobWebsiteLink) {
        this.mJobWebsiteLink = mJobWebsiteLink;
    }

    public String getmJobInfoImageUrl() {
        return mJobInfoImageUrl;
    }

    public void setmJobInfoImageUrl(String mJobInfoImageUrl) {
        this.mJobInfoImageUrl = mJobInfoImageUrl;
    }
}
