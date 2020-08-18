package com.diandian.framework.shiro;

import com.diandian.common.dto.AbstractCommonToken;
import com.diandian.common.enums.LoginTypeEnum;
import com.diandian.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@Slf4j
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("UserModularRealmAuthenticator doAuthenticate");

        AbstractCommonToken abstractCommonToken = (AbstractCommonToken) authenticationToken;
        LoginTypeEnum loginTypeEnum = abstractCommonToken.getLoginType();
        log.info("UserModularRealmAuthenticator-loginType:{}",loginTypeEnum.getRealmName());
        Realm currentRealm = null;
        Collection<Realm> realms = getRealms();
        for(Realm realm : realms){
            if(loginTypeEnum.getRealmName().equals(realm.getName())){
                currentRealm = realm;
                break;
            }
        }
        if(currentRealm==null){
            throw new BusinessException(HttpStatus.FORBIDDEN.getReasonPhrase(),HttpStatus.FORBIDDEN.value()+"");
        }
        //如果是多Realm
        //return doMultiRealmAuthentication(realms, authenticationToken);
        return doSingleRealmAuthentication(currentRealm,authenticationToken);
    }
}
