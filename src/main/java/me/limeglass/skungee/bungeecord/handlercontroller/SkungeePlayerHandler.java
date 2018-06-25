package me.limeglass.skungee.bungeecord.handlercontroller;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.limeglass.skungee.bungeecord.Skungee;
import me.limeglass.skungee.objects.SkungeePacket;
import me.limeglass.skungee.objects.SkungeePlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class SkungeePlayerHandler extends SkungeeHandler {
	
	protected Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
	protected Set<ProxiedPlayer> players = new HashSet<ProxiedPlayer>();
	
	@Override
	public Boolean onPacketCall(SkungeePacket packet, InetAddress address) {
		if (packet.getPlayers() != null) {
			for (SkungeePlayer player : packet.getPlayers()) {
				ProxiedPlayer proxiedPlayer = null;
				if (Skungee.getConfig().getBoolean("IncomingUUIDs", true) && player.getUUID() != null) {
					proxiedPlayer = ProxyServer.getInstance().getPlayer(player.getUUID());
					if (proxiedPlayer == null) {
						proxiedPlayer = ProxyServer.getInstance().getPlayer(player.getName()); //invalid UUID
					}
				} else if (player.getName() != null) {
					proxiedPlayer = ProxyServer.getInstance().getPlayer(player.getName());
				}
				if (proxiedPlayer != null) players.add(proxiedPlayer);
			}
		}
		return !players.isEmpty();
	}
	
	public abstract Object handlePacket(SkungeePacket packet, InetAddress address);
}