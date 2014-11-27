
package ca.qc.collegeahuntsic.weblab6.servlet.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static final String UPLOAD_STATUS_ATTRIBUTE_NAME = "uploadSuccessful";

    private static final String FILEPATH_PARAMETER_NAME = "uploadPath";

    private static final String FORWARD_RESOURCE_SUCCESSFUL_UPLOAD = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final String FORWARD_RESOURCE_FAILED_UPLOAD = "/WEB-INF/jsp/viewTemplate/viewIndex.jsp";

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    private String filePath;

    private boolean isMultipart;

    private int maxFileSize = 500 * 1024;

    private int maxMemSize = 40 * 1024;

    private File file;

    @Override
    public void init() throws ServletException {
        setFilePath(getServletConfig().getInitParameter(FileUploadServlet.FILEPATH_PARAMETER_NAME));
        FileUploadServlet.LOGGER.info("init parameter loaded : "
            + getFilePath());
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        setMultipart(ServletFileUpload.isMultipartContent(request));
        if(!isMultipart()) {
            FileUploadServlet.LOGGER.warn("No file uploaded");
            request.setAttribute(FileUploadServlet.UPLOAD_STATUS_ATTRIBUTE_NAME,
                Boolean.FALSE);
            getServletContext().getRequestDispatcher(FileUploadServlet.FORWARD_RESOURCE_FAILED_UPLOAD).forward(request,
                response);
            //end
        }
        MembreDTO membre = (MembreDTO) request.getSession().getAttribute(LoginMembreServlet.MEMBRE_ATTRIBUTE_NAME);
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setSizeThreshold(getMaxMemSize());
        diskFileItemFactory.setRepository(new File("c:\\temp"));
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setSizeMax(getMaxFileSize());
        try {
            List fileItems = servletFileUpload.parseRequest(request);
            Iterator i = fileItems.iterator();
            while(i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if(!fi.isFormField()) {
                    FileUploadServlet.LOGGER.warn("### FILE ITEM ###");
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    FileUploadServlet.LOGGER.warn("field name : "
                        + fieldName
                        + "\nfile name : "
                        + fileName
                        + "\ncontent type : "
                        + contentType
                        + "\nis in memory : "
                        + isInMemory
                        + "\nsize in bytes : "
                        + sizeInBytes);
                    if(fileName.lastIndexOf("\\") >= 0) {
                        String path = getFilePath()
                            + membre.getUsername()
                            + "\\"
                            + fileName.substring(fileName.lastIndexOf("\\"));
                        setFile(new File(path));
                    } else {
                        String path = getFilePath()
                            + membre.getUsername()
                            + "\\"
                            + fileName.substring(fileName.lastIndexOf("\\") + 1);
                        setFile(new File(path));
                    }
                    getFile().getParentFile().mkdirs();
                    fi.write(getFile());
                    FileUploadServlet.LOGGER.warn("\n\nfile status : UPLOADED\n\n");
                }
            }
            request.setAttribute(FileUploadServlet.UPLOAD_STATUS_ATTRIBUTE_NAME,
                Boolean.TRUE);
            getServletContext().getRequestDispatcher(FileUploadServlet.FORWARD_RESOURCE_SUCCESSFUL_UPLOAD).forward(request,
                response);
        } catch(Exception exception) {
            FileUploadServlet.LOGGER.warn(ExceptionUtils.getStackTrace(exception));
        }
    }

    public boolean isMultipart() {
        return this.isMultipart;
    }

    public void setMultipart(boolean isMultipart) {
        this.isMultipart = isMultipart;
    }

    public int getMaxFileSize() {
        return this.maxFileSize;
    }

    public void setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public int getMaxMemSize() {
        return this.maxMemSize;
    }

    public void setMaxMemSize(int maxMemSize) {
        this.maxMemSize = maxMemSize;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
