package com.aifeng.util;

import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.constants.Status;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by pro on 17-5-8.
 */
public class EnumTag extends SimpleTagSupport {

    private InsuranceType type;
    private Status status;

    public void setType(InsuranceType type) {
        this.type = type;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        JspWriter out = getJspContext().getOut();
        if(type != null) {
            out.write("type: " + type.getType());
        } else if(status != null) {
            out.write("status: " + status.getStatus());
        }


    }
}
