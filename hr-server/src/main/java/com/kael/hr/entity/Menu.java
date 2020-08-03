package com.kael.hr.entity;

public class Menu {
    private Integer id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String iconCls;
    private Byte keepAlive;
    private Byte requireAuth;
    private Integer parentId;
    private Byte enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Byte getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Byte keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Byte getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Byte requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }
}
