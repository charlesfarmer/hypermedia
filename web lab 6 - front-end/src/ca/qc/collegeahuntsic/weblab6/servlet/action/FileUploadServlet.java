
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.util.FileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String UPLOAD_STATUS_ATTRIBUTE_NAME = "uploadSuccessful";

    private static final String FORWARD_RESOURCE_SUCCESSFUL_UPLOAD = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final String FORWARD_RESOURCE_FAILED_UPLOAD = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        boolean fileUploadSuccessful = FileUpload.uploadFilesFromForm(request);
        if(fileUploadSuccessful) {
            FileUploadServlet.LOGGER.info("file uploaded");
            request.setAttribute(FileUploadServlet.UPLOAD_STATUS_ATTRIBUTE_NAME,
                Boolean.TRUE);
            request.getRequestDispatcher(FileUploadServlet.FORWARD_RESOURCE_SUCCESSFUL_UPLOAD).forward(request,
                response);
        } else {
            FileUploadServlet.LOGGER.info("file upload failed");
            request.setAttribute(FileUploadServlet.UPLOAD_STATUS_ATTRIBUTE_NAME,
                Boolean.FALSE);
            request.getRequestDispatcher(FileUploadServlet.FORWARD_RESOURCE_FAILED_UPLOAD).forward(request,
                response);
        }
    }
}
