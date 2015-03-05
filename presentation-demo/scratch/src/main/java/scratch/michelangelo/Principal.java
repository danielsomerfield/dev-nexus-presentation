package scratch.michelangelo;

import lombok.Value;

import java.util.Collection;
import java.util.stream.Collectors;

@Value
public class Principal {
    private Collection<Permission> permissions;

    public static Principal from(final Collection<Role> roles) {
        Collection<Permission> permissions = getPermissionFromRoles(roles);
        return new Principal(permissions);
    }

    private static Collection<Permission> getPermissionFromRoles(final Collection<Role> roles) {
        return roles.stream().flatMap(r -> r.getPermissions().stream()).collect(Collectors.toSet());
    }

    public boolean hasPermission(final String resourceName, final Permission.Action action) {
       return permissions.stream().filter(p -> p.getResource().equals(resourceName) && p.getAction().equals(action)).toArray().length > 0;
//        return permissions.stream().reduce(false, (a, perm)->a || isMatch(perm, resourceName, action), (x, y)->x);
    }

    private boolean isMatch(final Permission perm, final String resourceName, final Permission.Action action) {
        return perm.getResource().equals(resourceName) && perm.getAction().equals(action);
    }
}
