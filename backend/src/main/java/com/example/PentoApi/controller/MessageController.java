package com.example.PentoApi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.PentoApi.doa.Message;
import com.example.PentoApi.service.MessageService;

import java.util.ArrayList;
import java.util.List;
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
    @PostMapping(value = "/message", consumes = "application/json", produces = "application/json")
    public String postMessage(@RequestBody Message message) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try
        {
            System.out.println(message.getContent());
            return messageService.postMessage(message);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @GetMapping(value = "/message/{group_id}")
    public @ResponseBody List<Message> getAllMessages(@PathVariable String group_id) throws InterruptedException, ExecutionException {
        long startTime = System.nanoTime();
        try{
            return messageService.getAllMessages(group_id);
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
