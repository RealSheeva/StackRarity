package net.runelite.client.plugins.stackrarity;

// External
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

// RuneLite Plugins
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

// RuneLite
import net.runelite.client.config.ConfigManager;

// UI
import net.runelite.client.ui.overlay.OverlayManager;

import java.awt.*;

@Slf4j
@PluginDescriptor(
	name = "A Rare Stack"
)
public class StackRarityPlugin extends Plugin
{
	@Inject
	private StackRarityConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private StackRarityOverlay overlay;

	@Override
	protected void startUp() throws Exception
	{
		log.info("ItemRarity started!");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("ItemRarity stopped!");
		overlayManager.remove(overlay);
	}

	@Provides
	StackRarityConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(StackRarityConfig.class);
	}

	Color getRarityColor(final int itemPrice)
	{
		if (itemPrice < config.getCommonItemValue())
		{
			return config.getCommonItemColor();
		}
		else if (itemPrice < config.getUncommonItemValue())
		{
			return config.getUncommonItemColor();
		}
		else if (itemPrice < config.getRareItemValue())
		{
			return config.getRareItemColor();
		}
		else if (itemPrice < config.getEpicItemValue())
		{
			return config.getEpicItemColor();
		}

		return config.getLegendaryItemColor();
	}
}
