package hr.ddcode.cafford.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hr.ddcode.cafford.data.entity.UserAccount;
import hr.ddcode.cafford.data.repository.userAccount.UserAccountRepository;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserAccountRepository accRepo;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.length() < 3) {
            throw new UsernameNotFoundException(
                    String.format("Username [ %s ] cannot be null or less than 3 characters!", username));
        }

        UserAccount userAccount = accRepo.findByUsername(username);

        if (userAccount == null) {
            throw new UsernameNotFoundException(
                    String.format("Username [ %s ] does not exist!", username));
        }

        return new UserPrincipal(userAccount);
    }

}
