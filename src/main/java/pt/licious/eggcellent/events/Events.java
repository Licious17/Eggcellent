package pt.licious.eggcellent.events;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.util.helpers.BreedLogic;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pt.licious.eggcellent.files.FileHandler;

public class Events {

    public Events() {
        Pixelmon.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEggMade(BreedEvent.MakeEgg e) {
        if (!e.isCanceled() && e.parent1.getSpecies() == EnumSpecies.Ditto && e.parent2.getSpecies() == EnumSpecies.Ditto && FileHandler.config.getBlacklisted().contains(e.getEgg().getSpecies().name))
            while (FileHandler.config.getBlacklisted().contains(e.getEgg().getSpecies().name)) {
                e.setEgg(BreedLogic.makeEgg(e.parent1, e.parent2));
            }
    }

}
