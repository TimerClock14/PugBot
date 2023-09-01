package pugbot.core.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import pugbot.Utils;
import pugbot.core.exceptions.BadArgumentsException;

public class CmdJoinGroup extends Command {

	@Override
	public Message execCommand(Member caller, String[] args) {
		if(args.length == 0){
			throw new BadArgumentsException("No group specified");
		}
		
		String groupName = String.join(" ", args);
		Role role = server.getGroup(groupName);
		
		server.getGuild().getController().addSingleRoleToMember(caller, role).queue();
		
		return Utils.createMessage(String.format("`%s joined the group: %s`", getPlayerIgn(caller), groupName));
	}

	@Override
	public boolean isAdminRequired() {
		return false;
	}

	@Override
	public boolean isGlobalCommand() {
		return false;
	}

	@Override
	public String getName() {
		return "JoinGroup";
	}

	@Override
	public String getDescription() {
		return "Grants you this group's pingable role";
	}

	@Override
	public String getHelp() {
		return getBaseCommand() + " <group name>";
	}

}
