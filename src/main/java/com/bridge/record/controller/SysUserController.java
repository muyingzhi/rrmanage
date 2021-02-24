package com.bridge.record.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import com.bridge.record.model.SysRole;
import com.bridge.record.model.SysUser;
import com.bridge.record.service.UserDetailsServiceImpl;
import com.bridge.record.vo.VoUser;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private UserDetailsServiceImpl userService;
    @PostMapping("/nurse/list")
    public AjaxResult<Iterable<SysUser>> getNurseList(){
        Iterable<SysUser> its= userService.getNurse();
        return AjaxResult.success(its);
    }
    @PostMapping("/list")
    public AjaxResult<PageResponse<SysUser>> getPageList(
        @RequestParam String fullname,
        @RequestBody PageRequest page
        ){
            
            Iterable<SysUser> its= userService.getByFullname(fullname);
            PageInfo<SysUser> pageInfo = new PageInfo<>(Lists.newArrayList(its));
            PageResponse<SysUser> responseVo=new PageResponse<>();
            responseVo.setPageData(pageInfo.getList());
            responseVo.setTotal(pageInfo.getTotal());

            return AjaxResult.success(responseVo);
    }
    @PostMapping("/new")
    public AjaxResult<SysUser> addnew(HttpServletRequest request){
        SysUser record = userService.createOne();
        return AjaxResult.success(record);
    }
    @PostMapping("/save")
    public AjaxResult<Integer> save(@RequestBody VoUser user){
        SysUser record = new SysUser();
        record.setFullname(user.getFullname());
        record.setId(user.getId());
        record.setUsername(user.getUsername());
        record.setPassword(user.getPassword());


        List<SysRole> roles = new ArrayList<>();
        user.getRoles().forEach((one)->{
            SysRole role = new SysRole(one,"role");
            roles.add(role);
        });
        record.setRoles(roles);
        record.setRoleId(roles.get(0).getRoleid());
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
