package com.lss.teacher_manager.interceptors.shiro.common;

public class ShiroProperties {

    private String anonUrl;

    private String managerAnonUrl;

    public String getManagerAnonUrl() {
        return managerAnonUrl;
    }

    public void setManagerAnonUrl(String managerAnonUrl) {
        this.managerAnonUrl = managerAnonUrl;
    }

    /**
     * token默认有效时间 1天
     */
    private Long jwtTimeOut = 86400L;

    public String getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(String anonUrl) {
        this.anonUrl = anonUrl;
    }

    public Long getJwtTimeOut() {
        return jwtTimeOut;
    }

    public void setJwtTimeOut(Long jwtTimeOut) {
        this.jwtTimeOut = jwtTimeOut;
    }
}
