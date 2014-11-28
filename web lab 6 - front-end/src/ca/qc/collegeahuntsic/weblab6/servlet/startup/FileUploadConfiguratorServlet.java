
package ca.qc.collegeahuntsic.weblab6.servlet.startup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import ca.qc.collegeahuntsic.weblab6.Constants;
import ca.qc.collegeahuntsic.weblab6.util.FileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUploadConfiguratorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Log LOGGER = LogFactory.getLog(FileUploadConfiguratorServlet.class);

    @Override
    public void init() throws ServletException {
        FileUpload.init(getServletConfig().getInitParameter(Constants.FILE_UPLOAD_CONFIGURATOR_FILE_PATH_PARAMETER_NAME),
            Integer.parseInt(getServletConfig().getInitParameter(Constants.FILE_UPLOAD_CONFIGURATOR_MAX_FILE_SIZE_PARAMETER_NAME)),
            Integer.parseInt(getServletConfig().getInitParameter(Constants.FILE_UPLOAD_CONFIGURATOR_MAX_MEM_SIZE_PARAMETER_NAME)));
        FileUploadConfiguratorServlet.LOGGER.info("FileUploadConfigurator initialized");
    }

    @Override
    public void destroy() {
        FileUploadConfiguratorServlet.LOGGER.info("FileUploadConfigurator destroyed");
    }
}
