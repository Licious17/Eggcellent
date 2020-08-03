package pt.licious.eggcellent.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import pt.licious.eggcellent.Eggcellent;
import pt.licious.eggcellent.files.FileHandler;
import pt.licious.eggcellent.utils.PermissionUtils;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends CommandBase {

    @Override
    public String getName() {
        return "eggcellent";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "&cUsage: /eggcellent reload";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            FileHandler.readConfig();
            this.send(sender, "&aYou have reloaded the configuration files, please double check the console for any errors");
            return;
        }
        this.send(sender, getUsage(sender));
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        if (args.length == 1)
            possibleArgs.add("reload");
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionUtils.canUse(Eggcellent.MOD_ID + ".admin.reload", sender);
    }

    private void send(ICommandSender sender, String message) {
        sender.sendMessage(new TextComponentString(( "&f[&l&6" + Eggcellent.MOD_NAME + "&f] " + message).replaceAll("&([0-9a-fk-or])","\u00a7$1")));
    }


}
