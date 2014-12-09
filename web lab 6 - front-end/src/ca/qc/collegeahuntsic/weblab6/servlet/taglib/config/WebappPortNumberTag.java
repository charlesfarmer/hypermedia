
package ca.qc.collegeahuntsic.weblab6.servlet.taglib.config;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;

import ca.qc.collegeahuntsic.weblab6.servlet.taglib.AbstractTag;

public class WebappPortNumberTag extends AbstractTag {
    /**
     * Default constructor.
     */
    public WebappPortNumberTag() {
        super();
    }

    /**
     * Prints out the web application port number.
     * 
     * @return {@link Tag#EVAL_PAGE}
     * @throws JspTagException If an error occurs
     */
    @Override
    public int doEndTag() throws JspTagException {
        try {
            getPageContext().getOut().write(Integer.toString(getPageContext().getRequest().getServerPort()));
        } catch(IOException iOException) {
            throw new JspTagException(iOException.getMessage());
        }
        return Tag.EVAL_PAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release() {
        super.release();
    }
}
