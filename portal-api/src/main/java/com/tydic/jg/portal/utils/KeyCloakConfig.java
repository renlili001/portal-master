package com.tydic.jg.portal.utils;

import com.tydic.jg.authentication.AutoConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.ws.rs.core.Response;
import java.util.Collections;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@ConfigurationProperties
@AutoConfigureAfter(AutoConfiguration.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@Slf4j
//@ConditionalOnBean(type = "org.keycloak.adapters.springboot.KeycloakSpringBootProperties")
public class KeyCloakConfig {
    @Bean
    Keycloak keycloak(KeycloakSpringBootProperties keycloakSpringBootProperties,
                      @Value("${admin.user}") String user,
                      @Value("${admin.password}") String password){
        return KeycloakBuilder.builder()
                .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
                .realm(keycloakSpringBootProperties.getRealm())
                .username(user)
                .password(password)
                .clientId(keycloakSpringBootProperties.getResource())
                .clientSecret(keycloakSpringBootProperties.getCredentials().get("secret").toString()).build();
    }

    @Bean
    RealmResource realmResource(Keycloak keycloak, KeycloakSpringBootProperties keycloakSpringBootProperties){
        return keycloak.realm(keycloakSpringBootProperties.getRealm());
    }
    @Bean
    RolesResource rolesResource(RealmResource realmResource){
        return realmResource.roles();
    }
    @Bean
    UsersResource usersResource(RealmResource realmResource){
        return realmResource.users();
    }
    @Bean
    ClientResource productResource(RealmResource realmResource){
        final ClientsResource clientsResource = realmResource.clients();
        final ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setId("product");
        clientRepresentation.setName("产品");
        final Response response = clientsResource.create(clientRepresentation);
        final int status = response.getStatus();
        if(status == Response.Status.CREATED.getStatusCode()){
            log.info("创建client[产品]成功");
        }else if(status ==  Response.Status.CONFLICT.getStatusCode()){
            log.info("client[产品]已经存在");
        }
        return clientsResource.get("product");

    }
    public static void main(String[] args) {

        final Keycloak ispeco = KeycloakBuilder.builder()
                .serverUrl("http://172.168.1.131:8180/auth")
                .realm("Ispeco")
                .username("admin")
                .password("admin")
                .clientId("Ispeco-backend")
                .clientSecret("3aa5601e-eeb6-460e-b38a-b5537af484dc").build();
        final RealmResource realmResource = ispeco.realm("Ispeco");
        final UsersResource users = realmResource.users();
        final UserRepresentation user1 = users.search("user1").get(0);


        // 取得指定用户的 roleMappingResource
        RoleMappingResource roleMappingResource = realmResource
                .users()
                .get(user1.getId())
                .roles();

        // 为用户分配Realm角色
        final RolesResource roles = realmResource
                .roles();
        RoleRepresentation realmRole = roles
                .get("product_role_9")
                .toRepresentation();

        roleMappingResource.realmLevel().add(Collections.singletonList(realmRole));


    }
}
