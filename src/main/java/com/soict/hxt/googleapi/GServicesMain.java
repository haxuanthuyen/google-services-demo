package com.soict.hxt.googleapi;

/**
 * Created by thuyenhx on 02/08/2016.
 */
public class GServicesMain {
    public static void main(String[] args) throws Exception {

        String email="haxuanthuyen225@gmail.com";
        String pass="khongcodau";
        GooglePlayAPI service = new GooglePlayAPI(email, pass, "123");
        service.login();
//        GooglePlay.AndroidCheckinResponse andCheck = service.checkin();
//        System.out.println(andCheck.getAndroidId());
//        System.out.println(service.getAndroidID());
    }
}
