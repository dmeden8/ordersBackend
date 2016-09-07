package hr.ddcode.cafford.business;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hr.ddcode.cafford.data.entity.UserAccount;

public class UserPrincipal extends UserAccount implements UserDetails {

	private static final long serialVersionUID = -1790179767720596318L;

	public UserPrincipal(final UserAccount account) {
        super(account);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
