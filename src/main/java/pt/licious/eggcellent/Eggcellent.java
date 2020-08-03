package pt.licious.eggcellent;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.config.PixelmonConfig;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pt.licious.eggcellent.commands.ReloadCommand;
import pt.licious.eggcellent.events.Events;
import pt.licious.eggcellent.files.FileHandler;

import java.io.File;

@Mod(
        modid = Eggcellent.MOD_ID,
        name = Eggcellent.MOD_NAME,
        version = Eggcellent.VERSION,
        acceptableRemoteVersions = "*",
        dependencies = "after:" + Pixelmon.MODID,
        acceptedMinecraftVersions = "[1.12.2]"
    )

public class Eggcellent {

    public static final String MOD_ID = "eggcellent";
    public static final String MOD_NAME = "Eggcellent";
    public static final String VERSION = "1.0";
    public static Logger log = LogManager.getLogger(MOD_NAME);
    public static File configDir;
    public static File configFile;
    public static Events events;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent e) {
        log.info("Booting up " + MOD_NAME + " made by Licious @2020 - " + VERSION);
        if (!PixelmonConfig.allowDittoDittoBreeding) {
            log.warn("You don't have Ditto x Ditto breeding enabled this plugin is not necessary and will not boot");
            log.warn("To enable this feature and plugin go to ./config/pixelmon.hocon and set 'allowDittoDittoBreeding' to true, this plugin will require a server reboot to register itself");
            return;
        }
        configDir = new File(e.getModConfigurationDirectory() + "/" + MOD_ID);
        configDir.mkdir();
        configFile = new File(configDir, "config.json");
        FileHandler.readConfig();
        FileHandler.creationCheck();
        FileHandler.writeConfig();
        events = new Events();
        log.info("Finished the booting process");
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent e) {
        log.info("Registering the reload command...");
        e.registerServerCommand(new ReloadCommand());
        log.info("Done!");
    }

}
