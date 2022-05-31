package com.yike.apis.utils.glassnode;

public enum Glassnode {
    price("https://api.glassnode.com/v1/metrics/market/price_usd_close?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5Mzg1NnxZSHljQXdHRnUyb2EzSXhhQ1h5NnFjaUpQb1AzV2VpSUJibDN6VWdCTWw1bkdwWS14RHJrbDJXbi1CQW1JZmM9fLDwGHlrGbIvq0R-7FBayz-aL9x0NVHP_i7sWP_v8hp3"),
    addresses_active_addresses_number("https://api.glassnode.com/v1/metrics/addresses/active_count?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    distribution_with_non_zero_balance_number("https://api.glassnode.com/v1/metrics/addresses/min_point_zero_1_count?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    exchanges_exchanges_balance_number("https://api.glassnode.com/v1/metrics/distribution/balance_exchanges?a=%s&c=native&e=aggregated&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    fees_fees_total_number("https://api.glassnode.com/v1/metrics/fees/volume_sum?a=%s&c=native&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    lifespan_coin_days_destroyed_cdd_number("https://api.glassnode.com/v1/metrics/indicators/cdd?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    lightning_lightning_newwork_capacity_number("https://api.glassnode.com/v1/metrics/lightning/network_capacity_sum?a=%s&c=native&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    market_indicators_reserve_risk_number("https://api.glassnode.com/v1/metrics/indicators/reserve_risk?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    miners_block_height_number("https://api.glassnode.com/v1/metrics/blockchain/block_height?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    ratios_puell_multiple_number("https://api.glassnode.com/v1/metrics/indicators/puell_multiple?a=%s&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe"),
    transactions_exchanges_inflow_volume_number("https://api.glassnode.com/v1/metrics/transactions/transfers_volume_to_exchanges_sum?a=%s&c=native&e=aggregated&i=24h","_gid=GA1.2.1915675680.1650509768; _ga=GA1.2.1565483971.1650249477; _ga_8MGXPNL6MF=GS1.1.1650509767.1.1.1650510277.0; _s=MTY1MDU5NzE4NnxGSnluZ2FQMEF4ZVNpbzI1Ri13ODZoa21XT1dBbGk5c3BqbGxEcjl0X29vVzJRLW42V2FaYTB5cmtQVkNfTGM9fFxQkSy001-fQg3mhPa9RHcXz36q2T-eaqrFVePlCOHe");


    private String url;
    private String cookie;

    Glassnode(String url,String cookie) {
        this.url = url;
        this.cookie = cookie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
