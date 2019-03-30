package com.example.think.videodemo.Bean;

import java.util.List;

public class MainVideoBean {

    private List<DetailVideo> itemList;

    public List<DetailVideo> getItemList() {
        return itemList;
    }

    public void setItemList(List<DetailVideo> itemList) {
        this.itemList = itemList;
    }

    public class DetailVideo{

        private VideoBean data;

        public VideoBean getData() {
            return data;
        }

        public void setData(VideoBean data) {
            this.data = data;
        }

        public class VideoBean{

            private String description;

            private String title;

            private String category;

            private String playUrl;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            private Author author;

            private Cover cover;

            public Author getAuthor() {
                return author;
            }

            public void setAuthor(Author author) {
                this.author = author;
            }

            public Cover getCover() {
                return cover;
            }

            public void setCover(Cover cover) {
                this.cover = cover;
            }

            public class Author{
               private String icon;

               private String description;

               private String name;

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public class Cover{

                private String feed;

                private String blurred;

                public String getBlurred() {
                    return blurred;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public String getFeed() {
                    return feed;
                }

                public void setFeed(String feed) {
                    this.feed = feed;
                }
            }
        }

    }

}
