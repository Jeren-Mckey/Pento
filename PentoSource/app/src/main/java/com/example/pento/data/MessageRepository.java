package com.example.pento.data;

import com.example.pento.data.model.ResponseMessage;
import com.example.pento.data.model.ResponseMessageList;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class MessageRepository {

    private static volatile MessageRepository instance;

    private MessageDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private ResponseMessageList messages = null;

    // private constructor : singleton access
    private MessageRepository(MessageDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static MessageRepository getInstance(MessageDataSource dataSource) {
        if (instance == null) {
            instance = new MessageRepository(dataSource);
        }
        return instance;
    }


    public Result<String> sendMessage(String content, String member_id, String group_id) {
        // handle login
        Result<String> result = dataSource.sendMessage(content, member_id, group_id);
        return result;
    }

    public Result<ResponseMessageList> getMessages(String group_id)
    {
        Result<ResponseMessageList> result = dataSource.getMessages(group_id);
        return result;
    }
}