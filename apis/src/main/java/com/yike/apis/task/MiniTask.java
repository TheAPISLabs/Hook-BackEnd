package com.yike.apis.task;

import com.yike.apis.service.MiniService;
import com.yike.apis.utils.glassnode.Glassnode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Lazy(false)
@Slf4j
@Component
public class MiniTask {
    @Autowired
    private MiniService miniService;

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void setMarketcapitalizationData(){
        log.info("Mini：setMarketcapitalizationData-------------start");
        miniService.setMarketcapitalizationData("defi");
        miniService.setMarketcapitalizationData("metaverse");
        log.info("Mini：setMarketcapitalizationData-------------stop");
    }

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    public void setMarketcapitalizationDataNFT(){
        log.info("Mini：setMarketcapitalizationDataNFT-------------start");
        miniService.setMarketcapitalizationDataNFT();
        log.info("Mini：setMarketcapitalizationDataNFT-------------stop");
    }

    @Async
    @Scheduled(cron = "0 0 9 * * ?")
    public void getGlassnode(){
        log.info("Mini：getGlassnode-------------start");
        miniService.getGlassnode("price",Glassnode.price.getUrl(),"BTC",Glassnode.price.getCookie());
        miniService.getGlassnode("price",Glassnode.price.getUrl(),"ETH",Glassnode.price.getCookie());

        miniService.getGlassnode("addresses_active_addresses_number",Glassnode.addresses_active_addresses_number.getUrl(),"BTC",Glassnode.addresses_active_addresses_number.getCookie());
        miniService.getGlassnode("addresses_active_addresses_number",Glassnode.addresses_active_addresses_number.getUrl(),"ETH",Glassnode.addresses_active_addresses_number.getCookie());

        miniService.getGlassnode("distribution_with_non_zero_balance_number",Glassnode.distribution_with_non_zero_balance_number.getUrl(),"BTC",Glassnode.distribution_with_non_zero_balance_number.getCookie());
        miniService.getGlassnode("distribution_with_non_zero_balance_number",Glassnode.distribution_with_non_zero_balance_number.getUrl(),"ETH",Glassnode.distribution_with_non_zero_balance_number.getCookie());

        miniService.getGlassnode("exchanges_exchanges_balance_number",Glassnode.exchanges_exchanges_balance_number.getUrl(),"BTC",Glassnode.exchanges_exchanges_balance_number.getCookie());
        miniService.getGlassnode("exchanges_exchanges_balance_number",Glassnode.exchanges_exchanges_balance_number.getUrl(),"ETH",Glassnode.exchanges_exchanges_balance_number.getCookie());

        miniService.getGlassnode("fees_fees_total_number",Glassnode.fees_fees_total_number.getUrl(),"BTC",Glassnode.fees_fees_total_number.getCookie());
        miniService.getGlassnode("fees_fees_total_number",Glassnode.fees_fees_total_number.getUrl(),"ETH",Glassnode.fees_fees_total_number.getCookie());

        miniService.getGlassnode("lifespan_coin_days_destroyed_cdd_number",Glassnode.lifespan_coin_days_destroyed_cdd_number.getUrl(),"BTC",Glassnode.lifespan_coin_days_destroyed_cdd_number.getCookie());
        miniService.getGlassnode("lifespan_coin_days_destroyed_cdd_number",Glassnode.lifespan_coin_days_destroyed_cdd_number.getUrl(),"ETH",Glassnode.lifespan_coin_days_destroyed_cdd_number.getCookie());

        miniService.getGlassnode("lightning_lightning_newwork_capacity_number",Glassnode.lightning_lightning_newwork_capacity_number.getUrl(),"BTC",Glassnode.lightning_lightning_newwork_capacity_number.getCookie());
        miniService.getGlassnode("lightning_lightning_newwork_capacity_number",Glassnode.lightning_lightning_newwork_capacity_number.getUrl(),"ETH",Glassnode.lightning_lightning_newwork_capacity_number.getCookie());

        miniService.getGlassnode("market_indicators_reserve_risk_number",Glassnode.market_indicators_reserve_risk_number.getUrl(),"BTC",Glassnode.market_indicators_reserve_risk_number.getCookie());
        miniService.getGlassnode("market_indicators_reserve_risk_number",Glassnode.market_indicators_reserve_risk_number.getUrl(),"ETH",Glassnode.market_indicators_reserve_risk_number.getCookie());

        miniService.getGlassnode("miners_block_height_number",Glassnode.miners_block_height_number.getUrl(),"BTC",Glassnode.miners_block_height_number.getCookie());
        miniService.getGlassnode("miners_block_height_number",Glassnode.miners_block_height_number.getUrl(),"ETH",Glassnode.miners_block_height_number.getCookie());

        miniService.getGlassnode("ratios_puell_multiple_number",Glassnode.ratios_puell_multiple_number.getUrl(),"BTC",Glassnode.ratios_puell_multiple_number.getCookie());
        miniService.getGlassnode("ratios_puell_multiple_number",Glassnode.ratios_puell_multiple_number.getUrl(),"ETH",Glassnode.ratios_puell_multiple_number.getCookie());

        miniService.getGlassnode("transactions_exchanges_inflow_volume_number",Glassnode.transactions_exchanges_inflow_volume_number.getUrl(),"BTC",Glassnode.transactions_exchanges_inflow_volume_number.getCookie());
        miniService.getGlassnode("transactions_exchanges_inflow_volume_number",Glassnode.transactions_exchanges_inflow_volume_number.getUrl(),"ETH",Glassnode.transactions_exchanges_inflow_volume_number.getCookie());
        log.info("Mini：getGlassnode-------------stop");
    }

    @Scheduled(cron = "50 2 0 * * ?")
    public void setFigureData(){
        log.info("Mini：setFigureData-------------start");
        miniService.setFigureData();
        log.info("Mini：setFigureData-------------stop");
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void setCmcData(){
        log.info("Mini：setCmcData-------------start");
        miniService.setCmcData();
        log.info("Mini：setCmcData-------------stop");
    }

}
