package com.suncode.instamediadownloader.model;

import com.google.gson.annotations.SerializedName;

public class Graphql {

    @SerializedName("shortcode_media")
    public shortcode_media shortcodeMedia;

    public shortcode_media getShortcodeMedia() {
        return shortcodeMedia;
    }

    public class shortcode_media {
        @SerializedName("__typename")
        private String typename;

        @SerializedName("display_url")
        private String displayUrl;

        @SerializedName("video_url")
        private String videoUrl;

        public String getTypename() {
            return typename;
        }

        public String getDisplayUrl() {
            return displayUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }
    }
}
