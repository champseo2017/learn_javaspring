package com.SpringBootMysql.SpringBootMysql.template;

import org.springframework.stereotype.Component;

@Component
public class BuyerRequirementTemplate {

    public String template(String productname, String name, String emails, String mobile, String city) {
        return String.format(
                "<html>" +
                        "<body>" +
                        "<p>Product Name: %s</p>" +
                        "<p>Name: %s</p>" +
                        "<p>Email: %s</p>" +
                        "<p>Mobile: %s</p>" +
                        "<p>City: %s</p>" +
                        "</body>" +
                        "</html>",
                productname, name, emails, mobile, city);
    }

}