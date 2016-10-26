package com.my.spring.controller;

import com.my.spring.model.Paper;
import com.my.spring.service.PaperService;
import com.my.spring.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
@Controller
@RequestMapping(value="api/paper")
public class PaperController {
    @Autowired
    PaperService paperService;
    @RequestMapping(value="addPaper", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> addPaper(
            @ModelAttribute Paper paper,
            @RequestParam(value = "token",required = true) String token){
        return paperService.addPaper(paper,token);
    }
    @RequestMapping(value="deletePaper")
    @ResponseBody
    public DataWrapper<Void> deletePaper(
            @RequestParam(value = "id",required = true) Long id,
            @RequestParam(value = "token",required = true) String token){
        return paperService.deletePaper(id,token);
    }

    @RequestMapping(value="updatePaper",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> updatePaper(
            @ModelAttribute Paper paper,
            @RequestParam(value = "token",required = true) String token){
        System.out.println(paper);
        return paperService.updatePaper(paper,token);
    }


    @RequestMapping(value="getPaperList")
    @ResponseBody
    public DataWrapper<List<Paper>> getPaperList(
    		@RequestParam(value="token",required=true) String token){
        return paperService.getPaperList(token);
    }
    @RequestMapping(value="getPaperDetails/{paperId}")
    @ResponseBody
    public DataWrapper<Paper> getPaperDetailsByAdmin(
    		@PathVariable(value="paperId") Long paperId,
    		@RequestParam(value="token",required=true) String token){
        return paperService.getPaperDetailsByAdmin(paperId,token);
    }
    
}