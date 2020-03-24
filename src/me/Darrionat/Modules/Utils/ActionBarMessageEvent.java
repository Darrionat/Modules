// The code within this class was and is not developed by Darrion. It is usage of ActionBarAPI.
// https://www.spigotmc.org/resources/actionbarapi-1-8-1-14-2.1315/
package me.Darrionat.Modules.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ActionBarMessageEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private String message;
	private boolean cancelled = false;

	public ActionBarMessageEvent(Player player, String message) {
		this.player = player;
		this.message = message;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Player getPlayer() {
		return player;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

}
