package com.example.think.videodemo.Bean;

import java.util.List;

public class HotVideoBean {
    private List<HotABean> issueList;
    private String nextPageUrl;

    public List<HotABean> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<HotABean> issueList) {
        this.issueList = issueList;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public class HotABean{
        private List<itemBean> itemList;

        public List<itemBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<itemBean> itemList) {
            this.itemList = itemList;
        }

        public class itemBean{
            private ClearInfo data;

            public ClearInfo getData() {
                return data;
            }

            public void setData(ClearInfo data) {
                this.data = data;
            }

            public class ClearInfo{
                private String title;
                private String description;
                private String playUrl;
                private AuthInfo author;
                private CoverInfo cover;
                private String category;

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public CoverInfo getCover() {
                    return cover;
                }

                public void setCover(CoverInfo cover) {
                    this.cover = cover;
                }

                public AuthInfo getAuthor() {
                    return author;
                }

                public void setAuthor(AuthInfo author) {
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

                public class AuthInfo{
                    private String icon;
                    private String name;
                    private String description;

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

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
                }

                public class CoverInfo{
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
}
