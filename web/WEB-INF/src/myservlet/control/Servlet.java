package myservlet.control;

import mybean.data.Bean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by lei02 on 2019/4/15.
 */
public class Servlet extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String dataBase = request.getParameter("dataBase");
        String tableName = request.getParameter("tableName");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        boolean boo = (dataBase==null || dataBase.length()==0) ||
                (tableName==null || tableName.length()==0) ||
                (user == null || user.length()==0) ||
                (password == null || password.length() == 0);
        if (boo) {
            fail(request, response, "查询失败");
        }

        HttpSession session = request.getSession();
        Connection conn = null;
        Bean pageBean = null;
        try {
            pageBean = (Bean)session.getAttribute("pageBean");
            if (pageBean == null) {
                pageBean = new Bean();
                session.setAttribute("pageBean", pageBean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            pageBean = new Bean();
            session.setAttribute("pageBean", pageBean);
        }
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(1);

        try {
            String url = "jdbc:mysql://127.0.0.1/" + dataBase
                    + "?user=root&password=021191&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url);
            Statement sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = sql.executeQuery("select * from " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnName = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnName[i] = metaData.getColumnName(i + 1);
            }
            pageBean.setColumnName(columnName);
            rs.last();
            int rowNumber = rs.getRow();
            String[][] tableRecord = new String[rowNumber][columnCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                for (int k = 0; k < columnCount; k++) {
                    tableRecord[i][k] = rs.getString(k + 1);
                }
                i++;
            }
            pageBean.setTableRecord(tableRecord);
            conn.close();
            response.sendRedirect("showPage.jsp");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        doPost(request, response);
    }

    public void fail(HttpServletRequest request, HttpServletResponse response, String  backName){
        try {
            PrintWriter out = response.getWriter();
            out.print("<html><body>");
            out.print("<h2>" + backName + "</h2>");
            out.print("返回");
            out.print("<a href='index.jsp'>输入正确信息</a>");
            out.print("</body></html>");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
