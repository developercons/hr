package com.recruiting.controller;

import com.recruiting.entity.User;
import com.recruiting.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Marta Ginosyan
 */

public abstract class AbstractController {

    @Autowired
    private UserService userService;


    protected boolean approved() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth == null) return false;
        String authName = auth.getName();
        User user = userService.findUserByUsername(authName);
        return user.getApproved();
    }

    //    protected Model prepareModelForConversationPreviewAndNewMessage(Model model,
//            Pageable pageable,
//            Long conversationId,
//            Message message) {
//        // Finds conversation and sets last message as seen aas this step guarantee that all messages are seen
//        Conversation conversation = conversationService.findById(conversationId);
//        Message conversationLastMessage = conversation.getLastMessage();
//        if (conversationLastMessage != null && (!conversationLastMessage.getSeen())) {
//            conversationLastMessage.setSeen(true);
//            conversation = conversationService.save(conversation);
//        }
//
//        // Creates new message as a placeholder in case user will replay on conversation
//        Message newMessage;
//        if (message == null) newMessage = emptyEntityCreationService.emptyMessage();
//        else newMessage = message;
//        newMessage.setConversation(conversation);
//
//        //Find messages for conversation
//        Page<Message> messages = conversationService.findByConversation(conversation, pageable);
//
//        // Prepares ui with data
//        PageWrapper<Message> pageWrapper = new PageWrapper<>(messages, "");
//        model.addAttribute("pageWrapper", pageWrapper);
//        model.addAttribute("conversation", conversation);
//        model.addAttribute("message", newMessage);
//
//        return model;
//    }
//
//    protected Model prepareModelForConversationPreview(Model model, Pageable pageable, Long conversationId) {
//        // Finds conversation and sets last message as seen aas this step guarantee that all messages are seen
//        Conversation conversation = conversationService.findById(conversationId);
//
//        //Find messages for conversation
//        Page<Message> messages = conversationService.findByConversation(conversation, pageable);
//
//        // Prepares ui with data
//        PageWrapper<Message> pageWrapper = new PageWrapper<>(messages, "");
//        model.addAttribute("pageWrapper", pageWrapper);
//        model.addAttribute("conversation", conversation);
//
//        return model;
//    }
//
    protected User getAuthorizedUser() {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        String authName = auth.getName();
        return userService.findUserByUsername(authName);
    }
}
