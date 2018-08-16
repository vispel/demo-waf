package com.ks.filter.handler;

import javax.servlet.FilterConfig;

public class FilterLocalLog {

    private FilterConfig filterConfig;
    protected boolean isDebug;

    public FilterLocalLog(FilterConfig filterConfig, boolean isDebug) {
        this.filterConfig = filterConfig;
        this.isDebug = isDebug;
    }
    protected void logLocal(final String msg) {
        logLocal(msg, null);
    }

    protected void logLocal(final String paramName, Object o) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] msg = paramName.split("(?=\\p{Upper})");
        int iMax = msg.length - 1;
        for (int i = 0; i<iMax ; i++) {
            stringBuilder.append(String.valueOf(msg[i]) + ' ');
            if (i == iMax) stringBuilder.append(": ");
        }
        logLocal(stringBuilder.toString() + o.toString());
    }

    protected void logLocal(final String msg, final Exception e) {
        if (e != null) {
            if (filterConfig != null && filterConfig.getServletContext() != null) filterConfig.getServletContext().log(msg, e);
            else {
                System.out.println(msg+": "+e);
            }
        } else {
            if (filterConfig != null && filterConfig.getServletContext() != null) filterConfig.getServletContext().log(msg);
            else {
                System.out.println(msg);
            }
        }
    }
}