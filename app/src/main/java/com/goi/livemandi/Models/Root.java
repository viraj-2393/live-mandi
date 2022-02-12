package com.goi.livemandi.Models;

import java.util.ArrayList;
import java.util.Date;

public class Root {
    public int created;
    public int updated;
    public Date created_date;
    public Date updated_date;
    public ArrayList<String> org;
    public String org_type;
    public String source;
    public String title;
    public String desc;
    public String status;
    public int total;
    public int count;
    public String limit;
    public String offset;
    public ArrayList<Record> records;

    public int getCreated() {
        return created;
    }

    public int getUpdated() {
        return updated;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public ArrayList<String> getOrg() {
        return org;
    }

    public String getOrg_type() {
        return org_type;
    }

    public String getSource() {
        return source;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public String getLimit() {
        return limit;
    }

    public String getOffset() {
        return offset;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }
}
