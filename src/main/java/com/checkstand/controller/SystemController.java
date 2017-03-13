package com.checkstand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * Created by 11723 on 2017/3/13.
 */
@Controller
@RequestMapping("system")
public class SystemController {

    public static int prace = 666;
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public void chang(@RequestParam(value = "input",defaultValue = "666")int input){
        prace = input;
    }
}
