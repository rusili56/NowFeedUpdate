package com.example.nowfeed.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rusili on 11/7/16.
 */

public class Instagram {

    private Pagination pagination;
    private Meta meta;
    private List<Datum> data = new ArrayList<Datum>();

    public class Pagination {
    }

    public class Meta {

        private Integer code;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

    }


    public class Datum {

        private Object attribution;
        private List<Object> tags = new ArrayList<Object>();
        private String type;
        private Object location;
        private Comments comments;
        private String filter;
        private String createdTime;
        private String link;
        private Likes likes;
        private Images images;
        private List<Object> usersInPhoto = new ArrayList<Object>();
        private Object caption;
        private Boolean userHasLiked;
        private String id;
        private User user;

        public class User {

            private String username;
            private String profilePicture;
            private String id;
            private String fullName;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getProfilePicture() {
                return profilePicture;
            }

            public void setProfilePicture(String profilePicture) {
                this.profilePicture = profilePicture;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

        }

        public class Comments {

            private Integer count;

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

        }

        public class Likes {

            private Integer count;

            public Integer getCount() {
                return count;
            }

            public void setCount(Integer count) {
                this.count = count;
            }

        }

        public class Images {

            private low_resolution low_resolution;
            private Thumbnail thumbnail;
            private standard_resolution standard_resolution;

            public class low_resolution {

                private String url;
                private Integer width;
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

            public class standard_resolution {

                private String url;
                private Integer width;
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

            public class Thumbnail {

                private String url;
                private Integer width;
                private Integer height;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public Integer getWidth() {
                    return width;
                }

                public void setWidth(Integer width) {
                    this.width = width;
                }

                public Integer getHeight() {
                    return height;
                }

                public void setHeight(Integer height) {
                    this.height = height;
                }

            }

            public low_resolution getlow_resolution() {
                return low_resolution;
            }

            public void setlow_resolution(low_resolution low_resolution) {
                this.low_resolution = low_resolution;
            }

            public Thumbnail getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Thumbnail thumbnail) {
                this.thumbnail = thumbnail;
            }

            public standard_resolution getstandard_resolution() {
                return standard_resolution;
            }

            public void setstandard_resolution(standard_resolution standard_resolution) {
                this.standard_resolution = standard_resolution;
            }

        }

        public Object getAttribution() {
            return attribution;
        }

        public void setAttribution(Object attribution) {
            this.attribution = attribution;
        }

        public List<Object> getTags() {
            return tags;
        }

        public void setTags(List<Object> tags) {
            this.tags = tags;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Comments getComments() {
            return comments;
        }

        public void setComments(Comments comments) {
            this.comments = comments;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Likes getLikes() {
            return likes;
        }

        public void setLikes(Likes likes) {
            this.likes = likes;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public List<Object> getUsersInPhoto() {
            return usersInPhoto;
        }

        public void setUsersInPhoto(List<Object> usersInPhoto) {
            this.usersInPhoto = usersInPhoto;
        }

        public Object getCaption() {
            return caption;
        }

        public void setCaption(Object caption) {
            this.caption = caption;
        }

        public Boolean getUserHasLiked() {
            return userHasLiked;
        }

        public void setUserHasLiked(Boolean userHasLiked) {
            this.userHasLiked = userHasLiked;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
