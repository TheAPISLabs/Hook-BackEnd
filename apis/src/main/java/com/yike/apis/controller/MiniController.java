package com.yike.apis.controller;

import com.yike.apis.service.MiniService;
import com.yike.apis.utils.reponseUtil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(description = "mini")
@RequestMapping("/mini")
public class MiniController {
    @Autowired
    private MiniService miniService;

    @ApiOperation("Top Collectibles & NFTs Tokens by Market Capitalization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "type：NFT,Defi,metaverse", required = true, dataType = "string")
    })
    @RequestMapping(value = "/getMarketCapitalization", method = RequestMethod.GET)
    public ResponseData getMarketCapitalization(@RequestParam String type){
        return miniService.getMarketCapitalization(type);
    }

    @ApiOperation("Get cmc data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "NFT,Defi; default：NFT and Defi and metaverse", required = false, dataType = "string"),
            @ApiImplicitParam(name = "start", value = "default：1", required = false, dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "default：5", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getCmcData", method = RequestMethod.GET)
    public ResponseData getCmcData(@RequestParam(required = false) String type,@RequestParam(required = false) Integer start,@RequestParam(required = false) Integer limit){
        return miniService.getCmcData(type,start,limit);
    }

    @ApiOperation("Get graph data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "NFT,Defi,metaverse; default：null", required = false, dataType = "string"),
            @ApiImplicitParam(name = "time", value = "1d,3d,7d,30d; default：1d", required = false, dataType = "string")
    })
    @RequestMapping(value = "/getFigureData", method = RequestMethod.GET)
    public ResponseData getFigureData(@RequestParam(required = false) String type,@RequestParam(required = false) String time){
        return miniService.getFigureData(type,time);
    }

    @ApiOperation("Get glassnode graph data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "methods", value = "price,addresses_active_addresses_number," +
                    "distribution_with_non_zero_balance_number,exchanges_exchanges_balance_number," +
                    "fees_fees_total_number,lifespan_coin_days_destroyed_cdd_number," +
                    "lightning_lightning_newwork_capacity_number,market_indicators_reserve_risk_number," +
                    "miners_block_height_number,ratios_puell_multiple_number," +
                    "transactions_exchanges_inflow_volume_number; default：price", required = false, dataType = "string"),
            @ApiImplicitParam(name = "type", value = "BTC,ETH; default：BTC", required = false, dataType = "string")
    })
    @RequestMapping(value = "/getGlassnodeFigureData", method = RequestMethod.GET)
    public ResponseData getGlassnodeFigureData(@RequestParam(required = false) String methods,@RequestParam(required = false) String type){
        return miniService.getGlassnodeFigureData(methods,type);
    }
}
