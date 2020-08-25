package com.suncode.instamediadownloader.model;

import com.google.gson.annotations.SerializedName;

public class Graphql {

    public shortcode_media shortcode_media;

    public Graphql.shortcode_media getShortcode_media() {
        return shortcode_media;
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
