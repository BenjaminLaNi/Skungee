package me.limeglass.skungee.objects.events;

import org.bukkit.event.Cancellable;

import me.limeglass.skungee.objects.SkungeePlayer;

public class SkungeePlayerChatEvent extends SkungeePlayerEvent implements Cancellable {

	private boolean cancelled;
	private String message;
	
	public SkungeePlayerChatEvent(String message, String server, SkungeePlayer... players) {
		super(server, players);
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

}
