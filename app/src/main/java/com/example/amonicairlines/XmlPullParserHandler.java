package com.example.amonicairlines;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlPullParserHandler {
    private List<Service> services = new ArrayList<Service>();
    private Service service;
    private String text;

    public List<Service> getServices() {
        return services;
    }

    public List<Service> parse(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("service")) {
                            service = new Service();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("service")) {
                            services.add(service);
                        } else if (tagName.equalsIgnoreCase("name")) {
                            service.setName(text);
                        } else if (tagName.equalsIgnoreCase("cost")) {
                            if (text.equalsIgnoreCase("0")){
                                text = "free";
                            }
                            service.setCost(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return services;
    }
}
