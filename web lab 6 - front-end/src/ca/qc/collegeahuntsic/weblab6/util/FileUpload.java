
package ca.qc.collegeahuntsic.weblab6.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ca.qc.collegeahuntsic.weblab6.dto.MembreDTO;
import ca.qc.collegeahuntsic.weblab6.servlet.action.LoginMembreServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class FileUpload {

    private static final Log LOGGER = LogFactory.getLog(LoginMembreServlet.class);

    private static String filePath;

    private static int maxFileSize;

    private static int maxMemSize;

    private FileUpload() {
        super();
    }

    public static String getFilePath() {
        return FileUpload.filePath;
    }

    public static void setFilePath(String filePath) {
        FileUpload.filePath = filePath;
    }

    public static synchronized void init(String fileUploadPath,
        int fileSize,
        int memSize) {
        setFilePath(fileUploadPath);
        setMaxFileSize(fileSize);
        setMaxMemSize(memSize);
        FileUpload.LOGGER.info("FileUpload filePath initialized");
    }

    @SuppressWarnings("rawtypes")
    public static boolean uploadFilesFromForm(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart) {
            FileUpload.LOGGER.warn("No file uploaded");
            return false;
        }
        File file;
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
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    FileUpload.LOGGER.warn("#\\\\FILE ITEM\nfield name : "
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
                        file = (new File(path));
                    } else {
                        String path = getFilePath()
                            + membre.getUsername()
                            + "\\"
                            + fileName.substring(fileName.lastIndexOf("\\") + 1);
                        file = (new File(path));
                    }
                    file.getParentFile().mkdirs();
                    fi.write(file);
                    FileUpload.LOGGER.warn("\n\nfile status : UPLOADED\n\n");
                }
            }
            return true;
        } catch(Exception exception) {
            FileUpload.LOGGER.error(ExceptionUtils.getStackTrace(exception));
            return false;
        }
    }

    public static int getMaxFileSize() {
        return FileUpload.maxFileSize;
    }

    public static void setMaxFileSize(int maxFileSize) {
        FileUpload.maxFileSize = maxFileSize;
    }

    public static int getMaxMemSize() {
        return FileUpload.maxMemSize;
    }

    public static void setMaxMemSize(int maxMemSize) {
        FileUpload.maxMemSize = maxMemSize;
    }
}
