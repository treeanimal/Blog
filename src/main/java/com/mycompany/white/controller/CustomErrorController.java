//package com.mycompany.white.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @GetMapping("/error")
//    public String handleError(HttpServletRequest request, Model model){
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null){
//            int statusCode = Integer.valueOf(status.toString());
//
//            if (statusCode == HttpStatus.NOT_FOUND.value()){
//                return "/error/404";
//            }
//            if (statusCode == HttpStatus.FORBIDDEN.value()){
//                return "/error/500";
//            }
//            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
//                return "/error/500";
//            }
//        }
//        return "error";
//    }
//}
