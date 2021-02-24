package com.bridge.record.model;

import javax.persistence.*;
import lombok.Data;

/**
* @ClassName : BizDictionary
* @Description : 
* @Author : zhenjf  
* @Date: 2021-01-21  
*/

/**
 * 字典表
 */
@Data
@Table(name = "rr_dictionary")
public class BizDictionary {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 类别
     */
    @Column(name = "`title`")
    private String title;

    /**
     * 字典键
     */
    @Column(name = "item_code")
    private String itemCode;

    /**
     * 字典值
     */
    @Column(name = "item_value")
    private String itemValue;
}