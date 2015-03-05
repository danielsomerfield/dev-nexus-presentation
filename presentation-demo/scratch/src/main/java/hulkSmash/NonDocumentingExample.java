package hulkSmash;

import lombok.AllArgsConstructor;

public class NonDocumentingExample {

    @AllArgsConstructor
    public static class Client {

        private final API api;

        public Resource processAndReturn(){
            Resource resource = api.getResource();
            processResource(resource);
            return resource;
        }

        private void processResource(final Resource resource) {
            resource.display();
        }
    }

    public static class API {
        public Resource getResource() {
            return new Resource();
        }
    }

}
