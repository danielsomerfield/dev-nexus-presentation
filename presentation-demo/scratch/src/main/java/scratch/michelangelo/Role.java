package scratch.michelangelo;

import lombok.Value;

import java.util.Collection;

import static java.util.Arrays.asList;
import static scratch.michelangelo.Permission.Action.READ;
import static scratch.michelangelo.Permission.Action.WRITE;

@Value
public class Role {

    public static final Role MANAGER = new Role(
//            new Permission(WRITE, "goodies"),
            new Permission(READ, "goodies")
    );

    public static final Role ANONYMOUS = new Role();

    private final Collection<Permission> permissions;

    public Role(Permission... permissions) {
        this.permissions = asList(permissions);
    }

}
