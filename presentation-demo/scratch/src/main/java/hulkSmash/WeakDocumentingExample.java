package hulkSmash;

import lombok.AllArgsConstructor;

public class WeakDocumentingExample {

    @AllArgsConstructor
    public static class Client {

        private final API api;

        public Resource run(){
            try {
                Resource resource = api.getResource();
                return processResource(resource);
            } catch (ResourceTemporarilyUnavailableException e) {
                retry();
            } catch (AuthenticationException e) {
                lockAccount();
            } catch (AuthorizationFailedException e) {
                sendNoAccessMessage();
            }
            throw new UnsupportedOperationException("NYI");
        }

        private void sendNoAccessMessage(){};
        private void lockAccount() {}
        private void retry() {}
        private Resource processResource(final Resource resource) {
            throw new UnsupportedOperationException("NYI");
        }
    }

    public static class API {
        public Resource getResource() throws ResourceTemporarilyUnavailableException, AuthenticationException, AuthorizationFailedException {
            return new Resource();
        }
    }

    public static class ResourceTemporarilyUnavailableException extends RuntimeException {}
    public static class AuthenticationException extends RuntimeException {}
    public static class AuthorizationFailedException extends RuntimeException {}

}
