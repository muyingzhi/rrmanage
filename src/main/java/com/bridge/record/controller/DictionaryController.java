package com.bridge.record.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bridge.record.model.BizDictionary;
import com.bridge.record.service.BizDictionaryServiceImpl;
import com.bridge.common.AjaxResult;
import com.bridge.common.PageRequest;
import com.bridge.common.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "3-字典管理")
@RequestMapping("/api/dict")
public class DictionaryController {
    @Autowired
    private BizDictionaryServiceImpl dictionaryService;

    @PostMapping(value = "/getList")
    @ApiOperation("查询字典下拉选")
    public AjaxResult<List<BizDictionary>> getDictList(BizDictionary record ){
        List<BizDictionary> list= dictionaryService.getList(record);
        return AjaxResult.success(list);
    }


    @ApiOperation("分页查询")
    @PostMapping("/getPageList")
    public AjaxResult<PageResponse<BizDictionary>> getPaPageList(BizDictionary record,PageRequest page){

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<BizDictionary> list=dictionaryService.getList(record);
        PageInfo<BizDictionary> pageInfo = new PageInfo<>(list);
        PageResponse<BizDictionary> responseVo=new PageResponse<>();
        responseVo.setPageData(pageInfo.getList());
        responseVo.setTotal(pageInfo.getTotal());
        return AjaxResult.success(responseVo);
    }
    @PostMapping(value = "/getDictById")
    @ApiOperation("根据ID查询字典详情")
    public AjaxResult<BizDictionary> getDictById(@ApiParam(name="id",value = "字典id",required = true)  @RequestParam String id){
        return AjaxResult.success(dictionaryService.selectByPrimaryKey(id));
    }

    @ApiOperation("保存字典")
    @PostMapping("/save")
    public AjaxResult<Integer> addDict(@ApiParam(name="字典信息",value="新增字典参数") @Validated @RequestBody BizDictionary bizComponent){
        return AjaxResult.success(dictionaryService.save(bizComponent));
    }
    @ApiOperation("删除字典")
    @GetMapping("/deleteDict")
    public AjaxResult<Integer> deleteDict(@ApiParam(name = "id",value = "唯一主键",required = true)  @RequestParam(required = true) String id){
        return AjaxResult.success(dictionaryService.deleteByPrimaryKey(id));
    }

    @PostMapping(value = "/getDictListByType")
    @ApiOperation("查询字典下拉选")
    public AjaxResult<List<BizDictionary>> getDictListByType(String type){
        List<BizDictionary> list= dictionaryService.getDictListByType(type);
        return AjaxResult.success(list);
    }
}
