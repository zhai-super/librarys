package com.zyh.utils;


import java.util.List;

/*分页信息实体类*/
public class PageBean<T> {
    private List<T> selectUserDataA;//查询所得到的数据总条数
    private List<T> allUserData;//所用用户信息
    private Integer totalCount;//满足条件的总数据条数
    private Integer pageSize = 3;//每页展示3条数据
    private Integer currentPage = 1;//当前展示的页数,默认当前页为1
    private Integer totalPage;//总页数
    private boolean hasUpPage;//是否有上一页
    private boolean hasDownPage;//是否有下一页
    //得到条件查询后所有的数据
    public List<T> getSelectUserDataA() {
        return selectUserDataA;
    }

    public void setSelectUserDataA(List<T> selectUserData) {
        this.selectUserDataA = selectUserData;
    }
    public List<T> getAllUserData() {
        return allUserData;
    }

    public void setAllUserData(List<T> allUserData) {
        this.allUserData = allUserData;
    }

    //每页展示的条数规定只有5条,所以没有set方法
    public Integer getPageSize() {
        return pageSize;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(Integer currentPage) {
        //当前页数加上处理
        if (currentPage <= 0) {
            this.currentPage = 1;
        } else if (currentPage >= getTotalPage()) {
            this.currentPage = getTotalPage();
        } else {
            this.currentPage = currentPage;
        }
    }

    public Integer getTotalPage() {
        //总页数计算公式
        return (this.getTotalCount() + this.getPageSize() - 1) / this.getPageSize();
    }
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        //满足查询条件的数据总条数
        this.totalCount = totalCount;
    }

    public boolean isHasUpPage() {
        if (getCurrentPage() == 1) {
            return false;
        }
        return true;
    }
    public boolean isHasDownPage() {
        if (getCurrentPage() == totalPage) {
            return false;
        }
        return true;
    }

}
