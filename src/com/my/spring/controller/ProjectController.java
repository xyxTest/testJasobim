package com.my.spring.controller;

import com.my.spring.model.Project;
import com.my.spring.service.ProjectService;
import com.my.spring.utils.DataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
@Controller
@RequestMapping(value="api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @RequestMapping(value="addProject", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> addProject(
            @ModelAttribute Project project,
            @RequestParam(value = "token",required = true) String token){
        return projectService.addProject(project,token);
    }
    @RequestMapping(value="deleteProject")
    @ResponseBody
    public DataWrapper<Void> deleteProject(
            @RequestParam(value = "id",required = true) Long id,
            @RequestParam(value = "token",required = true) String token){
        return projectService.deleteProject(id,token);
    }

    @RequestMapping(value="updateProject",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> updateProject(
            @ModelAttribute Project project,
            @RequestParam(value = "token",required = true) String token){
        System.out.println(project);
        return projectService.updateProject(project,token);
    }


    @RequestMapping(value="getProjectList")
    @ResponseBody
    public DataWrapper<List<Project>> getProjectList(
    		@RequestParam(value = "token",required = true) String token)
    {
        return projectService.getProjectList(token);
    }
    @RequestMapping(value="getProjectDetails/{projectId}")
    @ResponseBody
    public DataWrapper<Project> getProjectDetailsByAdmin(
    		@PathVariable(value="projectId") Long projectId,
    		@RequestParam(value="token",required=true) String token){
        return projectService.getProjectDetailsByAdmin(projectId,token);
    }
    
}