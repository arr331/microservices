package com.auth.authserver.config;

import com.auth.authserver.config.keys.JwksKeys;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;

@Configuration
public class AuthorizationServerConfig {

  /**
   * First will be applied the OAuth2 security filters configuration.
   * In this configuration, I only indicate that all the failing request will be redirected to the /login page.
   */
  @Bean
  @Order(1)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http
            // Redirect to the login page when not authenticated from the
            // authorization endpoint
            .exceptionHandling(exceptions -> exceptions
                    .authenticationEntryPoint(
                            new LoginUrlAuthenticationEntryPoint("/login"))
            )
    ;

    return http.build();
  }

  /**
   * Then, will be applied the rest of the security filters.
   * Here, I indicate that all the endpoints require authentication and which is the login form.
   */
  @Bean
  @Order(2)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            // Form login handles the redirect to the login page from the
            // authorization server filter chain
            .formLogin(Customizer.withDefaults());
    return http.build();
  }

  /**
   * Configuration of the OAuth2 client.
   * Multiple clients can be configured. The current implementation stores them in memory, but a table
   * in the database can be used to store all the registered clients.
   */
  @Bean
  public RegisteredClientRepository registeredClientRepository() {
    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("client-id")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://backend-client:8080/login/oauth2/code/client-oidc")
            .redirectUri("http://backend-client:8080/authorized")
            .scope(OidcScopes.OPENID)
            .scope("message.read")
            .scope("message.write")
            .build();

    return new InMemoryRegisteredClientRepository(registeredClient);
  }

  /**
   * Acceptable URL of the authorization server
   */
  @Bean
  public ProviderSettings providerSettings() {
    return ProviderSettings.builder()
            .issuer("http://backend-auth:8079")
            .build();
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    RSAKey rsaKey = JwksKeys.generateRSAKey();
    JWKSet set = new JWKSet(rsaKey);
    return (j, sc) -> j.select(set);
  }
}
