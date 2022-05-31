package com.yike.apis.utils.opensea;

public enum OpenseaApi {
    collection("https://api.opensea.io/api/v1/collection/%s/stats","edd44ee08f3a430690f42a190ea94477","application/json");

    private String url;
    private String X_API_KEY;
    private String accept;

    OpenseaApi(String url, String x_API_KEY, String accept) {
        this.url = url;
        X_API_KEY = x_API_KEY;
        this.accept = accept;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getX_API_KEY() {
        return X_API_KEY;
    }

    public void setX_API_KEY(String x_API_KEY) {
        X_API_KEY = x_API_KEY;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
