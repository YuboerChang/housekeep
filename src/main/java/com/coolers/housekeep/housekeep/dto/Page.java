package com.coolers.housekeep.housekeep.dto;

import com.coolers.housekeep.housekeep.util.BaseUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {
    private Integer pageSize;
    private Integer pageNum;
    private Integer offset;

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.offset = (this.pageNum - 1) * this.pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        if (BaseUtil.isNotEmptyMathObject(this.pageSize)) {
            // mysql 偏移量从0页算起
            this.offset = (this.pageNum - 1) * this.pageSize;
        }
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        if (BaseUtil.isNotEmptyMathObject(this.pageNum)) {
            // mysql 偏移量从0页算起
            this.offset = (this.pageNum - 1) * this.pageSize;
        }
    }
}
