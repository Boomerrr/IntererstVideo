package com.example.think.videodemo.Bean;

import java.util.List;

public class RankBean {

    private List<DetailRank> itemList;

    public List<DetailRank> getItemList() {
        return itemList;
    }

    public void setItemList(List<DetailRank> itemList) {
        this.itemList = itemList;
    }

    public class DetailRank{

        private RankData data;

        public RankData getDate() {
            return data;
        }

        public void setDate(RankData data) {
            this.data = data;
        }

        public class RankData{

            private String title;

            private String description;

            private String playUrl;

            private String category;

            private Cover cover;

            private Author author;

            public Author getAuthor() {
                return author;
            }

            public void setAuthor(Author author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public Cover getCover() {
                return cover;
            }

            public void setCover(Cover cover) {
                this.cover = cover;
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

                public void setFeed(String fees) {
                    this.feed = fees;
                }
            }

            public class Author{
                private String icon;

                private String name;

                private String description;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

        }


    }

}
