package pugbot;

import java.awt.Color;
import java.io.File;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import pugbot.core.Database;
import pugbot.core.entities.Server;
import pugbot.core.entities.ServerManager;


public class Utils {
	
	/*
	 * Creates standard response message
	 */
	public static Message createMessage(String title, String description, boolean success){
		MessageBuilder embed = new MessageBuilder();
		Color color;
		if(success){
			color = Color.green;
		}else{
			color = Color.red;
		}
		if(title != null && !title.isEmpty()){
			embed.append(String.format("`%s`", title.replaceAll("`", "")));
		}
		if(description != null && !description.isEmpty()){
			embed.setEmbed(new EmbedBuilder().setDescription(description.replaceAll("`", "")).setColor(color).build());
		}
		
		return embed.build();
	}
	/*
	 * Creates standard response message with specified color
	 */
	public static Message createMessage(String title, String description, Color color){
		MessageBuilder embed = new MessageBuilder();
		if(title != null && !title.isEmpty()){
			embed.append(String.format("`%s`", title.replaceAll("`", "")));
		}
		if(description != null && !description.isEmpty()){
			embed.setEmbed(new EmbedBuilder().setDescription(description.replaceAll("`", "")).setColor(color).build());
		}
		return embed.build();
	}
	
	/*
	 * Creates message sans embed
	 */
	public static Message createMessage(String title){
		MessageBuilder embed = new MessageBuilder();
		embed.append(title);
		
		return embed.build();
	}
	
	/*
	 * Creates file
	 */
	public static void createFile(String path) {
		try{
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*
	 * Creates directory
	 */
	public static void createDir(String path) {
		try{
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdir();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/*
	 * Gets ign from member, or regular name if no nickname set
	 */
	public static String getPlayerIgn(Member player) {
		long serverId = player.getGuild().getIdLong();
		
		return Utils.getPlayerIgn(player, ServerManager.getServer(serverId));
	}

	/*
	 * Gets ign from member, or regular name if no nickname set
	 */
	public static String getPlayerIgn(Member player, Server server) {
		long playerId = player.getUser().getIdLong();
		return Utils.getPlayerIgn(playerId, server);
	}

	/*
	 * Gets ign from member, or regular name if no nickname set
	 */
	public static String getPlayerIgn(long playerId, Server server) {
		String fetchedNickname = Database.queryGetPlayerIgn(server.getId(), playerId);

		return fetchedNickname;
	}
}
