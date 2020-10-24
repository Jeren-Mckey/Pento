package com.example.PentoApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PentoApi.doa.Group;
import com.example.PentoApi.service.GroupService;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controller for Group interactions. Keep this class easy to read.
 */

@RestController
public class GroupController {

    @Autowired
    private GroupService loginService = new GroupService();

    @GetMapping(value = "/group")
    public List<Group> getGroup() throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return GroupService.getAllGroups();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Gets group info
     * return Group
     */
    @GetMapping(value = "/group/{group_name}")
    public Group getGroup(@PathVariable String group_name) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return GroupService.getGroup(group_name);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Adds a group to the database
     * param Group - group being added
     * return String - Confirmation string
     */
    @PostMapping(value = "/group", consumes = "application/json", produces = "application/json")
    public String postGroup(@RequestBody Group group) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return GroupService.postGroup(group);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Delete group from database
     * param group_id - id of the the group to be deleted
     * return String - confirmation string
     */
    @DeleteMapping(value = "/group/{group_id}", consumes = "application/json", produces = "application/json")
    public String postGroup(@PathVariable Group group_id) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return GroupService.deleteGroup(group_id);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Update group info from database
     * param group_id - id of the the group to be updated
     * return String - confirmation string
     */
    @PutMapping(value = "/group", consumes = "application/json", produces = "application/json")
    public String postGroup(@RequestBody Group group) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return GroupService.updateGroup(group);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

