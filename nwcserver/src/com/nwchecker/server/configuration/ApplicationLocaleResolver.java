package com.nwchecker.server.configuration;

import com.nwchecker.server.model.User;
import com.nwchecker.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ApplicationLocaleResolver extends CookieLocaleResolver {

    @Autowired
    UserService userService;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (localeCookieExist(request)) return super.resolveLocale(request);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Locale favLocale = getFavLocale(username);
        return (favLocale == null) ? super.resolveLocale(request)
                                   : favLocale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        super.setLocale(request, response, locale);

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        saveFavLocale(userName, locale);
    }

    private void saveFavLocale(String userName, Locale locale){
        if (userName.equals("anonymousUser")) return;

        User user = userService.getUserByUsername(userName);
        user.setFavLocale(locale.toLanguageTag());
        userService.updateUser(user);
    }

    private Locale getFavLocale(String username){
        if (username.equals("anonymousUser")) return null;

        User user = userService.getUserByUsername(username);
        return (user.getFavLocale() == null) ? null
                                             : Locale.forLanguageTag(user.getFavLocale());
    }

    private boolean localeCookieExist(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(getCookieName())) return true;
        }
        return false;
    }

    public Locale getLocaleFromCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(getCookieName())){
                return Locale.forLanguageTag(cookie.getValue());
            }
        }
        return null; //if cookie not found
    }
}
