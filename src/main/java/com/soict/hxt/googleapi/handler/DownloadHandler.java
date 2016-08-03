package com.soict.hxt.googleapi.handler;

import com.soict.hxt.googleapi.googleplay.GPlayBuilder;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.mime.MIME;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.IO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class DownloadHandler extends AbstractHandler {
    private static final Logger logger = LoggerFactory.getLogger("CrawlerLogs");

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String packageName = baseRequest.getParameter("id");
//        String packageName = "com.cleanmaster.mguard";
        String fileName = packageName.replaceAll("\\.", "_") + ".apk";
        response.setContentType("application/vnd.android.package-archive");
        response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        logger.info("start download apk " + fileName);

        GPlayBuilder playBuilder = GPlayBuilder.getInstance();
        InputStream downloadStream = playBuilder.downloadApkFile(packageName);

        byte[] buffer = new byte[1024];
        for (int k = 0; (k = downloadStream.read(buffer)) != -1;) {
            response.getOutputStream().write(buffer, 0, k);
        }
        response.getOutputStream().flush();
    }
}
