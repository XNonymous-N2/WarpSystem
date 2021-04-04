package de.xnonymous.warpsystem.warp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
@AllArgsConstructor
public class Warp {

    private String name;
    private Location location;

}
