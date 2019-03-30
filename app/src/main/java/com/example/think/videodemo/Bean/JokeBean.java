package com.example.think.videodemo.Bean;

import java.util.List;

public class JokeBean {

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{

        private List<JokeItem> data;

        public List<JokeItem> getData() {
            return data;
        }

        public void setData(List<JokeItem> data) {
            this.data = data;
        }

        public class JokeItem{

            private String content;

            private String updatetime;

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
        }

    }

}
