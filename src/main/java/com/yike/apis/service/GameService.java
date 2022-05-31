package com.yike.apis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yike.apis.pojo.game.Gameicon;
import com.yike.apis.pojo.game.GameiconGameprojectUser;
import com.yike.apis.pojo.game.Gameprojectlinked;
import com.yike.apis.pojo.game.Gameremark;
import com.yike.apis.pojo.game.vo.CommentLikedVo;
import com.yike.apis.pojo.game.vo.LikedVo;
import com.yike.apis.utils.reponseUtil.ResponseData;

import java.util.List;

public interface GameService {
    ResponseData getGameItems(String projectName,String sortField,String sort,Integer page, Integer pageSize);

    ResponseData getComments(String gpId, String uId,String sort,Integer page, Integer pageSize);

    ResponseData comment(Gameremark gameremark);

    ResponseData getGameIcon();

    ResponseData getGameIconByGpId(String gpId, String uId,Integer page, Integer pageSize);





    /**
     * Save the likes record
     * @param gameprojectlinked
     * @return
     */
    ResponseData save(Gameprojectlinked gameprojectlinked);


    /**
     * Query the likes list based on the id of the liked item.
     * @param likedGpId The ID of the liked item
     * @return
     */
    ResponseData getLikedListByLikedByLikedGpId(String likedGpId, Integer status,Integer page, Integer pageSize);

    /**
     * Query the likes list based on the likes person's ID.
     * @param likedUserId
     * @return
     */
    ResponseData getLikedListBylikedUserId(String likedUserId,Integer status,Integer page, Integer pageSize);

    /**
     * Check whether there is a "like" record by the ID of the "like" item and the id of the "like" person
     * @param likedGpId
     * @param likedUserId
     * @return
     */
    ResponseData getByLikedUserIdAndLikedGpId(String likedGpId, String likedUserId);

    /**
     * The likes in Redis are stored in the database
     */
    void transLikedFromRedis2DB();

    /**
     * Store the data of the number of likes in Redis into the database
     */
    void transLikedCountFromRedis2DB();

    ResponseData liked(LikedVo likedVo);

    ResponseData socialMedia();

    void GameThreePartyDataSynchronization();

    ResponseData userSetGameIcon(GameiconGameprojectUser gameiconGameprojectUser);

    ResponseData getBuyTokenImg(String gpId, String tokenId);

    ResponseData getPrice(String gpId);

    ResponseData uploadGameIcon(Gameicon gameicon);

    Object normal(String address, String start, String limit);

    ResponseData getGame(String gpId);

    ResponseData commentLiked(CommentLikedVo commentLikedVo);

    void commentLikedDataSynchronization();

    ResponseData tokenTokentrans(String tokenAddress, String start, String limit);

    ResponseData delUserSetGameIcon(GameiconGameprojectUser gameiconGameprojectUser);
}
