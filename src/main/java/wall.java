//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

class Data {
    public String from;
    public String to;
    public String mes;

    Data() {
    }

    public String toString() {
        return "Data{from='" + this.from + '\'' + ", to='" + this.to + '\'' + ", mes='" + this.mes + '\'' + '}';
    }
}

@WebServlet({"/wall"})
public class wall extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    public wall() {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBUtils.createDataSource();
        List<Data> list = DBUtils.load();

        String json=objectMapper.writeValueAsString(list);
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write(json);
        System.out.println(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Data data = objectMapper.readValue(req.getInputStream(), Data.class);
        DBUtils.createDataSource();
        DBUtils.save(data);
        resp.setContentType("application/json;charset=utf8");
        resp.getWriter().write("{\"ok\":true}");
    }
}
