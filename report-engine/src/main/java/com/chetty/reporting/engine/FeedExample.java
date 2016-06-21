/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chetty.reporting.engine;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;
import java.net.URL;

/**
 *
 * @author totoland
 */
public class FeedExample {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://www2.bot.or.th/RSS/fxrates/fxrate-all.xml");

        Feed feed = FeedParser.parse(url);

        int items = feed.getItemCount();
        for (int i = 0; i < items; i++) {
            FeedItem item = feed.getItem(i);
            System.out.println("targetCurrency: " + item.getElement("http://centralbanks.org/cb/1.0/", "targetCurrency").getValue());
            System.out.println("value: " + item.getElement("http://centralbanks.org/cb/1.0/", "value").getValue());
        }

    }
}
