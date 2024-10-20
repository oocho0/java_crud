package my.oochoo.web.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.oochoo.jdbc.ApplicationFactory;
import my.oochoo.web.Const.Path;
import my.oochoo.web.model.Page;
import my.oochoo.web.model.Post;
import my.oochoo.web.service.PostService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet({Path.HOME, Path.POST})
public class PostController extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(PostController.class.getName());
    private final PostService postService = ApplicationFactory.getPostService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String reqPath = req.getServletPath();
        Map<String, String> params = getQueryParams(req);

        String resPath = getPostListPage(req, reqPath, params);
        if (StringUtils.isBlank(resPath)) {
            resPath = getPostRegisterPage(req, reqPath, params);
        }
        if (StringUtils.isBlank(resPath)) {
            resPath = getPostModifyPage(req, reqPath, params);
        }

        req.setAttribute("jsp", resPath + Path.JSP);
        req.setAttribute("css", resPath + Path.CSS);
        req.setAttribute("js", resPath + Path.JS);

        try {
           req.getRequestDispatcher(Path.TEMPLATE).forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.warning(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private String getPostListPage(HttpServletRequest req,  String reqPath, Map<String, String> params) {
        if (StringUtils.equals(reqPath, Path.POST) || StringUtils.equals(reqPath, Path.HOME)) {
            Page page = new Page();
            List<Post> postList = postService.getAllPostList(params, page);
            page.setTotalPageCount(postList.size());
            req.setAttribute("postList", postList);
            req.setAttribute("page", page);
            return Path.POST + Path.LIST;
        }
        return null;
    }

    private String getPostRegisterPage(HttpServletRequest req, String reqPath, Map<String, String> params) {
        if (StringUtils.equals(reqPath, Path.POST + Path.REGISTER)) {
            return Path.POST + Path.REGISTER;
        }
        return null;
    }

    private String getPostModifyPage(HttpServletRequest req, String reqPath, Map<String, String> params) {

        if (StringUtils.equals(reqPath, Path.POST + Path.MODIFY)) {
            return Path.POST + Path.MODIFY;
        }
        return null;
    }

    private Map<String, String> getQueryParams(HttpServletRequest req) {
        String queryString = req.getQueryString();
        Map<String, String> params = new HashMap<>();

        if (StringUtils.isNotBlank(queryString)) {
            String[] querys = queryString.split("&");
            for (String query : querys) {
                String[] param = query.split("=");
                params.put(param[0], param[1]);
            }
        }
        return params;
    }
}
