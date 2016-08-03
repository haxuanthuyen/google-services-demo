package com.soict.hxt.googleapi.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soict.hxt.googleapi.bo.SearchInfo;
import com.soict.hxt.googleapi.googleplay.GPlayBuilder;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class SearchHandler extends AbstractHandler {
    private static final Logger logger = LoggerFactory.getLogger("CrawlerLogs");

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        String text = baseRequest.getParameter("text");
        logger.info("search for " + text);
        GPlayBuilder playBuilder = GPlayBuilder.getInstance();
        List<SearchInfo> lstSearch = playBuilder.searchApp(text);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();

        String json = gson.toJson(lstSearch);
        logger.info(json);
        response.getWriter().println(json);

    }
}
