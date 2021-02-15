package com.bridge.record.controller;

import javax.servlet.http.HttpServletRequest;

import com.bridge.common.AjaxResult;
import com.bridge.record.model.SysUser;
import com.bridge.record.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private UserDetailsServiceImpl userService;
    @PostMapping("/list")
    public AjaxResult<Iterable<SysUser>> getPageList(
        ){
            Iterable<SysUser> its= userService.getNurse();

            return AjaxResult.success(its);
    }
    @PostMapping("/new")
    public AjaxResult<SysUser> addnew(HttpServletRequest request){
        SysUser record = userService.createOne();
        return AjaxResult.success(record);
    }
    @PostMapping("/save")
    public AjaxResult<Integer> save(@RequestBody SysUser record){
        
        return AjaxResult.success(userService.save(record));
    }
    @GetMapping("/{id}")
    public AjaxResult<SysUser> selectByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(userService.selectByPrimaryKey(id));
    }
    @DeleteMapping("/{id}")
    public AjaxResult<Integer> deleteByPrimaryKey(@PathVariable Long id){
        
        return AjaxResult.success(userService.deleteByPrimaryKey(id));
    }
    
    
}
