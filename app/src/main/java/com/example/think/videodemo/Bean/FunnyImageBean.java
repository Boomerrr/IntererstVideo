package com.example.think.videodemo.Bean;

import java.util.List;

public class FunnyImageBean {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{

        private List<FunnyImageItem> data;

        public List<FunnyImageItem> getData() {
            return data;
        }

        public void setData(List<FunnyImageItem> data) {
            this.data = data;
        }

        public class FunnyImageItem{

            private String content;

            private String updatetime;

            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }

}
