package com.nwchecker.server.social;

import com.nwchecker.server.json.FacebookUserJson;

/**
 * Created by vkulistc on 6/12/2015.
 */
public interface FacebookTools {
    boolean loginFacebookUserByEmailAndNickname(String email, String nickname);
}
