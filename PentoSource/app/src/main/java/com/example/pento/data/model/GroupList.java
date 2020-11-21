package com.example.pento.data.model;

import java.util.ArrayList;
import java.util.List;

public class GroupList {
    //    I think a REsponse MEssage list should be a model that updates from the database constantly
    List<Group> groupList;

    public GroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public GroupList(){
        groupList = new ArrayList<>();
    }

    public void add(Group group){
        groupList.add(group);
    }

    public Group get(int position) {
        return groupList.get(position);
    }
    public int size(){
        return groupList.size();
    }

    @Override
    public String toString() {
        return "GroupList{" +
                "groupList=" + groupList.get(0).toString() +
                '}';
    }
}

