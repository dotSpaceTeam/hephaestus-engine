package team.unnamed.hephaestus.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EvalCommand implements CommandClass {

    private static final long COOL_DOWN = TimeUnit.SECONDS.toMillis(5);
    private final Map<UUID, Long> timestamps = new HashMap<>();
    private final ScriptEngine scriptEngine;

    public EvalCommand() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        this.scriptEngine = engineManager.getEngineByName("molang");
    }

    @Command(names = "eval")
    public void eval(CommandSender sender, String script) {

        if (sender instanceof Player) {
            UUID id = ((Player) sender).getUniqueId();
            Long lastUsage = timestamps.get(id);
            long now = System.currentTimeMillis();

            if (lastUsage != null && lastUsage + COOL_DOWN > now) {
                sender.sendMessage("§cPlease wait to execute this command again");
                return;
            }

            timestamps.put(id, now);
        }

        try {
            Object result = scriptEngine.eval(script);
            sender.sendMessage("§aResult: " + result);
        } catch (ScriptException e) {
            sender.sendMessage("§cScript Exception: " + e.getMessage());
        }
    }

}
