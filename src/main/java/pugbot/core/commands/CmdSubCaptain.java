package pugbot.core.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import pugbot.Utils;
import pugbot.core.entities.Game;
import pugbot.core.entities.QueueManager;
import pugbot.core.exceptions.BadArgumentsException;
import pugbot.core.exceptions.InvalidUseException;

public class CmdSubCaptain extends Command {

	@Override
	public Message execCommand(Member caller, String[] args) {
		if (args.length != 1) {
			throw new BadArgumentsException();
		}

		QueueManager qm = server.getQueueManager();
		Member target = server.getMember(args[0]);

		if (!qm.isPlayerIngame(caller)) {
			throw new InvalidUseException("You are not in-game");
		}

		Game game = qm.getPlayersGame(caller);

		if (!game.isCaptain(target)) {
			throw new InvalidUseException(getPlayerIgn(target) + " is not a captain in your game");
		}

		if (game.isCaptain(caller)) {
			throw new InvalidUseException("You are already a captain");
		}

		game.subCaptain(caller, target);

		return Utils.createMessage(String.format("`%s has replaced %s as captain`", getPlayerIgn(caller),
				getPlayerIgn(target)));
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
		return "SubCaptain";
	}

	@Override
	public String getDescription() {
		return "Substitute a captain in an ongoing game";
	}

	@Override
	public String getHelp() {
		return getBaseCommand() + " <captain username>";
	}
}
