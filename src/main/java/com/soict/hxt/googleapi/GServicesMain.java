package com.soict.hxt.googleapi;

import com.soict.hxt.googleapi.googleplay.GPlayBuilder;
import com.soict.hxt.googleapi.handler.DownloadHandler;
import com.soict.hxt.googleapi.handler.SearchHandler;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class GServicesMain {
    public static void main(String[] args) throws Exception {
        GPlayBuilder playBuilder = GPlayBuilder.getInstance();

        try {
            Server server = new Server(6677);
            ContextHandler context = new ContextHandler("/index.html");
            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setDirectoriesListed(true);
            resource_handler.setWelcomeFiles(new String[]{"asset/js/recommend.html"});
            resource_handler.setResourceBase(".");
            context.setHandler(resource_handler);

            ContextHandler contextSearch = new ContextHandler("/search");
            contextSearch.setHandler(new SearchHandler());

            ContextHandler contextDownload = new ContextHandler("/download");
            contextDownload.setHandler(new DownloadHandler());
            MimeTypes downloadTypes = new MimeTypes();
            downloadTypes.addMimeMapping("apk", "application/vnd.android.package-archive");
            downloadTypes.addMimeMapping(".apk", "application/vnd.android.package-archive");
            contextDownload.setMimeTypes(downloadTypes);

            ContextHandlerCollection contexts = new ContextHandlerCollection();
            contexts.setHandlers(new Handler[] { context, contextSearch, contextDownload});
            server.setHandler(contexts);

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
