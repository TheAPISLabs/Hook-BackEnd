package com.yike.apis.utils;

import com.yike.apis.pojo.game.Gameprojectlinked;
import com.yike.apis.pojo.game.vo.LikedCountVo;
import com.yike.apis.pojo.game.vo.LikedStatusEnum;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    private final String LOCK_FLAG = "redissonlock_";

    /**
     * RedissonLock is a published-subscribe mechanism that blocks transactions based on name
     * @param key
     */
    public void lock(RedissonClient redisson, String key){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        //Lock Provides the timeout parameter. Timeout ends forcible unlocking and prevents deadlock: 1 minute
        lock.lock(2, TimeUnit.MINUTES);
    }


    public void lock(RedissonClient redisson,String key,Integer time){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        //Lock Provides the timeout parameter. Timeout ends forcible unlocking and prevents deadlock: 1 minute
        lock.lock(time, TimeUnit.MINUTES);
    }

    public Boolean tryLock(RedissonClient redisson,String key,Integer time){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        try {
            return lock.tryLock(time, 60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Unlock the user based on name
     * @param key
     */
    public void unlock(RedissonClient redisson,String key){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        if(lock.isLocked()){ // Whether the state is still locked
            if(lock.isHeldByCurrentThread()){ // Is the lock of the currently executing thread
                lock.unlock(); // Release the lock
            }
        }
    }

    /**
     * Check whether the thread is locked by name
     * @param key
     */
    public Boolean isHeldByCurrentThread(RedissonClient redisson,String key){
        String lockKey = LOCK_FLAG + key;
        RLock lock = redisson.getLock(lockKey);
        if(lock.isLocked()){ // Whether the state is still locked
            if(lock.isHeldByCurrentThread()){ // Is the lock of the currently executing thread
                return true;
            }
        }
        return false;
    }

    /**
     * Add an element, the biggest difference between zset and set is that each element has a score, so there is a sorting auxiliary function; zadd
     *
     * @param key
     * @param value
     * @param score
     */
    public void add(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * Delete the element zrem
     *
     * @param key
     * @param value
     */
    public void remove(String key, String value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    /**
     * Score increases or decreases zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public Double incrScore(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * Query score zscore of value
     *
     * @param key
     * @param value
     * @return
     */
    public Double score(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * Determine the zrank of a value in a zset
     *
     * The smaller integral is in the front
     *
     * @param key
     * @param value
     * @return
     */
    public Long rank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * Query a set of values in the specified order. 0-1 means to get all the set contents zrange
     *
     * Returns the ordered set with the score smaller in front
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * Query the specified sequence of values in the collection and score, 0, -1 means to obtain all the collection content
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * Query zrevrange for the specified sequential value in the collection
     *
     * Returns the ordered set with the highest score in front
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> revRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * According to the value of score, the set zrangebyScore meeting the conditions can be obtained
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, long min, long max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * Returns the length of the collection
     *
     * @param key
     * @return
     */
    public Long size(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }


    /**
     * give a like Status to 1
     */
    public void saveLiked2Redis(String likedGpId, String likedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedGpId, likedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode().toString());
    }

    /**
     * Bad review. State of -1
     */
    public void savePoorRedis(String likedGpId, String likedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedGpId, likedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.POOR.getCode().toString());
    }

    /**
     * Cancel the likes. Change the state to 0
     */
    public void unlikeFromRedis(String likedGpId, String likedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedGpId, likedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode().toString());
    }

    /**
     * Get rid of bad reviews. Change the state to 0
     */
    public void unpoorFromRedis(String likedGpId, String likedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedGpId, likedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNPOOR.getCode().toString());
    }


    /**
     * Delete a "like" from Redis
     */
    public void deleteLikedFromRedis(String likedGpId, String likedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedGpId, likedUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }

    /**
     * The number of likes for this item increases by 1
     */
    public void incrementLikedCount(String likedGpId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_GAME_LIKED_COUNT, likedGpId, 1);
    }

    /**
     * The number of likes for the project is reduced by 1
     */
    public void decrementLikedCount(String likedGpId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_GAME_LIKED_COUNT, likedGpId, -1);
    }

    /**
     * The negative rating for the project is increased by 1
     */
    public void incrementPoorCount(String likedGpId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_GAME_FOOR_COUNT, likedGpId, 1);
    }

    /**
     * The negative rating of the project was reduced by 1
     */
    public void decrementPoorCount(String likedGpId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_GAME_FOOR_COUNT, likedGpId, -1);
    }

    public List<Gameprojectlinked> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<Gameprojectlinked> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = entry.getKey().toString();
            //Isolate likedUserId, likedPostId
            String[] split = key.split("::");
            String likedGpId = split[0];
            String likedPostId = split[1];
            Integer value = Integer.parseInt(entry.getValue().toString());

            //Assemble into a UserLike object
            Gameprojectlinked gameprojectlinked = new Gameprojectlinked(likedGpId, likedPostId, value);
            list.add(gameprojectlinked);

            //Save to list and delete from Redis
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    public List<LikedCountVo> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_GAME_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountVo> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //Store the number of likes in LikedCountDT
            String key = map.getKey().toString();
            LikedCountVo dto = new LikedCountVo(key,Long.parseLong(String.valueOf( map.getValue())));
            list.add(dto);
            //Delete this record from Redis
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_GAME_LIKED_COUNT, key);
        }
        return list;
    }

    public List<LikedCountVo> getRoorCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_GAME_FOOR_COUNT, ScanOptions.NONE);
        List<LikedCountVo> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //Store the number of likes in LikedCountDT
            String key = map.getKey().toString();
            LikedCountVo dto = new LikedCountVo(key, Long.parseLong(String.valueOf( map.getValue())));
            list.add(dto);
            //Delete this record from Redis
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_GAME_FOOR_COUNT, key);
        }
        return list;
    }

}
