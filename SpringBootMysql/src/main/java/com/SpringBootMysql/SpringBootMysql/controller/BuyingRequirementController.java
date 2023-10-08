package com.SpringBootMysql.SpringBootMysql.controller;

import com.SpringBootMysql.SpringBootMysql.model.BuyerRequirement;
import com.SpringBootMysql.SpringBootMysql.repository.BuyerRequirementRepository;
import com.SpringBootMysql.SpringBootMysql.service.EmailService;
import com.SpringBootMysql.SpringBootMysql.template.BuyerRequirementTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BuyingRequirementController {

    @Autowired
    private BuyerRequirementRepository iBuyingRequirementsRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BuyerRequirementTemplate buyerRequirementTemplate;

    private String To = "support@pharmerz.com";
    // private String To = "amigujarathi@gmail.com";
    private String Subject = "Buyer Request From Pharmerz ";

    @PostMapping(value = "/buyingRequirement")
    public ResponseEntity<BuyerRequirement> createBuyingRequirement(@RequestBody BuyerRequirement buyingRequirements) {
        String productname = buyingRequirements.getProductName();
        String name = buyingRequirements.getName();
        String mobile = buyingRequirements.getMobile();
        String emails = buyingRequirements.getEmail();
        String city = buyingRequirements.getCity();
        if (city == null) {
            city = "-";
        }
        String HTMLBODY = buyerRequirementTemplate.template(productname, name, emails, mobile, city);
        emailService.sendMail(To, Subject, HTMLBODY);
        iBuyingRequirementsRepository.save(buyingRequirements);
        return new ResponseEntity<>(buyingRequirements, HttpStatus.CREATED);
    }

    @GetMapping(value = "/buyingRequirements")
    public Page<BuyerRequirement> getAllBuyingRequirements(Pageable pageable) {
        Page<BuyerRequirement> requirements = iBuyingRequirementsRepository.findAllByOrderByCreatedDesc(pageable);
        return requirements;
    }

    @GetMapping(value = "/buyingRequirementByName/{name}")
    public Page<BuyerRequirement> getByName(@PathVariable String name, Pageable pageable) {
        Page<BuyerRequirement> buyersByName = iBuyingRequirementsRepository.findByNameContainingIgnoreCase(name,
                pageable);
        return buyersByName;
    }
}
