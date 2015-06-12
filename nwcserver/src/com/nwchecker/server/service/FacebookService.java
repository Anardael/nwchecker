package com.nwchecker.server.service;

import com.nwchecker.server.json.FacebookUserJson;

/**
 * Created by vkulistc on 6/12/2015.
 */
public interface FacebookService {
    boolean checkFacebookJsonByEmailAndNickname(String email, String nickname);
}
