package com.aifeng.mgr.permission;

import com.aifeng.constants.Constants;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
import java.util.Map;

/**
 * @author cosco
 */
public class PermissionTag extends TagSupport {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 菜单代码
     */
    private String menuCode;

    /**
     * 满足操作的权限值
     */
    private int action;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }


    @Override
    public int doStartTag() throws JspException {
        // TODO Auto-generated method stub
        if (hasPermission()) {
            return EVAL_PAGE;
        } else {
            return SKIP_BODY;
        }
    }

    // 验证是否有权限
    private boolean hasPermission() {
        HttpSession session = pageContext.getSession();
        List<Map<String, Object>> permissions = (List<Map<String, Object>>) session.getAttribute(Constants.SESSION_PERMISSIONS);
        int actionVal = 0;

        for (Map<String, Object> map : permissions) {
            if (map.get("menu_code").toString().equals(this.menuCode)) {
                actionVal = Integer.valueOf(map.get("action_val").toString());
                break;
            }
        }
        return (actionVal & action) > 0 ? true : false;
    }
}