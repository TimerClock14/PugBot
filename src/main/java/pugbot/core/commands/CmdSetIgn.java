package pugbot.core.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

import pugbot.Utils;
import pugbot.core.Database;
import pugbot.core.exceptions.InvalidUseException;
import pugbot.core.exceptions.BadArgumentsException;

public class CmdSetIgn extends Command {
  @Override
  public Message execCommand(Member caller, String[] args) {
    StringBuilder stringBuilder = new StringBuilder();

    if (args.length == 0) {
      throw new InvalidUseException("No ign provided");
    }
    
    Member target;
    String nickname;

    if (args.length > 1) {
      if (!server.isAdmin(caller)) {
        throw new InvalidUseException("Admin required to change another player's ign");
      }
      
      target = server.getMember(args[0]);

      if (target == null) {
        throw new BadArgumentsException();
      }

      nickname = args[1];
    }
    else {
      target = caller;
      nickname = args[0];
    }

    Database.setPlayerIgn(server.getId(), target.getUser().getIdLong(), nickname);

    stringBuilder.append(String.format("%s nickname set to %s", caller.getEffectiveName(), caller.getNickname()));

    return Utils.createMessage(null, stringBuilder.toString(), true);
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
    return "Set IGN (In-Game Name)";
  }

  @Override
  public String getDescription() {
    return "Sets a player's ign";
  }

  @Override
  public String getHelp() {
    return getBaseCommand() + " <ign> - " + this.getDescription() + "\n" +
        getBaseCommand() + " <target username> <ign> - Sets the ign of a specific player (Admin required)";
  }
}