package pt.licious.eggcellent.files.config;

import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import lombok.Getter;
import lombok.Setter;
import java.util.LinkedList;

@Getter
@Setter
public class Config {

    private LinkedList<String> blacklisted = new LinkedList<>();

    public Config() {
        for (String legendary : EnumSpecies.legendaries) {
            if (!this.blacklisted.contains(legendary))
                this.blacklisted.add(legendary);
        }
        for (String ultrabeast : EnumSpecies.ultrabeasts) {
            if (!this.blacklisted.contains(ultrabeast))
                this.blacklisted.add(ultrabeast);
        }
    }
}
