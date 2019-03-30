package com.example.think.videodemo.Bean;

public class LocationBean {
    private  LocationABean locationABean;

    public LocationABean getLocationABean() {
        return locationABean;
    }

    public void setLocationABean(LocationABean locationABean) {
        this.locationABean = locationABean;
    }

    public class LocationABean{
        private content c;

        public content getC() {
            return c;
        }

        public void setC(content c) {
            this.c = c;
        }

       public  class content{
            private String address;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
