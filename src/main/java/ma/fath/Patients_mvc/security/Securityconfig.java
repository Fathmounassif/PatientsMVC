package ma.fath.Patients_mvc.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ma.fath.Patients_mvc.security.sevice.UserDetailsServiceImpl;

@Configuration //la premier class qui démarer.
@EnableWebSecurity//activer la securité web.
public class Securityconfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* // méthode 1
        String encodedPWD=passwordEncoder.encode("1234"); //encoder par bcrypte
        System.out.println(encodedPWD);
        auth.inMemoryAuthentication() // sauf users de memoire
                .withUser("user1").password(encodedPWD).roles("USER");
        auth.inMemoryAuthentication() // sauf users de memoire
                .withUser("user2").password(passwordEncoder.encode("1111")).roles("USER");
        auth.inMemoryAuthentication() // sauf users de memoire
                .withUser("admin").password(passwordEncoder.encode("2345")).roles("USER","ADMIN");
//noop:ne changer pas le mode passe(hachage).*/

             //méthode 2
        /*  auth.jdbcAuthentication() //base de données.
                  .dataSource(dataSource) //datasource courant.
                  .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
                  //soring security va executer cette requet directement apres login et mp.
                  .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                  .rolePrefix("ROLE_")
                  .passwordEncoder(passwordEncoder);*/
        //méthode3:
        auth.userDetailsService(userDetailsService) ;


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        http.authorizeRequests().antMatchers("/").permitAll();// nécissite pas d'autentifier.
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
          http.exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();//droit d'acces("tous les requet http nécissite un authentification.").
  
    }

}
