package de.yougrowgroup.CollectronBackend.Constants;

import lombok.Getter;
import org.springframework.context.annotation.Bean;

public class SecurityConstants {

    @Getter
    private static final String JWT_KEY = "d89c5be447e7174947713b63c60ea96b73977ebd6ef60afc5e5f743d689d65fa"; //TODO change and make accessible via configuration before deployment
    @Getter
    private static final String JWT_HEADER = "Authorization";
    @Getter
    private static final String FRONTEND_ADDRESS = "http://localhost:4200";

}
