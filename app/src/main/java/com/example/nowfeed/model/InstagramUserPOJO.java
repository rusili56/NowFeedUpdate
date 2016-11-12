package com.example.nowfeed.model;

public class InstagramUserPOJO {

    private Meta meta;
    private Data data;

    /**
     * @return The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * @param meta The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }


    public class Meta {

        private Integer code;

        /**
         * @return The code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * @param code The code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

    }

    public class Data {

        private String username;
        private String bio;
        private String website;
        private String profilePicture;
        private String fullName;
        private Counts counts;
        private String id;

        /**
         * @return The username
         */
        public String getUsername() {
            return username;
        }

        /**
         * @param username The username
         */
        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * @return The bio
         */
        public String getBio() {
            return bio;
        }

        /**
         * @param bio The bio
         */
        public void setBio(String bio) {
            this.bio = bio;
        }

        /**
         * @return The website
         */
        public String getWebsite() {
            return website;
        }

        /**
         * @param website The website
         */
        public void setWebsite(String website) {
            this.website = website;
        }

        /**
         * @return The profilePicture
         */
        public String getProfilePicture() {
            return profilePicture;
        }

        /**
         * @param profilePicture The profile_picture
         */
        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        /**
         * @return The fullName
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * @param fullName The full_name
         */
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        /**
         * @return The counts
         */
        public Counts getCounts() {
            return counts;
        }

        /**
         * @param counts The counts
         */
        public void setCounts(Counts counts) {
            this.counts = counts;
        }

        /**
         * @return The id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(String id) {
            this.id = id;
        }

        public class Counts {

            private Integer media;
            private Integer followedBy;
            private Integer follows;

            /**
             * @return The media
             */
            public Integer getMedia() {
                return media;
            }

            /**
             * @param media The media
             */
            public void setMedia(Integer media) {
                this.media = media;
            }

            /**
             * @return The followedBy
             */
            public Integer getFollowedBy() {
                return followedBy;
            }

            /**
             * @param followedBy The followed_by
             */
            public void setFollowedBy(Integer followedBy) {
                this.followedBy = followedBy;
            }

            /**
             * @return The follows
             */
            public Integer getFollows() {
                return follows;
            }

            /**
             * @param follows The follows
             */
            public void setFollows(Integer follows) {
                this.follows = follows;
            }

        }
    }
}




