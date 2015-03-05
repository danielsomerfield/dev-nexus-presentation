package phonebook.service;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class LoginService {

    public Principal login(final String username, final String password) throws LoginFailedException {
        throw new UnsupportedOperationException(); // TODO
    }

    public Optional<Principal> loginOptional(final String username, final String password) {
        throw new UnsupportedOperationException(); // TODO
    }

    public Either<LoginFailure, Principal> loginEither (final String username, final String password) {
        throw new UnsupportedOperationException(); // TODO
    }

    public Either<Throwable, Principal> loginResponse (final String username, final String password) {
        throw new UnsupportedOperationException(); // TODO
    }


    public static class Either<Ex, T> {
        public Optional<Ex> left() {
            throw new UnsupportedOperationException("NYI");
        }

        public Optional<T> right() {
            throw new UnsupportedOperationException("NYI");
        }
    }

    public enum LoginFailure {
        INVALID_CREDENTIALS,
        ACCOUNT_LOCKED

    }

    public void loginCallback(final String username, final String password,
                                   Consumer<Principal> loginSuccess, Consumer<LoginFailure> loginFailure) {
        throw new UnsupportedOperationException(); // TODO
    }

    //Cases
    //1. Login success
    //2. Invalid credentials
    //3. Account locked
    //4. Failure to access downstream resource
    //5.

    @Value
    @EqualsAndHashCode(callSuper=false)
    public static class LoginFailedException extends Throwable {
        private final LoginFailure loginFailure;
    }
}
