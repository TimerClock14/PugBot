package pugbot.core.commands;

import net.dv8tion.jda.core.entities.Member;
import pugbot.Utils;
import pugbot.core.entities.Server;

// Command abstract class

public abstract class Command implements ICommand {

	protected Server server;
	
	protected String getBaseCommand(){
		String prefix = server.getSettingsManager().getCommandPrefix();
		
		return prefix + getName();
	}
	
	public void setServer(Server server) {
		this.server = server;
	}

	protected String getPlayerIgn(Member player) {
		return Utils.getPlayerIgn(player);
	}

	protected String getPlayerIgn(long playerId) {
		return Utils.getPlayerIgn(playerId, server);
	}
}
