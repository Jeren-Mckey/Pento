package com.example.PentoApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PentoApi.doa.Message;
import com.example.PentoApi.service.MessageService;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Controller for Messages. Keep this class easy to read.
 */

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService = new MessageService();

    /**
     * Adds a message to the database
     * param Message - message being added
     * return String - Confirmation string
     */
    @PostMapping(value = "/message/{group_id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody String postMessage(@PathVariable String group_id, @RequestBody Message message) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return messageService.postMessage(group_id, message);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping(value = "/message/{group_id}")
    public @ResponseBody ArrayList<Message> getAllMessages(@PathVariable String group_id) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return messageService.getAllMessages(group_id);
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
    @DeleteMapping(value = "/message/{group_id}/{message_id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody String deleteGroup(@PathVariable String group_id, @PathVariable String message_id) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            return messageService.deleteMessage(group_id, message_id);
        }
        catch (Exception e)
        {
            return null;
        }
    }

}
