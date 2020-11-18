package com.example.pento.data;

import com.example.pento.data.model.Group;
import com.example.pento.data.model.ResponseMessageList;

import java.util.List;

public class GroupRepository {

    private static volatile GroupRepository instance;

    private GroupDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private List<Group> groups = null;

    // private constructor : singleton access
    public GroupRepository(GroupDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static GroupRepository getInstance(GroupDataSource dataSource) {
        if (instance == null) {
            instance = new GroupRepository(dataSource);
        }
        return instance;
    }

    public Result<List<Group>> getGroupsByQuery(String query) {
        // handle login
        Result<List<Group>> result = dataSource.getGroupsByQuery(query);
        return result;
    }

    public Result<String> createGroup(String user_id, String region, String name, String tag)
    {
        Group group = new Group();
        Result<String> result = dataSource.createGroup(group);
        return result;
    }

}
