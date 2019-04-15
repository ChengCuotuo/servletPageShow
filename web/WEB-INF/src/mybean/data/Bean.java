package mybean.data;

import java.util.Date;

/**
 * Created by lei02 on 2019/4/15.
 */
public class Bean {
    private String number;
    private String name;
    private Date madeTime;
    private double price;
    private int pageSize;
    private int totalPages;
    private int currentPage;
    private String[][] tableRecord;
    private String[] columnName;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[][] getTableRecord() {
        return tableRecord;
    }

    public void setTableRecord(String[][] tableRecord) {
        this.tableRecord = tableRecord;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setName(String name) {
        this.name = name;

    }

     public Date getMadeTime() {
        return madeTime;

    }

    public void setMadeTime(Date madeTime) {
        this.madeTime = madeTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
