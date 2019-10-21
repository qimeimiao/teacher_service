package com.lss.teacher_manager.mybatis;

import java.io.Serializable;

public class PageParameter implements Serializable {
    private static final long serialVersionUID  = 1L;
    public static final int   DEFAULT_PAGE_SIZE = 10;
    private int               pageSize;
    private int               currentPage;
    private int               prePage;
    private int               nextPage;
    private int               totalPage;
    private int               totalCount;
    private boolean			  cancle=false;//是否需要计算总数
    

    public PageParameter() {
        this.currentPage = 1;
        this.pageSize = 10;
        this.nextPage = (this.currentPage + 1);
        this.prePage = (this.currentPage - 1);
    }



    public PageParameter(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.nextPage = (currentPage + 1);
        this.prePage = (currentPage - 1);
    }



    public int getCurrentPage() {
        return this.currentPage;
    }



    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        this.nextPage = (currentPage + 1);
        this.prePage = (currentPage - 1);
    }



    public int getPageSize() {
        return this.pageSize;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public int getPrePage() {
        return this.prePage;
    }



    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }



    public int getNextPage() {
        return this.nextPage;
    }



    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }



    public int getTotalPage() {
        return this.totalPage;
    }


    public int getTotalCount() {
        return this.totalCount;
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage=((int)totalCount/pageSize)+1;
    }



	public int getStart() {
		return (this.currentPage - 1 )*this.pageSize;
	}



	public boolean isCancle() {
		return cancle;
	}


	public void setCancle(boolean cancle) {
		this.cancle = cancle;
	}
	
}
