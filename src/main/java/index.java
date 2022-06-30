import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class index extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //获取同一个session对象
        HttpSession httpSession=req.getSession(false);
        //获取userName属性
        String userName=(String)httpSession.getAttribute("userName");
        Integer count=(Integer)httpSession.getAttribute("count");
        count+=1;
        httpSession.setAttribute("count",count);
        //设置传输的数据类型
        resp.setContentType("text/html;charset=utf8");
        resp.getWriter().write("<h3>欢迎"+userName+"<h3>"+count+"次");
    }
}
