package com.my.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.spring.model.Item;
import com.my.spring.service.ItemService;
import com.my.spring.utils.DataWrapper;

/**
 * Created by Administrator on 2016/6/22.
 */
@Controller
@RequestMapping(value="api/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @RequestMapping(value="addItem", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> addItem(
            @ModelAttribute Item item,
            @RequestParam(value = "token",required = false) String token){
        return itemService.addItem(item,token);
    }
    @RequestMapping(value="deleteItem")
    @ResponseBody
    public DataWrapper<Void> deleteItem(
            @RequestParam(value = "id",required = false) Long id,
            @RequestParam(value = "token",required = false) String token){
        return itemService.deleteItem(id,token);
    }
    @RequestMapping(value="deleteItemByTypeNameAndProjectId")
    @ResponseBody
    public DataWrapper<Void> deleteItemByTypeNameAndProjectId(
            @RequestParam(value = "typename",required = true) String typename,
            @RequestParam(value = "projectId",required = true) Long projectId,
            @RequestParam(value = "token",required = false) String token){
        return itemService.deleteItemByTypeNameAndProjectId(projectId,typename,token);
    }
    @RequestMapping(value="deleteItemByProjectId")
    @ResponseBody
    public DataWrapper<Void> deleteItemByProjectId(
            @RequestParam(value = "projectId",required = true) Long projectId,
            @RequestParam(value = "token",required = false) String token){
        return itemService.deleteItemByProjectId(projectId,token);
    }


    @RequestMapping(value="updateItem",method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> updateItem(
            @ModelAttribute Item Item,
            @RequestParam(value = "token",required = false) String token){
        System.out.println(Item);
        return itemService.updateItem(Item,token);
    }


    @RequestMapping(value="getItemList")
    @ResponseBody
    public DataWrapper<List<Item>> getItemList(
            @RequestParam(value = "token",required = false) String token){
        return itemService.getItemList(token);
    }
    @RequestMapping(value="getItemById")
    @ResponseBody
    public DataWrapper<Item> getItemById(
    		@RequestParam(value = "id",required = true) Long id,
            @RequestParam(value = "token",required = false) String token){
        return itemService.getItemById(id,token);
    }
    @RequestMapping(value="getItemByOthers")
    @ResponseBody
    public DataWrapper<List<Item>> getItemByOthers(
    		@RequestParam(value = "projectId",required = true) Long projectId,
    		@RequestParam(value = "typeName",required = false) Long typeName,
    		@RequestParam(value = "buildingNum",required = false) Long buildingNum,
    		@RequestParam(value = "floorNum",required = false) Long floorNum,
    		@RequestParam(value = "unitNum",required = false) Long unitNum,
    		@RequestParam(value = "householdNum",required = false) Long householdNum,
    		@RequestParam(value = "token",required = false) String token){
        return itemService.getItemByOthers(projectId,typeName,buildingNum,floorNum,unitNum,householdNum,token);
    }

}