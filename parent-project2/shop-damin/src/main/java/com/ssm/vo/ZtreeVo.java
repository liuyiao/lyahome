package com.ssm.vo;

/**
 * 用来做Ztree树显示的
 * 	{ id:113, pId:1, name:"呵呵呵呵呵 1-1",checked:true, open:true},
 */
public class ZtreeVo {
    private Long id;
    private Long pId;
    private String name;
    private Boolean checked;
    private Boolean open;

    @Override
    public String toString() {
        return "ZtreeVo{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", checked=" + checked +
                ", open=" + open +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
