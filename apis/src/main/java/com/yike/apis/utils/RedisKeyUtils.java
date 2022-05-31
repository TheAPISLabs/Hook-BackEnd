package com.yike.apis.utils;

public class RedisKeyUtils {

    //The key that holds the user's like data
    public static final String MAP_KEY_USER_LIKED = "MAP_KEY_USER_LIKED";

    //Saves the key of the number of likes for the project
    public static final String MAP_KEY_GAME_LIKED_COUNT = "MAP_KEY_GAME_LIKED_COUNT";

    //Save the key for the number of bad comments for the project
    public static final String MAP_KEY_GAME_FOOR_COUNT = "MAP_KEY_GAME_FOOR_COUNT";

    /**
     * Concatenate the liked project ID with the liked person's ID as the key. Format: 222222:333333
     * @param likedUserId Id of the project that was liked
     * @param likedPostId The ID of the person who liked it
     * @return
     */
    public static String getLikedKey(String likedUserId, String likedPostId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }
}
