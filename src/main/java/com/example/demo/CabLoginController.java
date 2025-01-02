package com.example.demo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CabLoginController {

    @Autowired
    private CabService service;

    Logger log = Logger.getAnonymousLogger();

    // Load the login page
    @RequestMapping("/")
    public ModelAndView loadpage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");
        return mv;
    }

    // Handle login request
    @RequestMapping("/login")
    public ModelAndView loginaction(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        Cab cab = service.loginCheck(req.getParameter("fullName"), req.getParameter("pwd"));
        if (cab != null) {
            mv.setViewName("booking.jsp"); // Redirect to booking page after successful login
            mv.addObject("cab", cab);
        } else {
            mv.setViewName("fail.jsp");
        }
        return mv;
    }

   

    // Register a new cab
    @ResponseBody
    @RequestMapping("/registerlogin")
    public ModelAndView redirectToRegisterMs(HttpServletRequest req) {
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("pwd");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String url = "http://Cab-register:8090/register/" + fullName + "/" + password + "/" + email + "/" + phoneNumber;
        log.info(url);
        RestTemplate temp = new RestTemplate(); // REST API call
        temp.getForObject(url, String.class);
        ModelAndView modelAndView = new ModelAndView("index.jsp");
        modelAndView.addObject("message", "Registration done");

        return modelAndView;
    }
    
 // Book a cab
    @ResponseBody
    @RequestMapping("/book")
    public String redirectToBookMs(HttpServletRequest req) {
        // Collect booking details from the request
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("pwd");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String location = req.getParameter("location");
        String destination = req.getParameter("destination");
        String carType = req.getParameter("carType");
        int distance = Integer.parseInt(req.getParameter("distance"));
        int fare = distance * 2;

        // Construct the REST API URL for booking
        String url = "http://Cab-booking:8091/book/" + fullName + "/" + password + "/" + email + "/" + phoneNumber
                     + "/" + location + "/" + destination + "/" + carType + "/" + distance;

        // Log the URL
        log.info("Calling REST API: " + url);

        // Make the REST API call
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);

        // Return booking confirmation
        return "Booking successful for " + fullName + ". Your fare is: " + fare;
    }
}