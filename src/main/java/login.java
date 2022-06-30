import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String userName=req.getParameter("userName");
        String password=req.getParameter("password");
        if("zhangsan".equals(userName)&&"123".equals(password))
        {
            HttpSession httpSession=req.getSession(true);
            //初始化
            httpSession.setAttribute("userName",userName);
            httpSession.setAttribute("password",password);
            httpSession.setAttribute("count",0);
            //重定向
            resp.sendRedirect("index");
        }
        else{
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("登录失败");
        }

    }
}
