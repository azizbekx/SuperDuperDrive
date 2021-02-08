package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/add")
    public String insertOrUpdate(Authentication authentication,
                                 @ModelAttribute("newCredential") CredentialModel newCredential,
                                 Model model){
        boolean success = false;
        int errorType = 1;
        String username = authentication.getName();

        success = credentialService.insertOrUpdateCredential(newCredential, username);

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);
        return "result";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(Authentication authentication,
                                   @PathVariable Integer credentialId,
                                   Model model){
        boolean success = false;
        int errorType = 1;

        success = credentialService.deleteCredential(credentialId);

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);
        return "result";

    }



}
