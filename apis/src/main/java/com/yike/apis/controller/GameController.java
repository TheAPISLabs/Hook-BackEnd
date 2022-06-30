package com.yike.apis.controller;

import com.yike.apis.aop.NoRepeatSubmit;
import com.yike.apis.pojo.game.Gameicon;
import com.yike.apis.pojo.game.GameiconGameprojectUser;
import com.yike.apis.pojo.game.Gameremark;
import com.yike.apis.pojo.game.vo.CommentLikedVo;
import com.yike.apis.pojo.game.vo.GameremarkVo;
import com.yike.apis.pojo.game.vo.LikedVo;
import com.yike.apis.service.GameService;
import com.yike.apis.service.TrachService;
import com.yike.apis.utils.RedisUtil;
import com.yike.apis.utils.reponseUtil.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@Api(description = "game")
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private TrachService trachService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("Get game items")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "projectName", required = false, dataType = "string"),
            @ApiImplicitParam(name = "sortField", value = "liked,initialReleaseDate", required = false, dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "asc,desc", required = false, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "page", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getGameItems", method = RequestMethod.GET)
    public ResponseData getGameItems(@RequestParam(required = false) String projectName,@RequestParam(required = false) String sortField,@RequestParam(required = false) String sort,
                                     @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize){
        return gameService.getGameItems(projectName,sortField,sort,page,pageSize);
    }

    @ApiOperation("Get game")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gpId", value = "gpId", required = true, dataType = "string")
    })
    @RequestMapping(value = "/getGame", method = RequestMethod.GET)
    public ResponseData getGame(@RequestParam String gpId){
        return gameService.getGame(gpId);
    }

    @ApiOperation("Get comments")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gpId", value = "gpId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "uId", value = "uId", required = false, dataType = "string"),
            @ApiImplicitParam(name = "sortField", value = "ALL,Most Hot,Recent,Pass Holder", required = false, dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "asc,desc", required = false, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "page", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getComments", method = RequestMethod.GET)
    public ResponseData getComments(@RequestParam String gpId,@RequestParam(required = false) String uId,@RequestParam(required = false) String sortField,@RequestParam(required = false) String sort,
                                     @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize){
        return gameService.getComments(gpId,uId,sortField,sort,page,pageSize);
    }

    @ApiOperation("Comment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameremark", value = "gameremark[Will pass:gpId,uid,content;Choose the:parentId,rootParentId]", required = true, dataType = "Gameremark")
    })
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData comment(@RequestBody(required = false) Gameremark gameremark){
        return gameService.comment(gameremark);
    }

    @ApiOperation("Get Game Icon")
    @RequestMapping(value = "/getGameIcon", method = RequestMethod.GET)
    public ResponseData getGameIcon(){
        return gameService.getGameIcon();
    }

    @ApiOperation("upload Game Icon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameicon", value = "gameicon[Will pass:icon]", required = true, dataType = "Gameicon")
    })
    @RequestMapping(value = "/uploadGameIcon", method = RequestMethod.POST)
    public ResponseData uploadGameIcon(@RequestBody(required = false) Gameicon gameicon){
        return gameService.uploadGameIcon(gameicon);
    }

    @ApiOperation("user set Game Icon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameiconGameprojectUser", value = "gameiconGameprojectUser[Will pass:gpId,uid,giId]", required = true, dataType = "GameiconGameprojectUser")
    })
    @RequestMapping(value = "/userSetGameIcon", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData userSetGameIcon(@RequestBody(required = false) GameiconGameprojectUser gameiconGameprojectUser){
        return gameService.userSetGameIcon(gameiconGameprojectUser);
    }

    @ApiOperation("del user set Game Icon")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gameiconGameprojectUser", value = "gameiconGameprojectUser[Will pass:gpId,uid,giId]", required = true, dataType = "GameiconGameprojectUser")
    })
    @RequestMapping(value = "/delUserSetGameIcon", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData delUserSetGameIcon(@RequestBody(required = false) GameiconGameprojectUser gameiconGameprojectUser){
        return gameService.delUserSetGameIcon(gameiconGameprojectUser);
    }

    @ApiOperation("Get Game Icon By GameProject")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gpId", value = "gpId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "uId", value = "uId", required = false, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "page", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = false, dataType = "int")
    })
    @RequestMapping(value = "/getGameIconByGpId", method = RequestMethod.GET)
    public ResponseData getGameIconByGpId(@RequestParam String gpId,@RequestParam(required = false) String uId,
                                          @RequestParam(required = false) Integer page,@RequestParam(required = false) Integer pageSize){
        return gameService.getGameIconByGpId(gpId,uId,page,pageSize);
    }

    @ApiOperation("liked")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "likedVo", value = "gameremark[Will pass:likedGpId(Game Project Number),likedUserId(The user id)," +
                    "status(1: favorable comment, -1: negative comment, 0: cancel favorable comment, -2: cancel negative comment)]", required = true, dataType = "LikedVo")
    })
    @RequestMapping(value = "/liked", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData liked(@RequestBody LikedVo likedVo){
        return gameService.liked(likedVo);
    }

    @ApiOperation("social media")
    @RequestMapping(value = "/socialMedia", method = RequestMethod.GET)
    public ResponseData socialMedia(){
        return gameService.socialMedia();
    }

    @ApiOperation("Comment liked")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commentLikedVo", value = "commentLikedVo[Will pass:grId(Game Comment Number),uId(The user id)," +
                    "status(1: favorable comment, 0: cancel favorable comment)]", required = true, dataType = "CommentLikedVo")
    })
    @RequestMapping(value = "/commentLiked", method = RequestMethod.POST)
    @NoRepeatSubmit
    public ResponseData commentLiked(@RequestBody CommentLikedVo commentLikedVo){
        return gameService.commentLiked(commentLikedVo);
    }

    @ApiOperation("tokenTokentrans")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenAddress", value = "tokenAddress", required = true, dataType = "string"),
            @ApiImplicitParam(name = "start", value = "start", required = true, dataType = "string"),
            @ApiImplicitParam(name = "limit", value = "limit", required = true, dataType = "string")
    })
    @RequestMapping(value = "/tokenTokentrans", method = RequestMethod.GET)
    @NoRepeatSubmit
    public ResponseData tokenTokentrans(@RequestParam String tokenAddress,@RequestParam String start,@RequestParam String limit){
        return gameService.tokenTokentrans(tokenAddress,start,limit);
    }
}
