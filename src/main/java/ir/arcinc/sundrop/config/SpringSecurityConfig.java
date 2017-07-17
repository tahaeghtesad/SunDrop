package ir.arcinc.sundrop.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableResourceServer
public class SpringSecurityConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService service) throws Exception {
        auth.userDetailsService(service);
    }

    @Bean
    @Autowired
    public AuthenticationProvider daoAuthenticationProvider(UserDetailsService service){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    @Autowired
    public ProviderManager authenticationProviderManager(AuthenticationProvider provider){
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(provider);
        return new ProviderManager(providers);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore, ClientDetailsService service){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(service));
        handler.setClientDetailsService(service);
        return handler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable();

        http.authorizeRequests().antMatchers("/oauth/**").permitAll().anyRequest().authenticated();
    }
}