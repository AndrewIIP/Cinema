package controller.command;

import model.entity.Movie;
import model.entity.User;
import model.services.MovieService;
import model.util.Cons;
import model.util.Languages;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddMovie implements Command {
    private static final int MEMORY_THRESHOLD = 1024 * 1024;
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 5;
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private static final String UPLOAD_DIRECTORY = "pic\\playbill";

    private String movieNameEng;
    private String movieNameUkr;
    private String pictureName;

    private MovieService movieService;

    public AddMovie(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User curUser = (User) request.getSession().getAttribute(Cons.SESSION_USER);
        Optional<Object> role = Optional.ofNullable(curUser.getRole());
        String localeTag = Optional.ofNullable((String) request.getSession().getAttribute(Cons.CUR_LANG)).orElse("en");
        Locale locale = Locale.forLanguageTag(Languages.isLangOrGetDefault(localeTag));
        ResourceBundle rsBundle = ResourceBundle.getBundle(Cons.LOCAL_RB_BASE_NAME, locale);

        String outUrlOK = "redirect:" + request.getContextPath() + request.getServletPath() + "/now_playing" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());
        String outUrlInvalid = "forward:/WEB-INF/common/oper_fail.jsp" +
                (request.getQueryString() == null ? "" : "?" + request.getQueryString());

        try{
            processPictureFromReq(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(invalidData(movieNameEng, movieNameUkr)){
            request.setAttribute(Cons.MESSAGE, rsBundle.getString("wrong.data"));
            return outUrlInvalid;
        }
        Movie movieToDB = createLazyMovie(pictureName);
        movieService.createMovie(movieToDB, movieNameEng, movieNameUkr);


        return outUrlOK;
    }

    private void processPictureFromReq(HttpServletRequest request) throws Exception {
        if (ServletFileUpload.isMultipartContent(request)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);
            String uploadPath = request.getServletContext().getRealPath("")
                    /*+ File.separator*/ + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        pictureName = fileName;
                    } else {
                        if (item.getFieldName().equals(Cons.MOVIE_NAME_ENG_PARAM)) {
                            movieNameEng = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                        } else if (item.getFieldName().equals(Cons.MOVIE_NAME_UKR_PARAM)) {
                            movieNameUkr = new String(item.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                        }
                    }
                }
            }
        }
    }

    private boolean invalidData(String movieNameEng, String movieNameUkr) {
        return !Optional.ofNullable(movieNameEng).isPresent() ||
                !Optional.ofNullable(movieNameUkr).isPresent();
    }

    private Movie createLazyMovie(String picUrl) {
        Movie movie = new Movie();
        movie.setPicUrl(picUrl);
        return movie;
    }
}
