/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.theme;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Totoland
 */
@ManagedBean
@SessionScoped
public class ThemeSwitcherController implements Serializable{
    private static final long serialVersionUID = -1841823368626728400L;
    private Map<String, String> themes; 
    private String theme;
    
    public ThemeSwitcherController() {
        
        this.theme = "adamantium";
        themes = new HashMap<String, String>();  
        themes.put("Aristo", "aristo");  
        themes.put("Black-Tie", "black-tie");  
        themes.put("Bootstrap", "bootstrap");
        themes.put("Blitzer", "blitzer");  
        themes.put("Bluesky", "bluesky");  
        themes.put("Casablanca", "casablanca");  
        themes.put("Cupertino", "cupertino");  
        themes.put("Dark-Hive", "dark-hive");  
        themes.put("Dot-Luv", "dot-luv");  
        themes.put("Eggplant", "eggplant");  
        themes.put("Excite-Bike", "excite-bike");  
        themes.put("Flick", "flick");  
        themes.put("Glass-X", "glass-x");  
        themes.put("Hot-Sneaks", "hot-sneaks");  
        themes.put("Humanity", "humanity");  
        themes.put("Le-Frog", "le-frog");
        themes.put("MetroUI", "metroui");
        themes.put("Midnight", "midnight");  
        themes.put("Mint-Choc", "mint-choc");  
        themes.put("Overcast", "overcast");  
        themes.put("Pepper-Grinder", "pepper-grinder");  
        themes.put("Redmond", "redmond");  
        themes.put("Rocket", "rocket");  
        themes.put("Sam", "sam");  
        themes.put("Smoothness", "smoothness");  
        themes.put("South-Street", "south-street");  
        themes.put("Start", "start");  
        themes.put("Sunny", "sunny");  
        themes.put("Swanky-Purse", "swanky-purse");  
        themes.put("Trontastic", "trontastic");  
        themes.put("UI-Darkness", "ui-darkness");  
        themes.put("UI-Lightness", "ui-lightness");  
        themes.put("Vader", "vader");
    }
    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * @return the themes
     */
    public Map<String, String> getThemes() {
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }
}
